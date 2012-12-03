package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ConnectionDao;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.ConnectionService;

public class ConnectionServiceImpl extends AbstractService<Connection, ConnectionDao> implements ConnectionService {

    @Override
    protected void deleteDependencies(Connection connection) {
    }

    @Override
    public List<Connection> findConnectionsByConnectionFarm(ConnectionFarm connectionFarm) {
        checkIdNotNull(connectionFarm);
        return connectionFarm.getConnections();
    }

    @Override
    public List<Connection> findConnectionsByComputer(Computer computer) {
        checkIdNotNull(computer);
        return computer.getConnections();
    }

    @Override
    public List<Connection> findConnectionsByUser(User user) {
        checkIdNotNull(user);
        return baseDao.findConnectionsByUser(user);
    }

    @Override
    protected boolean hasDependencies(Connection connection) {
        return false;
    }

    @Override
    public synchronized Connection addJobToLeastLoadedConnection(List<ConnectionFarm> connectionFarms,
            Integer maxRunningJobs) {
        checkNotEmpty(connectionFarms);
        if (maxRunningJobs == null || maxRunningJobs <= 0) {
            maxRunningJobs = Integer.MAX_VALUE;
        }
        Connection connection = baseDao.findLeastLoadedConnection(connectionFarms, maxRunningJobs);
        if (connection != null) {
            Computer computer = connection.getComputer();
            computer.setNumberOfRunningJobs(computer.getNumberOfRunningJobs() + 1);
            computerService.saveUpdate(computer);
        }
        return connection;
    }

}
