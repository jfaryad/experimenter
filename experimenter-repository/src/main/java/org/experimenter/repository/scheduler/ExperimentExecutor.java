package org.experimenter.repository.scheduler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.repository.service.ExperimentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.orm.hibernate3.HibernateInterceptor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * The actual executor of the experiment. It holds references to the experimenterService and the taskExecutor and when
 * the {@link #execute()} method is called, it prepares the input files, the executable and starts multiple background
 * threads for running the experiment on multiple machines in parallel.
 * 
 * @author jfaryad
 * 
 */
public class ExperimentExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(ExperimentExecutor.class);

    private Integer experimentId;

    @Autowired
    private ExperimentService experimentService;
    @Autowired
    private ConnectionService connectionService;
    @Autowired
    private HibernateInterceptor hibernateInterceptor;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Value("${experiment-timeout-seconds}")
    private Integer maxWaitTimeForExperiments;

    @Value("${overloaded-connection-pool.retry.interval.seconds}")
    private Integer overloadedConnectionPoolWaitInterval;

    @Value("${task.executor.rejected.retry.interval.seconds}")
    private Integer overloadedTaskExecutorWaitInterval;

    @Value("${task.executor.rejected.retry.time.seconds}")
    private Integer overloadedTaskExecutorWaitTime;

    private CountDownLatch doneSignal;

    public ExperimentExecutor() {

    }

    public ExperimentExecutor(Integer experimentId) {
        this.experimentId = experimentId;
    }

    public void execute() {
        LOG.info("Initializing experiment " + experimentId);
        Experiment experiment = experimentService.findById(experimentId);
        if (experiment == null) {
            throw new RuntimeException("Unable to find an experiment with id: " + experimentId);
        }
        if (!experimentService.setExperimentStarted(experiment)) {
            LOG.error("Experiment " + experimentId + " already running or finished. Aborting ...");
            return;
        }

        int numberOfJobs = loadBalanceJobs(experiment);
        boolean finished = false;
        try {
            finished = doneSignal.await(maxWaitTimeForExperiments, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOG.info("Executor for experiment: " + experimentId + " interrupted. Number of jobs still running: "
                    + doneSignal.getCount());
        }
        LOG.info("Executor for experiment: " + experimentId + ", finished in time: " + finished + ", jobs executed: "
                + (numberOfJobs - doneSignal.getCount()) + " out of total: " + numberOfJobs);
        experimentService.setExperimentFinished(experiment);

    }

    /**
     * Iterates through the inputs and every time tries to acquire a free connection from the connection pool. If it
     * succeeds, a new job is submitted to the task executor.
     * <p>
     * The acquiring of a free connection and also of a free thread in the thread pool are themselves wrapped in an
     * iteration that tries to execute the operation regularly, until it succeeds or the specified time runs out.
     * 
     * @param experiment
     * @return
     */
    private int loadBalanceJobs(Experiment experiment) {
        List<ConnectionFarm> availableFarms = experiment.getConnectionFarms();
        Integer maximumAllowedRunningJobsPerMachine = experiment.getMaxRunningJobs();
        Set<Input> availableInputs = getDistinctInputs(experiment);
        doneSignal = new CountDownLatch(availableInputs.size());
        for (Input input : availableInputs) {
            Connection leastLoadedConnection;
            while ((leastLoadedConnection = connectionService.addJobToLeastLoadedConnection(availableFarms,
                    maximumAllowedRunningJobsPerMachine)) == null) {
                try {
                    Thread.sleep(1000 * overloadedConnectionPoolWaitInterval);
                } catch (InterruptedException e) {
                    LOG.warn("Interrupted while waiting for connection pool to unload", e);
                }
            }
            ExperimentJob job = createJob(experiment, input, leastLoadedConnection);
            job.setDoneSignal(doneSignal);
            for (long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(overloadedTaskExecutorWaitTime); stop > System
                    .nanoTime();) {
                try {
                    taskExecutor.submit(job);
                    break;
                } catch (RejectedExecutionException e) {
                    LOG.warn("Job rejected by the taskExecutor, waiting and retrying.", e);
                    try {
                        Thread.sleep(1000 * overloadedTaskExecutorWaitInterval);
                    } catch (InterruptedException e1) {
                        LOG.warn("Interrupted while waiting for the thead manager to unload", e);
                    }
                }
            }

        }
        return availableInputs.size();
    }

    private ExperimentJob createJob(Experiment experiment, Input input, Connection connection) {
        String executable = experiment.getApplication().getExecutable();
        Integer computerId = connection.getComputer().getId();
        Integer inputId = input.getId();
        String hostname = connection.getComputer().getAddress();
        Short port = connection.getPort();
        String login = connection.getLogin();
        String password = connection.getPassword();
        String pathToFile = input.getData();
        String command = experiment.getCommand();
        ExperimentJob job = new ExperimentJob(experimentId, computerId, inputId, connection.getId(), hostname, port,
                login, password,
                pathToFile, command, executable);
        beanFactory.autowireBean(job);
        return job;
    }

    private Set<Input> getDistinctInputs(Experiment experiment) {
        Set<Input> distinctInputs = new HashSet<Input>();
        for (InputSet inputSet : experiment.getInputSets()) {
            distinctInputs.addAll(inputSet.getInputs());
        }
        return distinctInputs;
    }

}
