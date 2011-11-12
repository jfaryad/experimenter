package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.ConnectionFarmDao;
import org.experimenter.repository.entity.ConnectionFarm;

public class ConnectionFarmDaoImpl extends AbstractBaseDaoImpl<ConnectionFarm> implements ConnectionFarmDao {

    @Override
    public Class<ConnectionFarm> getEntityClass() {
        return ConnectionFarm.class;
    }

    @Override
    public String getTableName() {
        return Constants.CONNECTION_FARM;
    }

}
