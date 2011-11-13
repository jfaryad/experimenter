package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.ConnectionFarmService;

public class ConnectionFarmServiceImpl extends AbstractService<ConnectionFarm> implements ConnectionFarmService {

    @Override
    protected void deleteDependencies(ConnectionFarm connectionFarm) {
        junctionDao.removeConnectionFarmFromExperiment(connectionFarm, null);
        connectionService.delete(connectionService.findConnectionsByConnectionFarm(connectionFarm));
    }

    @Override
    public void addConnectionFarmToExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        junctionDao.addConnectionFarmToExperiment(connectionFarm, experiment);
    }

    @Override
    public void removeConnectionFarmFromExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        junctionDao.removeConnectionFarmFromExperiment(connectionFarm, experiment);
    }

    @Override
    public List<ConnectionFarm> findConnectionFarmsByUserGroup(UserGroup userGroup) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ConnectionFarm> findConnectionFarmsByExperiment(Experiment experiment) {
        // TODO Auto-generated method stub
        return null;
    }

}
