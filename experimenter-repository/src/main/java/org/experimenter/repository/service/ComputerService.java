package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.form.CriteriaForm;

public interface ComputerService {

    /**
     * Saves the given {@link Computer} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param computer
     *            the computer to save
     */
    public void saveUpdate(Computer computer);

    /**
     * Finds the {@link Computer} with the given id.
     * 
     * @param id
     *            the identifier of the computer
     * @return the computer with the given id or null, if such an entry doesn't exist in the database.
     */
    public Computer findById(Integer id);

    /**
     * Finds the {@link Computer} that matches the properties set in the input form.
     * 
     * @param computer
     *            a search form with the properties you want to search by
     * @return a list of computers that match the example
     */
    public List<Computer> findByExample(Computer computer);

    /**
     * Finds the {@link Computer} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of computers that match the criteria
     */
    public List<Computer> findByCriteria(CriteriaForm<Computer> criteria);

    /**
     * Deletes the given {@link Computer} from the database, including all existing links to other tables.
     * 
     * @param computer
     *            the computer to delete
     */
    public void delete(Computer computer);

    /**
     * Deletes the given list of {@link Computer}s from the database, including all existing links to other tables.
     * 
     * @param computers
     *            the computers to delete
     */
    public void delete(List<Computer> computers);

}
