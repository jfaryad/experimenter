package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.CriteriaForm;

public interface ConnectionFarmService {

    /**
     * Saves the given {@link ConnectionFarm} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param connectionFarm
     *            the connectionFarm to save
     */
    public void saveUpdate(ConnectionFarm connectionFarm);

    /**
     * Finds the {@link ConnectionFarm} with the given id.
     * 
     * @param id
     *            the identifier of the connectionFarm
     * @return the connectionFarm with the given id or null, if such an entry doesn't exist in the database.
     */
    public ConnectionFarm findById(Integer id);

    /**
     * Finds the {@link ConnectionFarm} that matches the properties set in the input form.
     * 
     * @param connectionFarm
     *            a search form with the properties you want to search by
     * @return a list of connectionFarms that match the example
     */
    public List<ConnectionFarm> findByExample(ConnectionFarm connectionFarm);

    /**
     * Finds the {@link ConnectionFarm} that matches the properties set in the input form. In addition, it is possible
     * to specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of connectionFarms that match the criteria
     */
    public List<ConnectionFarm> findByCriteria(CriteriaForm<ConnectionFarm> criteria);

    /**
     * Deletes the given {@link ConnectionFarm} from the database, including all existing links to other tables.
     * 
     * @param connectionFarm
     *            the connectionFarm to delete
     */
    public void delete(ConnectionFarm connectionFarm);

    /**
     * Deletes the given list of {@link ConnectionFarm}s from the database, including all existing links to other
     * tables.
     * 
     * @param connectionFarms
     *            the connectionFarms to delete
     */
    public void delete(List<ConnectionFarm> connectionFarms);

    /**
     * Adds a {@link ConnectionFarm} to a {@link Experiment}
     * 
     * @param connectionFarm
     *            the connectionFarm to add
     * @param experiment
     *            the experiment to add to
     */
    public void addConnectionFarmToExperiment(ConnectionFarm connectionFarm, Experiment experiment);

    /**
     * Removes a {@link ConnectionFarm} from a {@link Experiment}
     * 
     * @param connectionFarm
     *            the connectionFarm to remove
     * @param experiment
     *            the experiment to remove from
     */
    public void removeConnectionFarmFromExperiment(ConnectionFarm connectionFarm, Experiment experiment);

    /**
     * Find all connectionFarms belonging to the given userGroup.
     * 
     * @param userGroup
     *            the userGroup to search by
     * @return a list of connectionFarms
     */
    public List<ConnectionFarm> findConnectionFarmsByUserGroup(UserGroup userGroup);

    /**
     * Find all conectionFarms belonging to the given experiment.
     * 
     * @param experiment
     *            the experiment to search by
     * @return a list of conectionFarms
     */
    public List<ConnectionFarm> findConnectionFarmsByExperiment(Experiment experiment);
}
