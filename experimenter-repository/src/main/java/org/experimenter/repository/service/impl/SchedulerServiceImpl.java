package org.experimenter.repository.service.impl;

import java.text.ParseException;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.repository.service.SchedulerService;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.HibernateInterceptor;

public class SchedulerServiceImpl implements SchedulerService {

    private Scheduler scheduler;
    private ExperimentService experimentService;
    private HibernateInterceptor hibernateInterceptor;

    @Override
    public void saveUpdateJob(Experiment experiment) {
        try {
            if (triggerAlreadyExists(experiment)) {
                updateJob(experiment);
            } else {
                createJob(experiment);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopJob(Experiment experiment) {
        try {
            scheduler.deleteJob(experiment.getId().toString(), null);
        } catch (SchedulerException e) {
            e.printStackTrace();
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
        RunMeTask task = new RunMeTask(experiment.getId(), experimentService);
        ProxyFactory factory = new ProxyFactory(task);
        Advised advised = (Advised) factory.getProxy();
        advised.addAdvice(hibernateInterceptor);
        RunMeTask advisedTask = (RunMeTask) advised;
        JobDetail jobDetail = new JobDetail(experiment.getId().toString(), null, RunMeJob.class);
        jobDetail.getJobDataMap().put("type", "FULL");
        jobDetail.getJobDataMap().put("runMeTask", advisedTask);
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

    @Required
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Required
    public void setExperimentService(ExperimentService experimentService) {
        this.experimentService = experimentService;
    }

    @Required
    public void setHibernateInterceptor(HibernateInterceptor hibernateInterceptor) {
        this.hibernateInterceptor = hibernateInterceptor;
    }

}
