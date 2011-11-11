package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.JunctionDao;
import org.experimenter.repository.form.JunctionForm;
import org.experimenter.repository.model.ConnectionFarm;
import org.experimenter.repository.model.Experiment;
import org.experimenter.repository.model.Input;
import org.experimenter.repository.model.InputSet;
import org.experimenter.repository.model.Project;
import org.experimenter.repository.model.User;
import org.experimenter.repository.model.UserGroup;
import org.sqlproc.engine.SqlSession;

public class JunctionDaoImpl extends AbstractDao implements JunctionDao {

    private void addToJunctionTable(JunctionForm form, String engineName) {
        SqlSession session = getSqlSession();
        getCrudEngine(engineName).insert(session, form);
    }

    private void removeFromJunctionTable(JunctionForm form, String engineName) {
        SqlSession session = getSqlSession();
        getCrudEngine(engineName).delete(session, form);
    }

    @Override
    public void addUserToUserGroup(User user, UserGroup userGroup) {
        logger.debug(">> addUserToUserGroup(" + user + ", " + userGroup + ")");
        Integer id1 = (user != null) ? user.getUserId() : null;
        Integer id2 = (userGroup != null) ? userGroup.getUserGroupId() : null;
        addToJunctionTable(new JunctionForm(id1, id2), "ADD_USER_TO_USERGROUP");
        logger.debug("<< addUserToUserGroup");
    }

    @Override
    public void addInputToInputSet(Input input, InputSet inputSet) {
        logger.debug(">> addInputToInputSet(" + input + ", " + inputSet + ")");
        Integer id1 = (input != null) ? input.getInputId() : null;
        Integer id2 = (inputSet != null) ? inputSet.getInputSetId() : null;
        addToJunctionTable(new JunctionForm(id1, id2), "ADD_INPUT_TO_INPUT_SET");
        logger.debug("<< addInputToInputSet");
    }

    @Override
    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment) {
        logger.debug(">> addInputSetToExperiment(" + inputSet + ", " + experiment + ")");
        Integer id1 = (inputSet != null) ? inputSet.getInputSetId() : null;
        Integer id2 = (experiment != null) ? experiment.getExperimentId() : null;
        addToJunctionTable(new JunctionForm(id1, id2), "ADD_INPUT_SET_TO_EXPERIMENT");
        logger.debug("<< addInputSetToExperiment");
    }

    @Override
    public void addInputSetToProject(InputSet inputSet, Project project) {
        logger.debug(">> addInputSetToProject(" + inputSet + ", " + project + ")");
        Integer id1 = (inputSet != null) ? inputSet.getInputSetId() : null;
        Integer id2 = (project != null) ? project.getProjectId() : null;
        addToJunctionTable(new JunctionForm(id1, id2), "ADD_INPUT_SET_TO_PROJECT");
        logger.debug("<< addInputSetToProject");
    }

    @Override
    public void addConnectionFarmToExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        logger.debug(">> addConnectionFarmToExperiment(" + connectionFarm + ", " + experiment + ")");
        Integer id1 = (connectionFarm != null) ? connectionFarm.getConnectionFarmId() : null;
        Integer id2 = (experiment != null) ? experiment.getExperimentId() : null;
        addToJunctionTable(new JunctionForm(id1, id2), "ADD_FARM_TO_EXPERIMENT");
        logger.debug("<< addConnectionFarmToExperiment");
    }

    @Override
    public void removeUserFromUserGroup(User user, UserGroup userGroup) {
        logger.debug(">> removeUserFromUserGroup(" + user + ", " + userGroup + ")");
        Integer id1 = (user != null) ? user.getUserId() : null;
        Integer id2 = (userGroup != null) ? userGroup.getUserGroupId() : null;
        removeFromJunctionTable(new JunctionForm(id1, id2), "REMOVE_USER_FROM_USERGROUP");
        logger.debug("<< removeUserFromUserGroup");
    }

    @Override
    public void removeInputFromInputSet(Input input, InputSet inputSet) {
        logger.debug(">> removeInputFromInputSet(" + input + ", " + inputSet + ")");
        Integer id1 = (input != null) ? input.getInputId() : null;
        Integer id2 = (inputSet != null) ? inputSet.getInputSetId() : null;
        removeFromJunctionTable(new JunctionForm(id1, id2), "REMOVE_INPUT_FROM_INPUT_SET");
        logger.debug("<< removeInputFromInputSet");
    }

    @Override
    public void removeInputSetFromExperiment(InputSet inputSet, Experiment experiment) {
        logger.debug(">> removeInputSetFromExperiment(" + inputSet + ", " + experiment + ")");
        Integer id1 = (inputSet != null) ? inputSet.getInputSetId() : null;
        Integer id2 = (experiment != null) ? experiment.getExperimentId() : null;
        removeFromJunctionTable(new JunctionForm(id1, id2), "REMOVE_INPUT_SET_FROM_EXPERIMENT");
        logger.debug("<< removeInputSetFromExperiment");
    }

    @Override
    public void removeInputSetFromProject(InputSet inputSet, Project project) {
        logger.debug(">> removeInputSetFromProject(" + inputSet + ", " + project + ")");
        Integer id1 = (inputSet != null) ? inputSet.getInputSetId() : null;
        Integer id2 = (project != null) ? project.getProjectId() : null;
        removeFromJunctionTable(new JunctionForm(id1, id2), "REMOVE_INPUT_SET_FROM_PROJECT");
        logger.debug("<< removeInputSetFromProject");
    }

    @Override
    public void removeConnectionFarmFromExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        logger.debug(">> removeConnectionFarmFromExperiment(" + connectionFarm + ", " + experiment + ")");
        Integer id1 = (connectionFarm != null) ? connectionFarm.getConnectionFarmId() : null;
        Integer id2 = (experiment != null) ? experiment.getExperimentId() : null;
        removeFromJunctionTable(new JunctionForm(id1, id2), "REMOVE_FARM_FROM_EXPERIMENT");
        logger.debug("<< removeConnectionFarmFromExperiment");
    }

}
