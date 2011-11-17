package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.form.CriteriaForm;

public interface InputSetService {

    /**
     * Saves the given {@link InputSet} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param inputSet
     *            the inputSet to save
     */
    public void saveUpdate(InputSet inputSet);

    /**
     * Finds the {@link InputSet} with the given id.
     * 
     * @param id
     *            the identifier of the inputSet
     * @return the inputSet with the given id or null, if such an entry doesn't exist in the database.
     */
    public InputSet findById(Integer id);

    /**
     * Finds the {@link InputSet} that matches the properties set in the input form.
     * 
     * @param inputSet
     *            a search form with the properties you want to search by
     * @return a list of inputSets that match the example
     */
    public List<InputSet> findByExample(InputSet inputSet);

    /**
     * Finds the {@link InputSet} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of inputSets that match the criteria
     */
    public List<InputSet> findByCriteria(CriteriaForm<InputSet> criteria);

    /**
     * Deletes the given {@link InputSet} from the database, including all existing links to other tables.
     * 
     * @param inputSet
     *            the inputSet to delete
     */
    public void delete(InputSet inputSet);

    /**
     * Deletes the given list of {@link InputSet}s from the database, including all existing links to other tables.
     * 
     * @param inputSets
     *            the inputSets to delete
     */
    public void delete(List<InputSet> inputSets);

    /**
     * Adds a {@link InputSet} to a {@link Experiment}
     * 
     * @param inputSet
     *            the inputSet to add
     * @param experiment
     *            the experiment to add to
     */
    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment);

    /**
     * Adds a {@link InputSet} to a {@link Project}
     * 
     * @param inputSet
     *            the inputSet to add
     * @param project
     *            the project to add to
     */
    public void addInputSetToProject(InputSet inputSet, Project project);

    /**
     * Removes a {@link InputSet} from a {@link Experiment}
     * 
     * @param inputSet
     *            the inputSet to remove
     * @param experiment
     *            the experiment to remove from
     */
    public void removeInputSetFromExperiment(InputSet inputSet, Experiment experiment);

    /**
     * Removes a {@link InputSet} from a {@link Project}
     * 
     * @param inputSet
     *            the inputSet to remove
     * @param project
     *            the project to remove from
     */
    public void removeInputSetFromProject(InputSet inputSet, Project project);

    /**
     * Find all inputSets belonging to the given problemType.
     * 
     * @param problemType
     *            the problemType to search by
     * @return a list of inputSets
     */
    public List<InputSet> findInputSetsByProblemType(ProblemType problemType);

    /**
     * Find all inputSets belonging to the given experiment.
     * 
     * @param experiment
     *            the experiment to search by
     * @return a list of inputSets
     */
    public List<InputSet> findInputSetsByExperiment(Experiment experiment);

    /**
     * Find all inputSets the input belongs to.
     * 
     * @param input
     *            the input to search by
     * @return a list of inputSets
     */
    public List<InputSet> findInputSetsByInput(Input input);

    /**
     * Find all inputSets belonging to the given project.
     * 
     * @param project
     *            the project to search by
     * @return a list of inputSets
     */
    public List<InputSet> findInputSetsByProject(Project project);

}
