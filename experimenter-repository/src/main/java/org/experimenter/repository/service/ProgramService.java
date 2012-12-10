package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.form.CriteriaForm;

/**
 * The service taking care of {@link Program} related operations.
 * 
 * @author jfaryad
 */
public interface ProgramService extends EntityService<Program> {

    /**
     * Saves the given {@link Program} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param program
     *            the program to save
     */
    @Override
    public void saveUpdate(Program program);

    /**
     * Finds the {@link Program} with the given id.
     * 
     * @param id
     *            the identifier of the program
     * @return the program with the given id or null, if such an entry doesn't exist in the database.
     */
    @Override
    public Program findById(Integer id);

    /**
     * Finds the {@link Program} that matches the properties set in the input form.
     * 
     * @param program
     *            a search form with the properties you want to search by
     * @return a list of programs that match the example
     */
    @Override
    public List<Program> findByExample(Program program);

    /**
     * Finds the {@link Program} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of programs that match the criteria
     */
    @Override
    public List<Program> findByCriteria(CriteriaForm<Program> criteria);

    /**
     * Deletes the given {@link Program} from the database, including all existing links to other tables.
     * 
     * @param program
     *            the program to delete
     */
    @Override
    public void delete(Program program);

    /**
     * Deletes the given list of {@link Program}s from the database, including all existing links to other tables.
     * 
     * @param programs
     *            the programs to delete
     */
    @Override
    public void delete(List<Program> programs);

    /**
     * Find all programs belonging to the given project.
     * 
     * @param project
     *            the project to search by
     * @return a list of programs
     */
    public List<Program> findProgramsByProject(Project project);

    /**
     * Find all programs belonging to any user group the given user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of programs
     */
    public List<Program> findProgramsByUser(User user);

}
