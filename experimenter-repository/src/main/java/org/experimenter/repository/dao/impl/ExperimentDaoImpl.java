package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;

public class ExperimentDaoImpl extends AbstractBaseDaoImpl<Experiment> implements ExperimentDao {

    @Override
    public Class<Experiment> getEntityClass() {
        return Experiment.class;
    }

    @Override
    public void removeFromAssociations(Experiment experiment) {
        experiment.getApplication().getExperiments().remove(experiment);
        for (ConnectionFarm connectionFarm : experiment.getConnectionFarms())
            connectionFarm.getExperiments().remove(experiment);
        for (InputSet inputSet : experiment.getInputSets())
            inputSet.getExperiments().remove(experiment);
    }

    // @Override
    // public List<Experiment> findExperimentsByConnectionFarm(ConnectionFarm connectionFarm) {
    // logger.debug(">> findExperimentsByConnectionFarm: " + connectionFarm);
    // SqlSession session = getSqlSession();
    // String engineName = "FIND_EXPERIMENT_BY_FARM";
    // List<Experiment> experiments = getQueryEngine(engineName).query(session, getEntityClass(),
    // new SimpleForm(connectionFarm.getId()));
    // logger.debug("<< findExperimentsByConnectionFarm: number of experiments found:" + experiments.size());
    // return experiments;
    // }
    //
    // @Override
    // public List<Experiment> findExperimentsByInputSet(InputSet inputSet) {
    // logger.debug(">> findExperimentsByInputSet: " + inputSet);
    // SqlSession session = getSqlSession();
    // String engineName = "FIND_EXPERIMENT_BY_INPUT_SET";
    // List<Experiment> experiments = getQueryEngine(engineName).query(session, getEntityClass(),
    // new SimpleForm(inputSet.getId()));
    // logger.debug("<< findExperimentsByInputSet: number of experiments found:" + experiments.size());
    // return experiments;
    // }

}
