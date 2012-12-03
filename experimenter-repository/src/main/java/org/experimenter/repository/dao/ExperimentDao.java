package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.User;

public interface ExperimentDao extends BaseDao<Experiment> {

    /**
     * Find all experiments belonging to any user group the given user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of experiments
     */
    public List<Experiment> findExperimentsByUser(User user);

    /**
     * Finds all experiments that have a cron expression set, but no result yet.
     * 
     * @return a list of experiments
     */
    public List<Experiment> findScheduledExperiments();

}
