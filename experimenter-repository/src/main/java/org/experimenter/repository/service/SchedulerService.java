package org.experimenter.repository.service;

import org.experimenter.repository.entity.Experiment;

/**
 * The service responsible for scheduling experiments for execution
 * 
 * @author jfaryad
 * 
 */
public interface SchedulerService {

    /**
     * Saves a new job or updates an existing one for the given experiment.
     * 
     * @param experiment
     *            the experiment to save or update
     */
    public void saveUpdateJob(Experiment experiment);

    /**
     * Unregisters the job representing the given experiment
     * 
     * @param experiment
     *            the experiment to unregister
     */
    public void stopJob(Experiment experiment);
}
