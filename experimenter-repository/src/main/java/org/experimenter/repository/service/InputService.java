package org.experimenter.repository.service;

import java.io.File;
import java.util.List;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.form.CriteriaForm;

public interface InputService extends EntityService<Input> {

    /**
     * Saves the given {@link Input} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param input
     *            the input to save
     */
    @Override
    public void saveUpdate(Input input);

    /**
     * Saves the given {@link Input} to the database and copies the given data file to the storage.<br>
     * If copying fails, the database operation will be rolled back.
     * 
     * @param input
     *            the input to save
     * @param tmpDataFile
     *            the input data in a temporary file
     */
    public void saveWithData(Input input, File tmpDataFile);

    /**
     * Saves the {@link Input}s specified in the given zip archive to the database and copies the given data files to
     * the storage.<br>
     * If copying fails, the database operation will be rolled back.
     * 
     * @param inputSet
     *            the inputSet to add the new inputs to
     * @param tmpDataFile
     *            the input data in a temporary zip file
     */
    public void saveFromZipArchive(InputSet inputSet, File tmpDataFile);

    /**
     * Finds the {@link Input} with the given id.
     * 
     * @param id
     *            the identifier of the input
     * @return the input with the given id or null, if such an entry doesn't exist in the database.
     */
    @Override
    public Input findById(Integer id);

    /**
     * Finds the {@link Input} that matches the properties set in the input form.
     * 
     * @param input
     *            a search form with the properties you want to search by
     * @return a list of inputs that match the example
     */
    @Override
    public List<Input> findByExample(Input input);

    /**
     * Finds the {@link Input} that matches the properties set in the input form. In addition, it is possible to specify
     * the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of inputs that match the criteria
     */
    @Override
    public List<Input> findByCriteria(CriteriaForm<Input> criteria);

    /**
     * Deletes the given {@link Input} from the database, including all existing links to other tables.
     * 
     * @param input
     *            the input to delete
     */
    @Override
    public void delete(Input input);

    /**
     * Deletes the given list of {@link Input}s from the database, including all existing links to other tables.
     * 
     * @param inputs
     *            the inputs to delete
     */
    @Override
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

    /**
     * Finds the inputs belonging to any of the input sets assigned to the given experiment
     * 
     * @param experiment
     *            the experiment to retrieve inputs for
     * @return list of inputs
     */
    public List<Input> findInputsByExperiment(Experiment experiment);

}
