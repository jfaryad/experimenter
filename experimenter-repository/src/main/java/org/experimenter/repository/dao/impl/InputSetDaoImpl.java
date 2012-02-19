package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.InputSetDao;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;

public class InputSetDaoImpl extends AbstractBaseDaoImpl<InputSet> implements InputSetDao {

    @Override
    public Class<InputSet> getEntityClass() {
        return InputSet.class;
    }

    @Override
    public void removeFromAssociations(InputSet inputSet) {
        inputSet.getProblem().getInputSets().remove(inputSet);
        for (Input input : inputSet.getInputs())
            input.getInputSets().remove(inputSet);
        for (Experiment experiment : inputSet.getExperiments())
            experiment.getInputSets().remove(inputSet);
        for (Project project : inputSet.getProjects())
            project.getInputSets().remove(inputSet);
    }

    // @Override
    // public List<InputSet> findInputSetsByExperiment(Experiment experiment) {
    // logger.debug(">> findInputSetsByExperiment: " + experiment);
    // SqlSession session = getSqlSession();
    // String engineName = "FIND_INPUT_SET_BY_EXPERIMENT";
    // List<InputSet> inputSets = getQueryEngine(engineName).query(session, getEntityClass(),
    // new SimpleForm(experiment.getId()));
    // logger.debug("<< findInputSetsByExperiment: number of inputSets found:" + inputSets.size());
    // return inputSets;
    // }
    //
    // @Override
    // public List<InputSet> findInputSetsByInput(Input input) {
    // logger.debug(">> findInputSetsByInput: " + input);
    // SqlSession session = getSqlSession();
    // String engineName = "FIND_INPUT_SET_BY_INPUT";
    // List<InputSet> inputSets = getQueryEngine(engineName).query(session, getEntityClass(),
    // new SimpleForm(input.getId()));
    // logger.debug("<< findInputSetsByInput: number of inputSets found:" + inputSets.size());
    // return inputSets;
    // }
    //
    // @Override
    // public List<InputSet> findInputSetsByProject(Project project) {
    // logger.debug(">> findInputSetsByProject: " + project);
    // SqlSession session = getSqlSession();
    // String engineName = "FIND_INPUT_SET_BY_PROJECT";
    // List<InputSet> inputSets = getQueryEngine(engineName).query(session, getEntityClass(),
    // new SimpleForm(project.getId()));
    // logger.debug("<< findInputSetsByProject: number of inputSets found:" + inputSets.size());
    // return inputSets;
    // }

}
