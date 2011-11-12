package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.JunctionDao;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.JunctionForm;
import org.sqlproc.engine.SqlSession;

public class JunctionDaoImpl extends AbstractDao implements JunctionDao {

    private void addToJunctionTable(Entity ent1, Entity ent2, String engineName) {
        JunctionForm form = prepareForm(ent1, ent2);
        SqlSession session = getSqlSession();
        getCrudEngine(engineName).insert(session, form);
    }

    private void removeFromJunctionTable(Entity ent1, Entity ent2, String engineName) {
        JunctionForm form = prepareForm(ent1, ent2);
        SqlSession session = getSqlSession();
        getCrudEngine(engineName).delete(session, form);
    }

    private JunctionForm prepareForm(Entity ent1, Entity ent2) {
        Integer id1 = (ent1 != null) ? ent1.getId() : null;
        Integer id2 = (ent2 != null) ? ent2.getId() : null;
        return new JunctionForm(id1, id2);
    }

    @Override
    public void addUserToUserGroup(User user, UserGroup userGroup) {
        logger.debug(">> addUserToUserGroup(" + user + ", " + userGroup + ")");
        addToJunctionTable(user, userGroup, "ADD_USER_TO_USERGROUP");
        logger.debug("<< addUserToUserGroup");
    }

    @Override
    public void addInputToInputSet(Input input, InputSet inputSet) {
        logger.debug(">> addInputToInputSet(" + input + ", " + inputSet + ")");
        addToJunctionTable(input, inputSet, "ADD_INPUT_TO_INPUT_SET");
        logger.debug("<< addInputToInputSet");
    }

    @Override
    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment) {
        logger.debug(">> addInputSetToExperiment(" + inputSet + ", " + experiment + ")");
        addToJunctionTable(inputSet, experiment, "ADD_INPUT_SET_TO_EXPERIMENT");
        logger.debug("<< addInputSetToExperiment");
    }

    @Override
    public void addInputSetToProject(InputSet inputSet, Project project) {
        logger.debug(">> addInputSetToProject(" + inputSet + ", " + project + ")");
        addToJunctionTable(inputSet, project, "ADD_INPUT_SET_TO_PROJECT");
        logger.debug("<< addInputSetToProject");
    }

    @Override
    public void addConnectionFarmToExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        logger.debug(">> addConnectionFarmToExperiment(" + connectionFarm + ", " + experiment + ")");
        addToJunctionTable(connectionFarm, experiment, "ADD_FARM_TO_EXPERIMENT");
        logger.debug("<< addConnectionFarmToExperiment");
    }

    @Override
    public void removeUserFromUserGroup(User user, UserGroup userGroup) {
        logger.debug(">> removeUserFromUserGroup(" + user + ", " + userGroup + ")");
        removeFromJunctionTable(user, userGroup, "REMOVE_USER_FROM_USERGROUP");
        logger.debug("<< removeUserFromUserGroup");
    }

    @Override
    public void removeInputFromInputSet(Input input, InputSet inputSet) {
        logger.debug(">> removeInputFromInputSet(" + input + ", " + inputSet + ")");
        removeFromJunctionTable(input, inputSet, "REMOVE_INPUT_FROM_INPUT_SET");
        logger.debug("<< removeInputFromInputSet");
    }

    @Override
    public void removeInputSetFromExperiment(InputSet inputSet, Experiment experiment) {
        logger.debug(">> removeInputSetFromExperiment(" + inputSet + ", " + experiment + ")");
        removeFromJunctionTable(inputSet, experiment, "REMOVE_INPUT_SET_FROM_EXPERIMENT");
        logger.debug("<< removeInputSetFromExperiment");
    }

    @Override
    public void removeInputSetFromProject(InputSet inputSet, Project project) {
        logger.debug(">> removeInputSetFromProject(" + inputSet + ", " + project + ")");
        removeFromJunctionTable(inputSet, project, "REMOVE_INPUT_SET_FROM_PROJECT");
        logger.debug("<< removeInputSetFromProject");
    }

    @Override
    public void removeConnectionFarmFromExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        logger.debug(">> removeConnectionFarmFromExperiment(" + connectionFarm + ", " + experiment + ")");
        removeFromJunctionTable(connectionFarm, experiment, "REMOVE_FARM_FROM_EXPERIMENT");
        logger.debug("<< removeConnectionFarmFromExperiment");
    }

}
