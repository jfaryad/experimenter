package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.service.ExperimentService;

public class ExperimentServiceImpl extends AbstractService<Experiment, ExperimentDao> implements ExperimentService {

    @Override
    protected void deleteDependencies(Experiment experiment) {
        // nothing to do
    }

    @Override
    public List<Experiment> findExperimentsByApplication(Application application) {
        checkIdNotNull(application);
        return application.getExperiments();
    }

    @Override
    public List<Experiment> findExperimentsByConnectionFarm(ConnectionFarm connectionFarm) {
        checkIdNotNull(connectionFarm);
        return connectionFarm.getExperiments();
    }

    @Override
    public List<Experiment> findExperimentsByInputSet(InputSet inputSet) {
        checkIdNotNull(inputSet);
        return inputSet.getExperiments();
    }

}
