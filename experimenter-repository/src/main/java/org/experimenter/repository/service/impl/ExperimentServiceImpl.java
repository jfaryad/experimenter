package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.service.ExperimentService;

public class ExperimentServiceImpl extends AbstractService<Experiment, ExperimentDao> implements ExperimentService {

    @Override
    protected void deleteDependencies(Experiment experiment) {
        junctionDao.removeConnectionFarmFromExperiment(null, experiment);
        junctionDao.removeInputSetFromExperiment(null, experiment);
    }

    @Override
    public List<Experiment> findExperimentsByApplication(Application application) {
        checkIdNotNull(application);
        Experiment experiment = new Experiment();
        experiment.setApplication(application);
        CriteriaForm<Experiment> criteria = new CriteriaForm<Experiment>(experiment);
        return baseDao.findByCriteria(criteria);
    }

    @Override
    public List<Experiment> findExperimentsByConnectionFarm(ConnectionFarm connectionFarm) {
        checkIdNotNull(connectionFarm);
        return baseDao.findExperimentsByConnectionFarm(connectionFarm);
    }

    @Override
    public List<Experiment> findExperimentsByInputSet(InputSet inputSet) {
        checkIdNotNull(inputSet);
        return baseDao.findExperimentsByInputSet(inputSet);
    }

}
