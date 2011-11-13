package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;

public interface ConnectionFarmDao extends BaseDao<ConnectionFarm> {

    /**
     * Find all conectionFarms belonging to the given experiment.
     * 
     * @param experiment
     *            the experiment to search by
     * @return a list of conectionFarms
     */
    public List<ConnectionFarm> findConnectionFarmsByExperiment(Experiment experiment);

}
