package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.CriteriaForm;

public interface ProjectService {

    /**
     * Saves the given {@link Project} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param project
     *            the project to save
     */
    public void saveUpdate(Project project);

    /**
     * Finds the {@link Project} with the given id.
     * 
     * @param id
     *            the identifier of the project
     * @return the project with the given id or null, if such an entry doesn't exist in the database.
     */
    public Project findById(Integer id);

    /**
     * Finds the {@link Project} that matches the properties set in the input form.
     * 
     * @param project
     *            a search form with the properties you want to search by
     * @return a list of projects that match the example
     */
    public List<Project> findByExample(Project project);

    /**
     * Finds the {@link Project} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of projects that match the criteria
     */
    public List<Project> findByCriteria(CriteriaForm<Project> criteria);

    /**
     * Deletes the given {@link Project} from the database, including all existing links to other tables.
     * 
     * @param project
     *            the project to delete
     */
    public void delete(Project project);

    /**
     * Deletes the given list of {@link Project}s from the database, including all existing links to other tables.
     * 
     * @param projects
     *            the projects to delete
     */
    public void delete(List<Project> projects);

    /**
     * Find all projects belonging to the given userGroup.
     * 
     * @param userGroup
     *            the userGroup to search by
     * @return a list of projects
     */
    public List<Project> findProjectsByUserGroup(UserGroup userGroup);

    /**
     * Find all projects belonging to the given problemType.
     * 
     * @param problemType
     *            the problemType to search by
     * @return a list of projects
     */
    public List<Project> findProjectsByProblemType(ProblemType problemType);

    /**
     * Find all projects the inputSet belongs to.
     * 
     * @param inputSet
     *            the inputSet to search by
     * @return a list of projects
     */
    public List<Project> findProjectsByInputSet(InputSet inputSet);

}
