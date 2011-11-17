package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.form.CriteriaForm;

public interface ExperimentService {

    /**
     * Saves the given {@link Experiment} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param experiment
     *            the experiment to save
     */
    public void saveUpdate(Experiment experiment);

    /**
     * Finds the {@link Experiment} with the given id.
     * 
     * @param id
     *            the identifier of the experiment
     * @return the experiment with the given id or null, if such an entry doesn't exist in the database.
     */
    public Experiment findById(Integer id);

    /**
     * Finds the {@link Experiment} that matches the properties set in the input form.
     * 
     * @param experiment
     *            a search form with the properties you want to search by
     * @return a list of experiments that match the example
     */
    public List<Experiment> findByExample(Experiment experiment);

    /**
     * Finds the {@link Experiment} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of experiments that match the criteria
     */
    public List<Experiment> findByCriteria(CriteriaForm<Experiment> criteria);

    /**
     * Deletes the given {@link Experiment} from the database, including all existing links to other tables.
     * 
     * @param experiment
     *            the experiment to delete
     */
    public void delete(Experiment experiment);

    /**
     * Deletes the given list of {@link Experiment}s from the database, including all existing links to other tables.
     * 
     * @param experiments
     *            the experiments to delete
     */
    public void delete(List<Experiment> experiments);

    /**
     * Find all experiments belonging to the given application.
     * 
     * @param application
     *            the application to search by
     * @return a list of experiments
     */
    public List<Experiment> findExperimentsByApplication(Application application);

    /**
     * Find all experiments the connectionFarm belongs to.
     * 
     * @param connectionFarm
     *            the connectionFarm to search by
     * @return a list of experiments
     */
    public List<Experiment> findExperimentsByConnectionFarm(ConnectionFarm connectionFarm);

    /**
     * Find all experiments the inputSet belongs to.
     * 
     * @param inputSet
     *            the inputSet to search by
     * @return a list of experiments
     */
    public List<Experiment> findExperimentsByInputSet(InputSet inputSet);
}
