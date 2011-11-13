package org.experimenter.repository.service.impl;

import org.experimenter.repository.entity.Experiment;

public class ExperimentServiceImpl extends AbstractService<Experiment> {

    @Override
    protected void deleteDependencies(Experiment Experiment) {
        junctionDao.removeExperimentFromExperimentGroup(Experiment, null);
    }

}
