package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;

public interface ExperimentDao extends BaseDao<Experiment> {

    /**
     * Find all experiments the connectionFarm belongs to.
     * 
     * @param connectionFarm
     *            the connectionFarm to search by
     * @return a list of experiments
     */
    public List<Experiment> findExperimentsByConnectionFarm(ConnectionFarm connectionFarm);

    /**
     * Find all experiments the inputSet belongs to.
     * 
     * @param inputSet
     *            the inputSet to search by
     * @return a list of experiments
     */
    public List<Experiment> findExperimentsByInputSet(InputSet inputSet);

}
