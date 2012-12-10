package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.form.CriteriaForm;

/**
 * The service taking care of {@link Computer} related operations.
 * 
 * @author jfaryad
 * 
 */
public interface ComputerService extends EntityService<Computer> {

    /**
     * Saves the given {@link Computer} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param computer
     *            the computer to save
     */
    @Override
    public void saveUpdate(Computer computer);

    /**
     * Finds the {@link Computer} with the given id.
     * 
     * @param id
     *            the identifier of the computer
     * @return the computer with the given id or null, if such an entry doesn't exist in the database.
     */
    @Override
    public Computer findById(Integer id);

    /**
     * Finds the {@link Computer} that matches the properties set in the input form.
     * 
     * @param computer
     *            a search form with the properties you want to search by
     * @return a list of computers that match the example
     */
    @Override
    public List<Computer> findByExample(Computer computer);

    /**
     * Finds the {@link Computer} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of computers that match the criteria
     */
    @Override
    public List<Computer> findByCriteria(CriteriaForm<Computer> criteria);

    /**
     * Deletes the given {@link Computer} from the database, including all existing links to other tables.
     * 
     * @param computer
     *            the computer to delete
     */
    @Override
    public void delete(Computer computer);

    /**
     * Deletes the given list of {@link Computer}s from the database, including all existing links to other tables.
     * 
     * @param computers
     *            the computers to delete
     */
    @Override
    public void delete(List<Computer> computers);

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
