package org.experimenter.repository.scheduler;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.repository.service.InputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * Wraps the launching of the ExperimentJob in another thread pool executor and waits for it to finish, or the timeout
 * to pass. After that, the thread pool will be shutdown by force, killing any running jobs.
 * 
 * @author jfaryad
 * 
 */
public class JobLauncher implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(JobLauncher.class);

    @Autowired
    private ExperimentService experimentService;
    @Autowired
    private ConnectionService connectionService;
    @Autowired
    private InputService inputService;
    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Value("${overloaded-connection-pool.retry.interval.millis}")
    private Integer overloadWaitInterval;
    @Value("${experiment-timeout-seconds}")
    private Integer maxWaitTimeForExperiments;

    private final CountDownLatch doneSignal;

    private final ThreadPoolExecutor timeoutExecutor = new ThreadPoolExecutor(1, 1, 601, TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>());

    private final Integer experimentId;
    private final Integer inputId;
    private Integer selectedConnection;

    public JobLauncher(CountDownLatch doneSignal, Integer experimentId, Integer inputId) {
        this.experimentId = experimentId;
        this.inputId = inputId;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        ExperimentJob job = loadBalancedJob();
        timeoutExecutor.execute(job);
        try {
            if (timeoutExecutor.awaitTermination(maxWaitTimeForExperiments, TimeUnit.SECONDS)) {
                LOG.error("timeoutExecutor terminated because of timeout");
            }
        } catch (InterruptedException e) {
            LOG.error("timeoutExecutor interrupted while waiting for job to finish");
        }
        timeoutExecutor.shutdownNow();
        connectionService.removeJobFromConnection(connectionService.findById(selectedConnection));
        LOG.debug("Exiting experiment: " + experimentId + ", inputId: " + inputId);
        doneSignal.countDown();
    }

    private ExperimentJob loadBalancedJob() {
        Experiment experiment = experimentService.findById(experimentId);
        List<ConnectionFarm> availableFarms = experiment.getConnectionFarms();
        Integer maximumAllowedRunningJobsPerMachine = experiment.getMaxRunningJobs();
        Input input = inputService.findById(inputId);
        Connection leastLoadedConnection;
        while ((leastLoadedConnection = connectionService.addJobToLeastLoadedConnection(availableFarms,
                maximumAllowedRunningJobsPerMachine)) == null) {
            try {
                LOG.debug("all connections are overloaded, sleeping...");
                Thread.sleep(overloadWaitInterval);
            } catch (InterruptedException e) {
                LOG.warn("Interrupted while waiting for connection pool to unload", e);
            }
        }
        ExperimentJob job = createJob(experiment, input, leastLoadedConnection);
        return job;
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

}
