package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ConnectionFarmDao;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.service.ConnectionFarmService;

public class ConnectionFarmServiceImpl extends AbstractService<ConnectionFarm, ConnectionFarmDao> implements
        ConnectionFarmService {

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
        checkIdNotNull(userGroup);
        ConnectionFarm connectionFarm = new ConnectionFarm();
        connectionFarm.setUserGroup(userGroup);
        CriteriaForm<ConnectionFarm> criteria = new CriteriaForm<ConnectionFarm>(connectionFarm);
        return baseDao.findByCriteria(criteria);
    }

    @Override
    public List<ConnectionFarm> findConnectionFarmsByExperiment(Experiment experiment) {
        checkIdNotNull(experiment);
        return baseDao.findConnectionFarmsByExperiment(experiment);
    }

}
