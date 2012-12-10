package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;

/**
 * The data access object for the Input entity. Extends {@link BaseDao}.
 * 
 * @author jfaryad
 * 
 */
public interface InputDao extends BaseDao<Input> {

    /**
     * Finds the input by the value of the checksum of it's data.
     * 
     * @param checksum
     *            the checksom of the input data
     * @return the unique input
     */
    public Input findInputByChecksum(String checksum);

    /**
     * Finds the inputs belonging to any of the input sets assigned to the given experiment
     * 
     * @param experiment
     *            the experiment to retrieve inputs for
     * @return list of inputs
     */
    public List<Input> findInputsByExperiment(Experiment experiment);

}
