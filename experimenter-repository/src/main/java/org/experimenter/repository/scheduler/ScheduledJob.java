package org.experimenter.repository.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ScheduledJob extends QuartzJobBean {
    private ExperimentExecutor experimentExecutor;

    public void setExperimentExecutor(ExperimentExecutor experimentExecutor) {
        this.experimentExecutor = experimentExecutor;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        experimentExecutor.execute();
    }
}