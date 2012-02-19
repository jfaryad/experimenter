package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ConnectionDao;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.service.ConnectionService;

public class ConnectionServiceImpl extends AbstractService<Connection, ConnectionDao> implements ConnectionService {

    @Override
    protected void deleteDependencies(Connection Connection) {
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

}
