package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.ConnectionDao;
import org.experimenter.repository.entity.Connection;

public class ConnectionDaoImpl extends AbstractBaseDaoImpl<Connection> implements ConnectionDao {

    @Override
    public Class<Connection> getEntityClass() {
        return Connection.class;
    }

    @Override
    public String getTableName() {
        return Constants.CONNECTION;
    }

}
