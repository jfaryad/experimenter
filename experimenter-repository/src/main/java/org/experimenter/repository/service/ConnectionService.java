package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.form.CriteriaForm;

public interface ConnectionService {

    /**
     * Saves the given {@link Connection} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param connection
     *            the connection to save
     */
    public void saveUpdate(Connection connection);

    /**
     * Finds the {@link Connection} with the given id.
     * 
     * @param id
     *            the identifier of the connection
     * @return the connection with the given id or null, if such an entry doesn't exist in the database.
     */
    public Connection findById(Integer id);

    /**
     * Finds the {@link Connection} that matches the properties set in the input form.
     * 
     * @param connection
     *            a search form with the properties you want to search by
     * @return a list of connections that match the example
     */
    public List<Connection> findByExample(Connection connection);

    /**
     * Finds the {@link Connection} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of connections that match the criteria
     */
    public List<Connection> findByCriteria(CriteriaForm<Connection> criteria);

    /**
     * Deletes the given {@link Connection} from the database, including all existing links to other tables.
     * 
     * @param connection
     *            the connection to delete
     */
    public void delete(Connection connection);

    /**
     * Deletes the given list of {@link Connection}s from the database, including all existing links to other tables.
     * 
     * @param connections
     *            the connections to delete
     */
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

}
