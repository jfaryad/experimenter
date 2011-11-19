package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.ConnectionFarmDao;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.form.SimpleForm;
import org.sqlproc.engine.SqlSession;

public class ConnectionFarmDaoImpl extends AbstractBaseDaoImpl<ConnectionFarm> implements ConnectionFarmDao {

    @Override
    public Class<ConnectionFarm> getEntityClass() {
        return ConnectionFarm.class;
    }

    @Override
    public String getTableName() {
        return Constants.CONNECTION_FARM;
    }

    @Override
    public List<ConnectionFarm> findConnectionFarmsByExperiment(Experiment experiment) {
        logger.debug(">> findConnectionFarmsByExperiment: " + experiment);
        SqlSession session = getSqlSession();
        String engineName = "FIND_FARM_BY_EXPERIMENT";
        List<ConnectionFarm> farms = getQueryEngine(engineName).query(session, getEntityClass(),
                new SimpleForm(experiment.getId()));
        logger.debug("<< findConnectionFarmsByExperiment: number of farms found:" + farms.size());
        return farms;
    }

}
