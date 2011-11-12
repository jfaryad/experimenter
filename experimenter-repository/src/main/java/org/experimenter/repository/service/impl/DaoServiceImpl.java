package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ApplicationDao;
import org.experimenter.repository.dao.ComputerDao;
import org.experimenter.repository.dao.ConnectionDao;
import org.experimenter.repository.dao.ConnectionFarmDao;
import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.dao.InputSetDao;
import org.experimenter.repository.dao.JunctionDao;
import org.experimenter.repository.dao.ProblemTypeDao;
import org.experimenter.repository.dao.ProgramDao;
import org.experimenter.repository.dao.ProjectDao;
import org.experimenter.repository.dao.UserDao;
import org.experimenter.repository.dao.UserGroupDao;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.CriteriaForm;
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
    private JunctionDao junctionDao;

    @Override
    public void saveUpdateUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("The user to save must not be null.");
        if (user.getId() == null)
            userDao.insert(user);
        else
            userDao.update(user);
    }

    @Override
    public User findUserById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return userDao.findById(id);
    }

    @Override
    public List<User> findUserByExample(User user) {
        if (user == null)
            throw new IllegalArgumentException("The example user to search by must not be null.");
        return userDao.findByCriteria(new CriteriaForm<User>(user));
    }

    @Override
    public List<User> findUserByCriteria(CriteriaForm<User> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model user to search by must not be null.");
        return userDao.findByCriteria(criteria);
    }

    @Override
    public void deleteUser(User user) {
        if (user == null || user.getId() == null)
            throw new IllegalArgumentException("The user to delete must be an existing user persisted in the database.");
        junctionDao.removeUserFromUserGroup(user, null);
        userDao.deleteById(user.getId());
    }

    @Override
    public void deleteUsers(List<User> users) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateUserGroup(UserGroup userGroup) {
        if (userGroup == null)
            throw new IllegalArgumentException("The userGroup to save must not be null.");
        if (userGroup.getId() == null)
            userGroupDao.insert(userGroup);
        else
            userGroupDao.update(userGroup);
    }

    @Override
    public UserGroup findUserGroupById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return userGroupDao.findById(id);
    }

    @Override
    public List<UserGroup> findUserGroupByExample(UserGroup userGroup) {
        if (userGroup == null)
            throw new IllegalArgumentException("The example userGroup to search by must not be null.");
        return userGroupDao.findByCriteria(new CriteriaForm<UserGroup>(userGroup));
    }

    @Override
    public List<UserGroup> findUserGroupByCriteria(CriteriaForm<UserGroup> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model userGroup to search by must not be null.");
        return userGroupDao.findByCriteria(criteria);
    }

    @Override
    public void deleteUserGroup(UserGroup userGroup) {
        if (userGroup == null || userGroup.getId() == null)
            throw new IllegalArgumentException("The user to delete must be an existing user persisted in the database.");
        junctionDao.removeUserFromUserGroup(null, userGroup);
        // TODO
        userDao.deleteById(userGroup.getId());
    }

    @Override
    public void deleteUserGroups(List<UserGroup> userGroups) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateApplication(Application application) {
        if (application == null)
            throw new IllegalArgumentException("The application to save must not be null.");
        if (application.getId() == null)
            applicationDao.insert(application);
        else
            applicationDao.update(application);
    }

    @Override
    public Application findApplicationById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return applicationDao.findById(id);
    }

    @Override
    public List<Application> findApplicationByExample(Application application) {
        if (application == null)
            throw new IllegalArgumentException("The example application to search by must not be null.");
        return applicationDao.findByCriteria(new CriteriaForm<Application>(application));
    }

    @Override
    public List<Application> findApplicationByCriteria(CriteriaForm<Application> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model application to search by must not be null.");
        return applicationDao.findByCriteria(criteria);
    }

    @Override
    public void deleteApplication(Application application) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteApplications(List<Application> applications) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateComputer(Computer computer) {
        if (computer == null)
            throw new IllegalArgumentException("The computer to save must not be null.");
        if (computer.getId() == null)
            computerDao.insert(computer);
        else
            computerDao.update(computer);
    }

    @Override
    public Computer findComputerById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return computerDao.findById(id);
    }

    @Override
    public List<Computer> findComputerByExample(Computer computer) {
        if (computer == null)
            throw new IllegalArgumentException("The example computer to search by must not be null.");
        return computerDao.findByCriteria(new CriteriaForm<Computer>(computer));
    }

    @Override
    public List<Computer> findComputerByCriteria(CriteriaForm<Computer> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model computer to search by must not be null.");
        return computerDao.findByCriteria(criteria);
    }

    @Override
    public void deleteComputer(Computer computer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteComputers(List<Computer> computers) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateConnection(Connection connection) {
        if (connection == null)
            throw new IllegalArgumentException("The connection to save must not be null.");
        if (connection.getId() == null)
            connectionDao.insert(connection);
        else
            connectionDao.update(connection);
    }

    @Override
    public Connection findConnectionById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return connectionDao.findById(id);
    }

    @Override
    public List<Connection> findConnectionByExample(Connection connection) {
        if (connection == null)
            throw new IllegalArgumentException("The example connection to search by must not be null.");
        return connectionDao.findByCriteria(new CriteriaForm<Connection>(connection));
    }

    @Override
    public List<Connection> findConnectionByCriteria(CriteriaForm<Connection> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model connection to search by must not be null.");
        return connectionDao.findByCriteria(criteria);
    }

    @Override
    public void deleteConnection(Connection connection) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteConnections(List<Connection> connections) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateConnectionFarm(ConnectionFarm connectionFarm) {
        if (connectionFarm == null)
            throw new IllegalArgumentException("The connectionFarm to save must not be null.");
        if (connectionFarm.getId() == null)
            connectionFarmDao.insert(connectionFarm);
        else
            connectionFarmDao.update(connectionFarm);
    }

    @Override
    public ConnectionFarm findConnectionFarmById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return connectionFarmDao.findById(id);
    }

    @Override
    public List<ConnectionFarm> findConnectionFarmByExample(ConnectionFarm connectionFarm) {
        if (connectionFarm == null)
            throw new IllegalArgumentException("The example connectionFarm to search by must not be null.");
        return connectionFarmDao.findByCriteria(new CriteriaForm<ConnectionFarm>(connectionFarm));
    }

    @Override
    public List<ConnectionFarm> findConnectionFarmByCriteria(CriteriaForm<ConnectionFarm> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException(
                    "The criteria or the model connectionFarm to search by must not be null.");
        return connectionFarmDao.findByCriteria(criteria);
    }

    @Override
    public void deleteConnectionFarm(ConnectionFarm connectionFarm) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteConnectionFarms(List<ConnectionFarm> connectionFarms) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateProject(Project project) {
        if (project == null)
            throw new IllegalArgumentException("The project to save must not be null.");
        if (project.getId() == null)
            projectDao.insert(project);
        else
            projectDao.update(project);
    }

    @Override
    public Project findProjectById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return projectDao.findById(id);
    }

    @Override
    public List<Project> findProjectByExample(Project project) {
        if (project == null)
            throw new IllegalArgumentException("The example project to search by must not be null.");
        return projectDao.findByCriteria(new CriteriaForm<Project>(project));
    }

    @Override
    public List<Project> findProjectByCriteria(CriteriaForm<Project> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model project to search by must not be null.");
        return projectDao.findByCriteria(criteria);
    }

    @Override
    public void deleteProject(Project project) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteProjects(List<Project> projects) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateProgram(Program program) {
        if (program == null)
            throw new IllegalArgumentException("The program to save must not be null.");
        if (program.getId() == null)
            programDao.insert(program);
        else
            programDao.update(program);
    }

    @Override
    public Program findProgramById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return programDao.findById(id);
    }

    @Override
    public List<Program> findProgramByExample(Program program) {
        if (program == null)
            throw new IllegalArgumentException("The example program to search by must not be null.");
        return programDao.findByCriteria(new CriteriaForm<Program>(program));
    }

    @Override
    public List<Program> findProgramByCriteria(CriteriaForm<Program> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model program to search by must not be null.");
        return programDao.findByCriteria(criteria);
    }

    @Override
    public void deleteProgram(Program program) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deletePrograms(List<Program> programs) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateExperiment(Experiment experiment) {
        if (experiment == null)
            throw new IllegalArgumentException("The experiment to save must not be null.");
        if (experiment.getId() == null)
            experimentDao.insert(experiment);
        else
            experimentDao.update(experiment);
    }

    @Override
    public Experiment findExperimentById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return experimentDao.findById(id);
    }

    @Override
    public List<Experiment> findExperimentByExample(Experiment experiment) {
        if (experiment == null)
            throw new IllegalArgumentException("The example experiment to search by must not be null.");
        return experimentDao.findByCriteria(new CriteriaForm<Experiment>(experiment));
    }

    @Override
    public List<Experiment> findExperimentByCriteria(CriteriaForm<Experiment> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model experiment to search by must not be null.");
        return experimentDao.findByCriteria(criteria);
    }

    @Override
    public void deleteExperiment(Experiment experiment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteExperiments(List<Experiment> experiments) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateInput(Input input) {
        if (input == null)
            throw new IllegalArgumentException("The input to save must not be null.");
        if (input.getId() == null)
            inputDao.insert(input);
        else
            inputDao.update(input);
    }

    @Override
    public Input findInputById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return inputDao.findById(id);
    }

    @Override
    public List<Input> findInputByExample(Input input) {
        if (input == null)
            throw new IllegalArgumentException("The example input to search by must not be null.");
        return inputDao.findByCriteria(new CriteriaForm<Input>(input));
    }

    @Override
    public List<Input> findInputByCriteria(CriteriaForm<Input> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model input to search by must not be null.");
        return inputDao.findByCriteria(criteria);
    }

    @Override
    public void deleteInput(Input input) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteInputs(List<Input> inputs) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateInputSet(InputSet inputSet) {
        if (inputSet == null)
            throw new IllegalArgumentException("The inputSet to save must not be null.");
        if (inputSet.getId() == null)
            inputSetDao.insert(inputSet);
        else
            inputSetDao.update(inputSet);
    }

    @Override
    public InputSet findInputSetById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return inputSetDao.findById(id);
    }

    @Override
    public List<InputSet> findInputSetByExample(InputSet inputSet) {
        if (inputSet == null)
            throw new IllegalArgumentException("The example inputSet to search by must not be null.");
        return inputSetDao.findByCriteria(new CriteriaForm<InputSet>(inputSet));
    }

    @Override
    public List<InputSet> findInputSetByCriteria(CriteriaForm<InputSet> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model inputSet to search by must not be null.");
        return inputSetDao.findByCriteria(criteria);
    }

    @Override
    public void deleteInputSet(InputSet inputSet) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteInputSets(List<InputSet> inputSets) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveUpdateProblemType(ProblemType problemType) {
        if (problemType == null)
            throw new IllegalArgumentException("The problemType to save must not be null.");
        if (problemType.getId() == null)
            problemTypeDao.insert(problemType);
        else
            problemTypeDao.update(problemType);
    }

    @Override
    public ProblemType findProblemTypeById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return problemTypeDao.findById(id);
    }

    @Override
    public List<ProblemType> findProblemTypeByExample(ProblemType problemType) {
        if (problemType == null)
            throw new IllegalArgumentException("The example problemType to search by must not be null.");
        return problemTypeDao.findByCriteria(new CriteriaForm<ProblemType>(problemType));
    }

    @Override
    public List<ProblemType> findProblemTypeByCriteria(CriteriaForm<ProblemType> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model problemType to search by must not be null.");
        return problemTypeDao.findByCriteria(criteria);
    }

    @Override
    public void deleteProblemType(ProblemType problemType) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteProblemTypes(List<ProblemType> problemTypes) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addUserToUserGroup(User user, UserGroup userGroup) {
        junctionDao.addUserToUserGroup(user, userGroup);
    }

    @Override
    public void addInputToInputSet(Input input, InputSet inputSet) {
        junctionDao.addInputToInputSet(input, inputSet);
    }

    @Override
    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment) {
        junctionDao.addInputSetToExperiment(inputSet, experiment);
    }

    @Override
    public void addInputSetToProject(InputSet inputSet, Project project) {
        junctionDao.addInputSetToProject(inputSet, project);
    }

    @Override
    public void addConnectionFarmToExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        junctionDao.addConnectionFarmToExperiment(connectionFarm, experiment);
    }

    @Override
    public void removeUserFromUserGroup(User user, UserGroup userGroup) {
        junctionDao.removeUserFromUserGroup(user, userGroup);
    }

    @Override
    public void removeInputFromInputSet(Input input, InputSet inputSet) {
        junctionDao.removeInputFromInputSet(input, inputSet);
    }

    @Override
    public void removeInputSetFromExperiment(InputSet inputSet, Experiment experiment) {
        junctionDao.removeInputSetFromExperiment(inputSet, experiment);
    }

    @Override
    public void removeInputSetFromProject(InputSet inputSet, Project project) {
        junctionDao.removeInputSetFromProject(inputSet, project);
    }

    @Override
    public void removeConnectionFarmFromExperiment(ConnectionFarm connectionFarm, Experiment experiment) {
        junctionDao.removeConnectionFarmFromExperiment(connectionFarm, experiment);
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

    @Required
    public void setJunctionDao(JunctionDao junctionDao) {
        this.junctionDao = junctionDao;
    }

}
