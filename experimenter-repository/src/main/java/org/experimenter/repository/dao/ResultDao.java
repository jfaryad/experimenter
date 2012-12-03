package org.experimenter.repository.dao;

import java.util.Collection;
import java.util.List;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Result;

/**
 * DAO for accessing experiment results in the database
 * 
 * @author jfaryad
 * 
 */
public interface ResultDao extends BaseDao<Result> {

    /**
     * Finds the result uniquely defined by the three arguments.
     * 
     * @param experimentId
     *            id of experiment
     * @param inputId
     *            id of input
     * @param param
     *            the parameter stored in the result
     * @return the result or null
     */
    public Result findResultByExperimentInputParam(Integer experimentId, Integer inputId, String param);

    /**
     * Lists all the results for all the parameters for all the inputs of one experiment.
     * 
     * @param experimentId
     *            id of the experiment
     * @return all results for an experiment (not null list)
     */
    public List<Result> findResultsForExperiment(Experiment experiment);

    /**
     * Returns the list of all available distinct parameters from all results of the given experiments.
     * 
     * @param experimentIds
     *            the experiments to look at results for
     * 
     * @return a list of distinct string values
     */
    List<String> findAllExperimentParameters(Collection<Integer> experimentIds);

    /**
     * Returns the list of all results for the given parameter and experiments
     * 
     * @param parameter
     *            the parameter to search for
     * @param experimentIds
     *            the experiments to look at results for
     * 
     * @return list of Results
     */
    List<Result> findResultsForParam(String parameter, Collection<Integer> experimentIds);
}
