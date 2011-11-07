package org.experimenter.repository.service.impl;

import org.experimenter.repository.dao.ApplicationDao;
import org.experimenter.repository.dao.ComputerDao;
import org.experimenter.repository.dao.ConnectionDao;
import org.experimenter.repository.dao.ConnectionFarmDao;
import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.dao.InputSetDao;
import org.experimenter.repository.dao.ProblemTypeDao;
import org.experimenter.repository.dao.ProgramDao;
import org.experimenter.repository.dao.ProjectDao;
import org.experimenter.repository.dao.UserDao;
import org.experimenter.repository.dao.UserGroupDao;
import org.experimenter.repository.model.ConnectionFarm;
import org.experimenter.repository.model.Experiment;
import org.experimenter.repository.model.Input;
import org.experimenter.repository.model.InputSet;
import org.experimenter.repository.model.Project;
import org.experimenter.repository.model.User;
import org.experimenter.repository.model.UserGroup;
import org.experimenter.repository.service.DaoService;
import org.springframework.beans.factory.annotation.Required;

public class DaoServiceImpl implements DaoService {

    private UserDao userDao;
    private UserGroupDao userGroupDao;
    private ApplicationDao applicationDao;
    private ComputerDao computerDao;
    private ConnectionDao connectionDao;
    private ConnectionFarmDao connectionFarmDao;
    private ExperimentDao experimentDao;
    private ProjectDao projectDao;
    private ProgramDao programDao;
    private InputDao inputDao;
    private InputSetDao inputSetDao;
    private ProblemTypeDao problemTypeDao;

    @Override
    public void addUserToUserGroup(User user, UserGroup userGroup) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addInputSetToProject(InputSet inputSet, Project project) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addInputToInputSet(Input input, InputSet inputSet) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addFarmToExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        // TODO Auto-generated method stub

    }

    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Required
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }

    @Required
    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Required
    public void setComputerDao(ComputerDao computerDao) {
        this.computerDao = computerDao;
    }

    @Required
    public void setConnectionDao(ConnectionDao connectionDao) {
        this.connectionDao = connectionDao;
    }

    @Required
    public void setConnectionFarmDao(ConnectionFarmDao connectionFarmDao) {
        this.connectionFarmDao = connectionFarmDao;
    }

    @Required
    public void setExperimentDao(ExperimentDao experimentDao) {
        this.experimentDao = experimentDao;
    }

    @Required
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Required
    public void setProgramDao(ProgramDao programDao) {
        this.programDao = programDao;
    }

    @Required
    public void setInputDao(InputDao inputDao) {
        this.inputDao = inputDao;
    }

    @Required
    public void setInputSetDao(InputSetDao inputSetDao) {
        this.inputSetDao = inputSetDao;
    }

    @Required
    public void setProblemTypeDao(ProblemTypeDao problemTypeDao) {
        this.problemTypeDao = problemTypeDao;
    }

}
