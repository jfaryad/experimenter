package org.experimenter.repository.service.impl;

import java.text.ParseException;
import java.util.List;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.scheduler.ExperimentExecutor;
import org.experimenter.repository.scheduler.ScheduledJob;
import org.experimenter.repository.scheduler.SessionAdvisor;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.repository.service.SchedulerService;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.orm.hibernate3.HibernateInterceptor;

/**
 * Implementation of {@link SchedulerService}
 * 
 * @author jfaryad
 */
public class SchedulerServiceImpl implements SchedulerService {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ExperimentService experimentService;
    @Autowired
    private HibernateInterceptor hibernateInterceptor;
    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    public SchedulerServiceImpl() {
    }

    @Override
    public void saveUpdateJob(Experiment experiment) {
        try {
            if (triggerAlreadyExists(experiment)) {
                updateJob(experiment);
            } else {
                createJob(experiment);
            }
        } catch (ParseException e) {
            LOG.error("Error saving job for experiment: " + experiment.getId(), e);
        } catch (SchedulerException e) {
            LOG.error("Error saving job for experiment: " + experiment.getId(), e);
        }
    }

    @Override
    public void stopJob(Experiment experiment) {
        try {
            scheduler.deleteJob(experiment.getId().toString(), null);
        } catch (SchedulerException e) {
            LOG.error("Error stopping job for experiment: " + experiment.getId(), e);
        }
    }

    private CronTrigger createNewTrigger(Experiment experiment) throws ParseException {
        CronTrigger newCronTrigger = new CronTrigger();
        newCronTrigger.setJobName(experiment.getId().toString());
        newCronTrigger.setName(experiment.getId().toString());
        newCronTrigger.setCronExpression(experiment.getCronExpression());
        return newCronTrigger;
    }

    private void addNewJobToScheduler(Experiment experiment) throws SchedulerException {
        ExperimentExecutor task = new ExperimentExecutor(experiment.getId());
        beanFactory.autowireBean(task);
        ExperimentExecutor sessionAwareTask = SessionAdvisor.adviseWithHibernateInterceptor(task,
                hibernateInterceptor);

        JobDetail jobDetail = new JobDetail(experiment.getId().toString(), null, ScheduledJob.class);
        jobDetail.getJobDataMap().put("type", "FULL");
        jobDetail.getJobDataMap().put("experimentExecutor", sessionAwareTask);
        scheduler.addJob(jobDetail, true);
    }

    private boolean triggerAlreadyExists(Experiment experiment) throws SchedulerException {
        return scheduler.getTrigger(experiment.getId().toString(), null) != null;
    }

    private void createJob(Experiment experiment) throws SchedulerException, ParseException {
        addNewJobToScheduler(experiment);
        scheduler.scheduleJob(createNewTrigger(experiment));
    }

    private void updateJob(Experiment experiment) throws SchedulerException, ParseException {
        CronTrigger updatedCronTrigger = createNewTrigger(experiment);
        scheduler.rescheduleJob(updatedCronTrigger.getName(), null, updatedCronTrigger);
    }

    /**
     * Is run at the start of the application to register all jobs from the database.
     */
    public void initializeAllActiveJobs() {
        List<Experiment> experiments = experimentService.findScheduledExperiments();
        for (Experiment experiment : experiments) {
            LOG.debug("Initializing experiment " + experiment.getId());
            saveUpdateJob(experiment);
        }

    }

}
