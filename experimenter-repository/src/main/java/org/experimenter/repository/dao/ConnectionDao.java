package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.User;

/**
 * The data access object for the Connection entity. Extends {@link BaseDao}.
 * 
 * @author jfaryad
 * 
 */
public interface ConnectionDao extends BaseDao<Connection> {

    /**
     * Find all connections belonging to any user group the given user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of connections
     */
    public List<Connection> findConnectionsByUser(User user);

    /**
     * Finds the connection belonging to any of the given farms that have the lowest number of running jobs on it's
     * machine
     * 
     * @param connectionFarms
     *            the farms that will be searched
     * @param maxRunningJobs
     *            find only computers with maxRunningJobs running
     * @return the first found connection with the lowest number of jobs or null, if no connection was found
     */
    public Connection findLeastLoadedConnection(List<ConnectionFarm> connectionFarms, Integer maxRunningJobs);

    /**
     * Finds all connections with the specified computer and farm
     * 
     * @param connectionFarms
     * @param computer
     * @return a non null list
     */
    public List<Connection> findByComputerAndConnectionFarms(List<ConnectionFarm> connectionFarms, Computer computer);

}
