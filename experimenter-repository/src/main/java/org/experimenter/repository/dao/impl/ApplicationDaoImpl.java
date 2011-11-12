package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.ApplicationDao;
import org.experimenter.repository.entity.Application;

public class ApplicationDaoImpl extends AbstractBaseDaoImpl<Application> implements ApplicationDao {

    @Override
    public Class<Application> getEntityClass() {
        return Application.class;
    }

    @Override
    public String getTableName() {
        return Constants.APPLICATION;
    }

}
