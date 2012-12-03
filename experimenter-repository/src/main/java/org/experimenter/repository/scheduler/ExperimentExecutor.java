package org.experimenter.repository.scheduler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
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
 * threads for runnint the experiment on multiple machines in parallel.
 * 
 * @author jfaryad
 * 
 */
public class ExperimentExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(ExperimentExecutor.class);

    private static final String DEST_DIR_BASE = "/tmp/expwork";
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

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

    @Value("${overloaded-connection-pool.retry.interval.millis}")
    private Integer overloadWaitInterval;

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
        List<ExperimentJob> jobList = loadBalanceJobs(experiment);
        final CountDownLatch doneSignal = new CountDownLatch(jobList.size());
        if (!experimentService.setExperimentStarted(experiment)) {
            LOG.error("Experiment " + experimentId + " already running or finished. Aborting ...");
            return;
        }
        for (ExperimentJob job : jobList) {
            job.setDoneSignal(doneSignal);
            taskExecutor.submit(job);
        }
        boolean finished = false;
        try {
            finished = doneSignal.await(maxWaitTimeForExperiments, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOG.info("Executor for experiment: " + experimentId + " interrupted. Number of jobs still running: "
                    + doneSignal.getCount());
        }
        LOG.info("Executor for experiment: " + experimentId + ", finished in time: " + finished + ", jobs executed: "
                + (jobList.size() - doneSignal.getCount()));
        experimentService.setExperimentFinished(experiment);

    }

    private List<ExperimentJob> loadBalanceJobs(Experiment experiment) {
        List<ExperimentJob> jobList = new ArrayList<ExperimentJob>();
        List<ConnectionFarm> availableFarms = experiment.getConnectionFarms();
        Integer maximumAllowedRunningJobsPerMachine = experiment.getMaxRunningJobs();
        Set<Input> availableInputs = getDistinctInputs(experiment);
        for (Input input : availableInputs) {
            Connection leastLoadedConnection;
            while ((leastLoadedConnection = connectionService.addJobToLeastLoadedConnection(availableFarms,
                    maximumAllowedRunningJobsPerMachine)) == null) {
                try {
                    Thread.sleep(overloadWaitInterval);
                } catch (InterruptedException e) {
                    LOG.warn("Interrupted while waiting for connection pool to unload", e);
                }
            }
            jobList.add(createJob(experiment, input, leastLoadedConnection));
        }
        return jobList;
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
        String command = experiment.getApplication().getProgram().getCommand();
        ExperimentJob job = new ExperimentJob(experimentId, computerId, inputId, hostname, port, login, password,
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
