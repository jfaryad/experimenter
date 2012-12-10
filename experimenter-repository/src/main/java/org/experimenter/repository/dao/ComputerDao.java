package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.ConnectionFarm;

/**
 * The data access object for the Computer entity. Extends {@link BaseDao}.
 * 
 * @author jfaryad
 * 
 */
public interface ComputerDao extends BaseDao<Computer> {

    /**
     * Finds the computer belonging to any of the given farms that have the lowest number of running jobs on
     * 
     * @param connectionFarms
     *            the farms that will be searched
     * @param maxRunningJobs
     *            find only computers with maxRunningJobs running
     * @return the first found computer with the lowest number of jobs or null, if no such computer was found
     */
    Computer findLeastLoadedComputer(List<ConnectionFarm> connectionFarms, int maxRunningJobs);

}
