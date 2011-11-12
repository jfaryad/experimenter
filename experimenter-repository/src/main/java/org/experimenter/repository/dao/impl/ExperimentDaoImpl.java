package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.entity.Experiment;

public class ExperimentDaoImpl extends AbstractBaseDaoImpl<Experiment> implements ExperimentDao {

    @Override
    public Class<Experiment> getEntityClass() {
        return Experiment.class;
    }

    @Override
    public String getTableName() {
        return Constants.EXPERIMENT;
    }

}
