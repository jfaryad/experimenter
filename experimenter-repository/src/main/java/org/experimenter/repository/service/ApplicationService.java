package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.form.CriteriaForm;

public interface ApplicationService {

    /**
     * Saves the given {@link Application} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param application
     *            the application to save
     */
    public void saveUpdate(Application application);

    /**
     * Finds the {@link Application} with the given id.
     * 
     * @param id
     *            the identifier of the application
     * @return the application with the given id or null, if such an entry doesn't exist in the database.
     */
    public Application findById(Integer id);

    /**
     * Finds the {@link Application} that matches the properties set in the input form.
     * 
     * @param application
     *            a search form with the properties you want to search by
     * @return a list of applications that match the example
     */
    public List<Application> findByExample(Application application);

    /**
     * Finds the {@link Application} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of applications that match the criteria
     */
    public List<Application> findByCriteria(CriteriaForm<Application> criteria);

    /**
     * Deletes the given {@link Application} from the database, including all existing links to other tables.
     * 
     * @param application
     *            the application to delete
     */
    public void delete(Application application);

    /**
     * Deletes the given list of {@link Application}s from the database, including all existing links to other tables.
     * 
     * @param applications
     *            the applications to delete
     */
    public void delete(List<Application> applications);

    public List<Application> findApplicationsByProgram(Program program);

}
