package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ConnectionFarmDao;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.ConnectionFarmService;

public class ConnectionFarmServiceImpl extends AbstractService<ConnectionFarm, ConnectionFarmDao> implements
        ConnectionFarmService {

    @Override
    protected void deleteDependencies(ConnectionFarm connectionFarm) {
        connectionService.delete(connectionFarm.getConnections());
    }

    @Override
    public void addConnectionFarmToExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        experiment.getConnectionFarms().add(connectionFarm);
        connectionFarm.getExperiments().add(experiment);
    }

    @Override
    public void removeConnectionFarmFromExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        experiment.getConnectionFarms().remove(connectionFarm);
        connectionFarm.getExperiments().remove(experiment);
    }

    @Override
    public List<ConnectionFarm> findConnectionFarmsByUserGroup(UserGroup userGroup) {
        checkIdNotNull(userGroup);
        return baseDao.findConnectionFarmsByUserGroup(userGroup);
    }

    @Override
    public List<ConnectionFarm> findConnectionFarmsByExperiment(Experiment experiment) {
        checkIdNotNull(experiment);
        return baseDao.findConnectionFarmsByExperiment(experiment);
    }

    @Override
    public List<ConnectionFarm> findConnectionFarmsByUser(User user) {
        checkIdNotNull(user);
        return baseDao.findConnectionFarmsByUser(user);
    }

    @Override
    protected boolean hasDependencies(ConnectionFarm connectionFarm) {
        return !connectionFarm.getConnections().isEmpty();
    }

}
