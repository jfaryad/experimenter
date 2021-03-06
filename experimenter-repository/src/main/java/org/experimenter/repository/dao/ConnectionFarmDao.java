package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.UserGroup;

public interface ConnectionFarmDao extends BaseDao<ConnectionFarm> {

    /**
     * Find all conectionFarms belonging to the given experiment.
     * 
     * @param experiment
     *            the experiment to search by
     * @return a list of conectionFarms
     */
    public List<ConnectionFarm> findConnectionFarmsByExperiment(Experiment experiment);

    /**
     * Find all conectionFarms belonging to the given user group.
     * 
     * @param userGroup
     *            the user group to search by
     * @return a list of conectionFarms
     */
    public List<ConnectionFarm> findConnectionFarmsByUserGroup(UserGroup userGroup);

    /**
     * Delete all the connection farms belonging to the given user group.
     * 
     * @param userGroup
     *            the user group whose connection farms to delete
     */
    public void deleteConnectionFarmsByUserGroup(UserGroup userGroup);

}
