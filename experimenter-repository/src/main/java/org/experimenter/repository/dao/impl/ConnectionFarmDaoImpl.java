package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.ConnectionFarmDao;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;

public class ConnectionFarmDaoImpl extends AbstractBaseDaoImpl<ConnectionFarm> implements ConnectionFarmDao {

    @Override
    public Class<ConnectionFarm> getEntityClass() {
        return ConnectionFarm.class;
    }

    @Override
    public void removeFromAssociations(ConnectionFarm connectionFarm) {
        connectionFarm.getUserGroup().getConnectionFarms().remove(connectionFarm);
        for (Experiment experiment : connectionFarm.getExperiments())
            experiment.getConnectionFarms().remove(connectionFarm);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ConnectionFarm> findConnectionFarmsByExperiment(Experiment experiment) {
        logger.debug(">> findConnectionFarmsByExperiment: " + experiment);
        List<ConnectionFarm> farms = getSession().getNamedQuery(ConnectionFarm.Q_GET_BY_EXPERIMENT)
                .setEntity("experiment", experiment).list();
        logger.debug("<< findConnectionFarmsByExperiment: number of farms found:" + farms.size());
        return farms;
    }

    @Override
    public void deleteConnectionFarmsByUserGroup(UserGroup userGroup) {
        logger.debug(">> deleteConnectionFarmsByUserGroup: " + userGroup);
        getSession().getNamedQuery(ConnectionFarm.Q_DELETE_BY_USERGROUP).setEntity("userGroup", userGroup)
                .executeUpdate();
        logger.debug("<< deleteConnectionFarmsByUserGroup");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ConnectionFarm> findConnectionFarmsByUserGroup(UserGroup userGroup) {
        logger.debug(">> findConnectionFarmsByUserGroup: " + userGroup);
        List<ConnectionFarm> farms = getSession().getNamedQuery(ConnectionFarm.Q_GET_BY_USERGROUP)
                .setEntity("userGroup", userGroup).list();
        logger.debug("<< findConnectionFarmsByUserGroup: number of farms found:" + farms.size());
        return farms;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ConnectionFarm> findConnectionFarmsByUser(User user) {
        logger.debug(">> findConnectionFarmsByUser: " + user);
        List<ConnectionFarm> farms = getSession().getNamedQuery(ConnectionFarm.Q_GET_BY_USER)
                .setEntity("user", user)
                .list();
        logger.debug("<< findConnectionFarmsByUser: number of farms found:" + farms.size());
        return farms;
    }

}
