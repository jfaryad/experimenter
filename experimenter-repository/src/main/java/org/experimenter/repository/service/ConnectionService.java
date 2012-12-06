package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.form.CriteriaForm;

public interface ConnectionService extends EntityService<Connection> {

    /**
     * Saves the given {@link Connection} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param connection
     *            the connection to save
     */
    @Override
    public void saveUpdate(Connection connection);

    /**
     * Finds the {@link Connection} with the given id.
     * 
     * @param id
     *            the identifier of the connection
     * @return the connection with the given id or null, if such an entry doesn't exist in the database.
     */
    @Override
    public Connection findById(Integer id);

    /**
     * Finds the {@link Connection} that matches the properties set in the input form.
     * 
     * @param connection
     *            a search form with the properties you want to search by
     * @return a list of connections that match the example
     */
    @Override
    public List<Connection> findByExample(Connection connection);

    /**
     * Finds the {@link Connection} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of connections that match the criteria
     */
    @Override
    public List<Connection> findByCriteria(CriteriaForm<Connection> criteria);

    /**
     * Deletes the given {@link Connection} from the database, including all existing links to other tables.
     * 
     * @param connection
     *            the connection to delete
     */
    @Override
    public void delete(Connection connection);

    /**
     * Deletes the given list of {@link Connection}s from the database, including all existing links to other tables.
     * 
     * @param connections
     *            the connections to delete
     */
    @Override
    public void delete(List<Connection> connections);

    /**
     * Find all connections belonging to the given connectionFarm.
     * 
     * @param connectionFarm
     *            the connectionFarm to search by
     * @return a list of connections
     */
    public List<Connection> findConnectionsByConnectionFarm(ConnectionFarm connectionFarm);

    /**
     * Find all connections belonging to the given computer.
     * 
     * @param computer
     *            the computer to search by
     * @return a list of connections
     */
    public List<Connection> findConnectionsByComputer(Computer computer);

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
     * machine and increments the job counter.<br>
     * If there are several equally loaded machines, any of them can be selected.
     * <p>
     * <b>WARNING</b> This method must be synchronized, either in jdk or in the database (select for update)
     * 
     * @param connectionFarms
     *            the farms that will be searched
     * 
     * @param maxRunningJobs
     *            find only computers with maxRunningJobs running. If maxRunningJobs is null or negative, it will be
     *            ignored
     * @return the selected connection or null, no such connection exists
     */
    public Connection addJobToLeastLoadedConnection(List<ConnectionFarm> connectionFarms, Integer maxRunningJobs);

    /**
     * Decreases the running jobs counter on the computer this connection belogs to.
     * <p>
     * <b>WARNING</b> This method must be synchronized, either in jdk or in the database (select for update)
     * 
     * @param connection
     *            the connection whose computer's job count is to be decreased
     */
    public void removeJobFromConnection(Connection connection);

    /**
     * Tests whether it is possible to connect to a remote machine using the information provided in the arguments.
     * 
     * @param hostname
     * @param login
     * @param password
     * @param port
     * @return true if connection was sucessful, false otherwise
     */
    boolean testConnection(String hostname, String login, String password, Short port);

}
