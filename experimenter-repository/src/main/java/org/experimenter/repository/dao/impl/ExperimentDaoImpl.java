package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.form.SimpleForm;
import org.sqlproc.engine.SqlSession;

public class ExperimentDaoImpl extends AbstractBaseDaoImpl<Experiment> implements ExperimentDao {

    @Override
    public Class<Experiment> getEntityClass() {
        return Experiment.class;
    }

    @Override
    public String getTableName() {
        return Constants.EXPERIMENT;
    }

    @Override
    public List<Experiment> findExperimentsByConnectionFarm(ConnectionFarm connectionFarm) {
        logger.debug(">> findExperimentsByConnectionFarm: " + connectionFarm);
        SqlSession session = getSqlSession();
        String engineName = "FIND_EXPERIMENT_BY_FARM";
        List<Experiment> experiments = getQueryEngine(engineName).query(session, getEntityClass(),
                new SimpleForm(connectionFarm.getId()));
        logger.debug("<< findExperimentsByConnectionFarm: number of experiments found:" + experiments.size());
        return experiments;
    }

    @Override
    public List<Experiment> findExperimentsByInputSet(InputSet inputSet) {
        logger.debug(">> findExperimentsByInputSet: " + inputSet);
        SqlSession session = getSqlSession();
        String engineName = "FIND_EXPERIMENT_BY_INPUT_SET";
        List<Experiment> experiments = getQueryEngine(engineName).query(session, getEntityClass(),
                new SimpleForm(inputSet.getId()));
        logger.debug("<< findExperimentsByInputSet: number of experiments found:" + experiments.size());
        return experiments;
    }

}
