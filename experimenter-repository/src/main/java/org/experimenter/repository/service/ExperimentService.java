package org.experimenter.repository.service;

import java.util.List;
import java.util.Map;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.form.CriteriaForm;

/**
 * The service taking care of {@link Experiment} related operations.
 * 
 * @author jfaryad
 */
public interface ExperimentService extends EntityService<Experiment> {

    /**
     * Saves the given {@link Experiment} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param experiment
     *            the experiment to save
     */
    @Override
    public void saveUpdate(Experiment experiment);

    /**
     * Finds the {@link Experiment} with the given id.
     * 
     * @param id
     *            the identifier of the experiment
     * @return the experiment with the given id or null, if such an entry doesn't exist in the database.
     */
    @Override
    public Experiment findById(Integer id);

    /**
     * Finds the {@link Experiment} that matches the properties set in the input form.
     * 
     * @param experiment
     *            a search form with the properties you want to search by
     * @return a list of experiments that match the example
     */
    @Override
    public List<Experiment> findByExample(Experiment experiment);

    /**
     * Finds the {@link Experiment} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of experiments that match the criteria
     */
    @Override
    public List<Experiment> findByCriteria(CriteriaForm<Experiment> criteria);

    /**
     * Deletes the given {@link Experiment} from the database, including all existing links to other tables.
     * 
     * @param experiment
     *            the experiment to delete
     */
    @Override
    public void delete(Experiment experiment);

    /**
     * Deletes the given list of {@link Experiment}s from the database, including all existing links to other tables.
     * 
     * @param experiments
     *            the experiments to delete
     */
    @Override
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

    /**
     * Find all experiments belonging to any user group the given user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of experiments
     */
    public List<Experiment> findExperimentsByUser(User user);

    /**
     * Finds all experiments that have a cron expression set, but no result yet.
     * 
     * @return a list of experiments
     */
    public List<Experiment> findScheduledExperiments();

    /**
     * Marks the experiment as running.
     * <p>
     * Should be synchronized on the same object as all other methods that manipulate the experiment status.
     * 
     * @param experiment
     *            the experiment to mark running
     * @return true if the experiment has been marked, false if it already is running or finished
     */
    public boolean setExperimentStarted(Experiment experiment);

    /**
     * Returns the status of the given experiment.
     * <p>
     * Should be synchronized on the same object as all other methods that manipulate the experiment status.
     * 
     * @param experiment
     *            the experiment to get the status for
     * @return {@link Experiment.Status}
     */
    public Experiment.Status getExperimentStatus(Experiment experiment);

    /**
     * Returns the status of the given experiments.
     * <p>
     * Should be synchronized on the same object as all other methods that manipulate the experiment status.
     * 
     * @param experiments
     *            the experiments to get the status for
     * @return a map of entries (experiment id, {@link Experiment.Status})
     */
    public Map<Integer, Experiment.Status> getExperimentListStatus(List<Experiment> experiments);

    /**
     * Marks the experiment as finished.
     * <p>
     * Should be synchronized on the same object as all other methods that manipulate the experiment status.
     * 
     * @param experiment
     *            the experiment to mark finished
     * @return true if the experiment has been marked, false if it is not running or has already finished.
     */
    public boolean setExperimentFinished(Experiment experiment);

    /**
     * Like {@link #findExperimentsByUser(User)} but returns only experiments that are already finished.
     * 
     * @param user
     *            to user to filter by
     * @return a list of experiments
     */
    public List<Experiment> findFinishedExperimentsByUser(User user);
}
