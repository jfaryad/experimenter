package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;

public interface InputSetDao extends BaseDao<InputSet> {

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
