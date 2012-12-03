package org.experimenter.repository.dao;

import org.experimenter.repository.entity.Input;

public interface InputDao extends BaseDao<Input> {

    /**
     * Finds the input by the value of the checksum of it's data.
     * 
     * @param checksum
     *            the checksom of the input data
     * @return the unique input
     */
    public Input findInputByChecksum(String checksum);

}
