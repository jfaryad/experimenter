package org.experimenter.repository.service;

import org.experimenter.repository.entity.Experiment;

public interface SchedulerService {

    public void saveUpdateJob(Experiment experiment);

    public void stopJob(Experiment experiment);
}
