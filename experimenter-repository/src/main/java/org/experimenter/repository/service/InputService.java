package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.form.CriteriaForm;

public interface InputService {

    /**
     * Saves the given {@link Input} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param input
     *            the input to save
     */
    public void saveUpdate(Input input);

    /**
     * Finds the {@link Input} with the given id.
     * 
     * @param id
     *            the identifier of the input
     * @return the input with the given id or null, if such an entry doesn't exist in the database.
     */
    public Input findById(Integer id);

    /**
     * Finds the {@link Input} that matches the properties set in the input form.
     * 
     * @param input
     *            a search form with the properties you want to search by
     * @return a list of inputs that match the example
     */
    public List<Input> findByExample(Input input);

    /**
     * Finds the {@link Input} that matches the properties set in the input form. In addition, it is possible to specify
     * the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of inputs that match the criteria
     */
    public List<Input> findByCriteria(CriteriaForm<Input> criteria);

    /**
     * Deletes the given {@link Input} from the database, including all existing links to other tables.
     * 
     * @param input
     *            the input to delete
     */
    public void delete(Input input);

    /**
     * Deletes the given list of {@link Input}s from the database, including all existing links to other tables.
     * 
     * @param inputs
     *            the inputs to delete
     */
    public void delete(List<Input> inputs);

    /**
     * Adds a {@link Input} to a {@link InputSet}
     * 
     * @param input
     *            the user to input
     * @param inputSet
     *            the inputSet to add to
     */
    public void addInputToInputSet(Input input, InputSet inputSet);

    /**
     * Removes a {@link Input} from a {@link InputSet}
     * 
     * @param input
     *            the input to remove
     * @param inputSet
     *            the inputSet to remove from
     */
    public void removeInputFromInputSet(Input input, InputSet inputSet);

    /**
     * Find all inputs belonging to the given problemType.
     * 
     * @param problemType
     *            the problemType to search by
     * @return a list of inputs
     */
    public List<Input> findInputsByProblemType(ProblemType problemType);

    /**
     * Find all inputs belonging to the given inputSet.
     * 
     * @param inputSet
     *            the inputSet to search by
     * @return a list of inputs
     */
    public List<Input> findInputsByInputSet(InputSet inputSet);

}
