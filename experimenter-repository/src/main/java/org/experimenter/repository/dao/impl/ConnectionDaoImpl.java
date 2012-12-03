package org.experimenter.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.experimenter.repository.dao.ConnectionDao;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.User;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Connection> findConnectionsByUser(User user) {
        logger.debug(">> findConnectionsByUser: " + user);
        List<Connection> list = getSession().getNamedQuery(Connection.Q_GET_BY_USER)
                .setEntity("user", user)
                .list();
        logger.debug("<< findConnectionsByUser: number found:" + list.size());
        return list;
    }

    @Override
    public Connection findLeastLoadedConnection(List<ConnectionFarm> connectionFarms, Integer maxRunningJobs) {
        logger.debug(">> findLeastLoadedConnection");
        List<Integer> farmIds = new ArrayList<Integer>();
        for (ConnectionFarm farm : connectionFarms) {
            farmIds.add(farm.getId());
        }
        Connection connection = (Connection) getSession().getNamedQuery(Connection.Q_GET_LEAST_LOADED)
                .setParameterList("farmIds", farmIds)
                .setInteger("maxJobs", maxRunningJobs)
                .setMaxResults(1)
                .uniqueResult();
        logger.debug("<< findLeastLoadedConnection: found: " + connection);
        return connection;
    }

}
