package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ConnectionDao;
import org.experimenter.repository.entity.Connection;

public class ConnectionDaoImpl extends AbstractBaseDaoImpl<Connection> implements ConnectionDao {

    @Override
    public Class<Connection> getEntityClass() {
        return Connection.class;
    }

    @Override
    public void removeFromAssociations(Connection connection) {
        connection.getConnectionFarm().getConnections().remove(connection);
        connection.getComputer().getConnections().remove(connection);
    }

}
