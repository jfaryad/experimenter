package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.InputSetDao;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;
import org.sqlproc.engine.SqlSession;

public class InputSetDaoImpl extends AbstractBaseDaoImpl<InputSet> implements InputSetDao {

    @Override
    public Class<InputSet> getEntityClass() {
        return InputSet.class;
    }

    @Override
    public String getTableName() {
        return Constants.INPUT_SET;
    }

    @Override
    public List<InputSet> findInputSetsByExperiment(Experiment experiment) {
        logger.debug(">> findInputSetsByExperiment: " + experiment);
        SqlSession session = getSqlSession();
        String engineName = "FIND_INPUT_SET_BY_EXPERIMENT";
        List<InputSet> inputSets = getQueryEngine(engineName).query(session, getEntityClass(), experiment.getId());
        logger.debug("<< findInputSetsByExperiment: number of inputSets found:" + inputSets.size());
        return inputSets;
    }

    @Override
    public List<InputSet> findInputSetsByInput(Input input) {
        logger.debug(">> findInputSetsByInput: " + input);
        SqlSession session = getSqlSession();
        String engineName = "FIND_INPUT_SET_BY_INPUT";
        List<InputSet> inputSets = getQueryEngine(engineName).query(session, getEntityClass(), input.getId());
        logger.debug("<< findInputSetsByInput: number of inputSets found:" + inputSets.size());
        return inputSets;
    }

    @Override
    public List<InputSet> findInputSetsByProject(Project project) {
        logger.debug(">> findInputSetsByProject: " + project);
        SqlSession session = getSqlSession();
        String engineName = "FIND_INPUT_SET_BY_PROJECT";
        List<InputSet> inputSets = getQueryEngine(engineName).query(session, getEntityClass(), project.getId());
        logger.debug("<< findInputSetsByProject: number of inputSets found:" + inputSets.size());
        return inputSets;
    }

}
