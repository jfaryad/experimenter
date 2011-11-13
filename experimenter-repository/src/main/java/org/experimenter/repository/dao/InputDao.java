package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;

public interface InputDao extends BaseDao<Input> {

    /**
     * Find all inputs belonging to the given inputSet.
     * 
     * @param inputSet
     *            the inputSet to search by
     * @return a list of inputs
     */
    public List<Input> findInputsByInputSet(InputSet inputSet);

}
