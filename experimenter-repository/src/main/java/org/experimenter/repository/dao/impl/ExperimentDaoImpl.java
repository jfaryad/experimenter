package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.User;

public class ExperimentDaoImpl extends AbstractBaseDaoImpl<Experiment> implements ExperimentDao {

    @Override
    public Class<Experiment> getEntityClass() {
        return Experiment.class;
    }

    @Override
    public void removeFromAssociations(Experiment experiment) {
        experiment.getApplication().getExperiments().remove(experiment);
        for (ConnectionFarm connectionFarm : experiment.getConnectionFarms())
            connectionFarm.getExperiments().remove(experiment);
        for (InputSet inputSet : experiment.getInputSets())
            inputSet.getExperiments().remove(experiment);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Experiment> findExperimentsByUser(User user) {
        logger.debug(">> findExperimentsByUser: " + user);
        List<Experiment> list = getSession().getNamedQuery(Experiment.Q_GET_BY_USER)
                .setEntity("user", user)
                .list();
        logger.debug("<< findExperimentsByUser: number found:" + list.size());
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Experiment> findScheduledExperiments() {
        logger.debug(">> findScheduledExperiments");
        List<Experiment> list = getSession().getNamedQuery(Experiment.Q_GET_SCHEDULED).list();
        logger.debug("<< findScheduledExperiments: number found:" + list.size());
        return list;
    }
}
