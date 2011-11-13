package org.experimenter.repository.service.impl;

import org.experimenter.repository.entity.Connection;

public class ConnectionServiceImpl extends AbstractService<Connection> {

    @Override
    protected void deleteDependencies(Connection Connection) {
        junctionDao.removeConnectionFromConnectionGroup(Connection, null);
    }

}
