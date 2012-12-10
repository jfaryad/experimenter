package org.experimenter.repository.service;

import java.io.File;
import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.form.CriteriaForm;

/**
 * The service taking care of {@link Application} related operations.
 * 
 * @author jfaryad
 * 
 */
public interface ApplicationService extends EntityService<Application> {

    /**
     * Saves the given {@link Application} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param application
     *            the application to save
     */
    @Override
    public void saveUpdate(Application application);

    /**
     * Saves the givn {@link Application} to the database and copies the given data file to the storage.<br>
     * If copying fails, the database operation will be rolled back.
     * 
     * @param application
     *            the application to save
     * @param tmpDataFile
     *            the application data in a temporary file
     */
    public void saveWithData(Application application, File tmpDataFile);

    /**
     * Finds the {@link Application} with the given id.
     * 
     * @param id
     *            the identifier of the application
     * @return the application with the given id or null, if such an entry doesn't exist in the database.
     */
    @Override
    public Application findById(Integer id);

    /**
     * Finds the {@link Application} that matches the properties set in the input form.
     * 
     * @param application
     *            a search form with the properties you want to search by
     * @return a list of applications that match the example
     */
    @Override
    public List<Application> findByExample(Application application);

    /**
     * Finds the {@link Application} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of applications that match the criteria
     */
    @Override
    public List<Application> findByCriteria(CriteriaForm<Application> criteria);

    /**
     * Deletes the given {@link Application} from the database, including all existing links to other tables.
     * 
     * @param application
     *            the application to delete
     */
    @Override
    public void delete(Application application);

    /**
     * Deletes the given list of {@link Application}s from the database, including all existing links to other tables.
     * 
     * @param applications
     *            the applications to delete
     */
    @Override
    public void delete(List<Application> applications);

    /**
     * Find all applications belonging to the given program.
     * 
     * @param program
     *            the program to search by
     * @return a list of applications
     */
    public List<Application> findApplicationsByProgram(Program program);

    /**
     * Find all applications belonging to any user group the given user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of applications
     */
    public List<Application> findApplicationsByUser(User user);

}
