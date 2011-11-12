package org.experimenter.repository.service;

import java.util.List;

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

public interface DaoService {

    /**
     * Saves the given {@link User} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param user
     *            the user to save
     */
    public void saveUpdateUser(User user);

    /**
     * Finds the {@link User} with the given id.
     * 
     * @param id
     *            the identifier of the user
     * @return the user with the given id or null, if such an entry doesn't exist in the database.
     */
    public User findUserById(Integer id);

    /**
     * Finds the {@link User} that matches the properties set in the input form.
     * 
     * @param user
     *            a search form with the properties you want to search by
     * @return a list of users that match the example
     */
    public List<User> findUserByExample(User user);

    /**
     * Finds the {@link User} that matches the properties set in the input form. In addition, it is possible to specify
     * the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of users that match the criteria
     */
    public List<User> findUserByCriteria(CriteriaForm<User> criteria);

    /**
     * Deletes the given {@link User} from the database, including all existing links to other tables.
     * 
     * @param user
     *            the user to delete
     */
    public void deleteUser(User user);

    /**
     * Deletes the given list of {@link User}s from the database, including all existing links to other tables.
     * 
     * @param users
     *            the users to delete
     */
    public void deleteUsers(List<User> users);

    /**
     * Saves the given {@link UserGroup} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param userGroup
     *            the userGroup to save
     */
    public void saveUpdateUserGroup(UserGroup userGroup);

    /**
     * Finds the {@link UserGroup} with the given id.
     * 
     * @param id
     *            the identifier of the userGroup
     * @return the userGroup with the given id or null, if such an entry doesn't exist in the database.
     */
    public UserGroup findUserGroupById(Integer id);

    /**
     * Finds the {@link UserGroup} that matches the properties set in the input form.
     * 
     * @param userGroup
     *            a search form with the properties you want to search by
     * @return a list of userGroups that match the example
     */
    public List<UserGroup> findUserGroupByExample(UserGroup userGroup);

    /**
     * Finds the {@link UserGroup} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of userGroups that match the criteria
     */
    public List<UserGroup> findUserGroupByCriteria(CriteriaForm<UserGroup> criteria);

    /**
     * Deletes the given {@link UserGroup} from the database, including all existing links to other tables.
     * 
     * @param userGroup
     *            the userGroup to delete
     */
    public void deleteUserGroup(UserGroup userGroup);

    /**
     * Deletes the given list of {@link UserGroup}s from the database, including all existing links to other tables.
     * 
     * @param userGroups
     *            the userGroups to delete
     */
    public void deleteUserGroups(List<UserGroup> userGroups);

    /**
     * Saves the given {@link Application} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param application
     *            the application to save
     */
    public void saveUpdateApplication(Application application);

    /**
     * Finds the {@link Application} with the given id.
     * 
     * @param id
     *            the identifier of the application
     * @return the application with the given id or null, if such an entry doesn't exist in the database.
     */
    public Application findApplicationById(Integer id);

    /**
     * Finds the {@link Application} that matches the properties set in the input form.
     * 
     * @param application
     *            a search form with the properties you want to search by
     * @return a list of applications that match the example
     */
    public List<Application> findApplicationByExample(Application application);

    /**
     * Finds the {@link Application} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of applications that match the criteria
     */
    public List<Application> findApplicationByCriteria(CriteriaForm<Application> criteria);

    /**
     * Deletes the given {@link Application} from the database, including all existing links to other tables.
     * 
     * @param application
     *            the application to delete
     */
    public void deleteApplication(Application application);

    /**
     * Deletes the given list of {@link Application}s from the database, including all existing links to other tables.
     * 
     * @param applications
     *            the applications to delete
     */
    public void deleteApplications(List<Application> applications);

    /**
     * Saves the given {@link Computer} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param computer
     *            the computer to save
     */
    public void saveUpdateComputer(Computer computer);

    /**
     * Finds the {@link Computer} with the given id.
     * 
     * @param id
     *            the identifier of the computer
     * @return the computer with the given id or null, if such an entry doesn't exist in the database.
     */
    public Computer findComputerById(Integer id);

    /**
     * Finds the {@link Computer} that matches the properties set in the input form.
     * 
     * @param computer
     *            a search form with the properties you want to search by
     * @return a list of computers that match the example
     */
    public List<Computer> findComputerByExample(Computer computer);

    /**
     * Finds the {@link Computer} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of computers that match the criteria
     */
    public List<Computer> findComputerByCriteria(CriteriaForm<Computer> criteria);

    /**
     * Deletes the given {@link Computer} from the database, including all existing links to other tables.
     * 
     * @param computer
     *            the computer to delete
     */
    public void deleteComputer(Computer computer);

    /**
     * Deletes the given list of {@link Computer}s from the database, including all existing links to other tables.
     * 
     * @param computers
     *            the computers to delete
     */
    public void deleteComputers(List<Computer> computers);

    /**
     * Saves the given {@link Connection} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param connection
     *            the connection to save
     */
    public void saveUpdateConnection(Connection connection);

    /**
     * Finds the {@link Connection} with the given id.
     * 
     * @param id
     *            the identifier of the connection
     * @return the connection with the given id or null, if such an entry doesn't exist in the database.
     */
    public Connection findConnectionById(Integer id);

    /**
     * Finds the {@link Connection} that matches the properties set in the input form.
     * 
     * @param connection
     *            a search form with the properties you want to search by
     * @return a list of connections that match the example
     */
    public List<Connection> findConnectionByExample(Connection connection);

    /**
     * Finds the {@link Connection} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of connections that match the criteria
     */
    public List<Connection> findConnectionByCriteria(CriteriaForm<Connection> criteria);

    /**
     * Deletes the given {@link Connection} from the database, including all existing links to other tables.
     * 
     * @param connection
     *            the connection to delete
     */
    public void deleteConnection(Connection connection);

    /**
     * Deletes the given list of {@link Connection}s from the database, including all existing links to other tables.
     * 
     * @param connections
     *            the connections to delete
     */
    public void deleteConnections(List<Connection> connections);

    /**
     * Saves the given {@link ConnectionFarm} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param connectionFarm
     *            the connectionFarm to save
     */
    public void saveUpdateConnectionFarm(ConnectionFarm connectionFarm);

    /**
     * Finds the {@link ConnectionFarm} with the given id.
     * 
     * @param id
     *            the identifier of the connectionFarm
     * @return the connectionFarm with the given id or null, if such an entry doesn't exist in the database.
     */
    public ConnectionFarm findConnectionFarmById(Integer id);

    /**
     * Finds the {@link ConnectionFarm} that matches the properties set in the input form.
     * 
     * @param connectionFarm
     *            a search form with the properties you want to search by
     * @return a list of connectionFarms that match the example
     */
    public List<ConnectionFarm> findConnectionFarmByExample(ConnectionFarm connectionFarm);

    /**
     * Finds the {@link ConnectionFarm} that matches the properties set in the input form. In addition, it is possible
     * to specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of connectionFarms that match the criteria
     */
    public List<ConnectionFarm> findConnectionFarmByCriteria(CriteriaForm<ConnectionFarm> criteria);

    /**
     * Deletes the given {@link ConnectionFarm} from the database, including all existing links to other tables.
     * 
     * @param connectionFarm
     *            the connectionFarm to delete
     */
    public void deleteConnectionFarm(ConnectionFarm connectionFarm);

    /**
     * Deletes the given list of {@link ConnectionFarm}s from the database, including all existing links to other
     * tables.
     * 
     * @param connectionFarms
     *            the connectionFarms to delete
     */
    public void deleteConnectionFarms(List<ConnectionFarm> connectionFarms);

    /**
     * Saves the given {@link Project} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param project
     *            the project to save
     */
    public void saveUpdateProject(Project project);

    /**
     * Finds the {@link Project} with the given id.
     * 
     * @param id
     *            the identifier of the project
     * @return the project with the given id or null, if such an entry doesn't exist in the database.
     */
    public Project findProjectById(Integer id);

    /**
     * Finds the {@link Project} that matches the properties set in the input form.
     * 
     * @param project
     *            a search form with the properties you want to search by
     * @return a list of projects that match the example
     */
    public List<Project> findProjectByExample(Project project);

    /**
     * Finds the {@link Project} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of projects that match the criteria
     */
    public List<Project> findProjectByCriteria(CriteriaForm<Project> criteria);

    /**
     * Deletes the given {@link Project} from the database, including all existing links to other tables.
     * 
     * @param project
     *            the project to delete
     */
    public void deleteProject(Project project);

    /**
     * Deletes the given list of {@link Project}s from the database, including all existing links to other tables.
     * 
     * @param projects
     *            the projects to delete
     */
    public void deleteProjects(List<Project> projects);

    /**
     * Saves the given {@link Program} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param program
     *            the program to save
     */
    public void saveUpdateProgram(Program program);

    /**
     * Finds the {@link Program} with the given id.
     * 
     * @param id
     *            the identifier of the program
     * @return the program with the given id or null, if such an entry doesn't exist in the database.
     */
    public Program findProgramById(Integer id);

    /**
     * Finds the {@link Program} that matches the properties set in the input form.
     * 
     * @param program
     *            a search form with the properties you want to search by
     * @return a list of programs that match the example
     */
    public List<Program> findProgramByExample(Program program);

    /**
     * Finds the {@link Program} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of programs that match the criteria
     */
    public List<Program> findProgramByCriteria(CriteriaForm<Program> criteria);

    /**
     * Deletes the given {@link Program} from the database, including all existing links to other tables.
     * 
     * @param program
     *            the program to delete
     */
    public void deleteProgram(Program program);

    /**
     * Deletes the given list of {@link Program}s from the database, including all existing links to other tables.
     * 
     * @param programs
     *            the programs to delete
     */
    public void deletePrograms(List<Program> programs);

    /**
     * Saves the given {@link Experiment} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param experiment
     *            the experiment to save
     */
    public void saveUpdateExperiment(Experiment experiment);

    /**
     * Finds the {@link Experiment} with the given id.
     * 
     * @param id
     *            the identifier of the experiment
     * @return the experiment with the given id or null, if such an entry doesn't exist in the database.
     */
    public Experiment findExperimentById(Integer id);

    /**
     * Finds the {@link Experiment} that matches the properties set in the input form.
     * 
     * @param experiment
     *            a search form with the properties you want to search by
     * @return a list of experiments that match the example
     */
    public List<Experiment> findExperimentByExample(Experiment experiment);

    /**
     * Finds the {@link Experiment} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of experiments that match the criteria
     */
    public List<Experiment> findExperimentByCriteria(CriteriaForm<Experiment> criteria);

    /**
     * Deletes the given {@link Experiment} from the database, including all existing links to other tables.
     * 
     * @param experiment
     *            the experiment to delete
     */
    public void deleteExperiment(Experiment experiment);

    /**
     * Deletes the given list of {@link Experiment}s from the database, including all existing links to other tables.
     * 
     * @param experiments
     *            the experiments to delete
     */
    public void deleteExperiments(List<Experiment> experiments);

    /**
     * Saves the given {@link Input} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param input
     *            the input to save
     */
    public void saveUpdateInput(Input input);

    /**
     * Finds the {@link Input} with the given id.
     * 
     * @param id
     *            the identifier of the input
     * @return the input with the given id or null, if such an entry doesn't exist in the database.
     */
    public Input findInputById(Integer id);

    /**
     * Finds the {@link Input} that matches the properties set in the input form.
     * 
     * @param input
     *            a search form with the properties you want to search by
     * @return a list of inputs that match the example
     */
    public List<Input> findInputByExample(Input input);

    /**
     * Finds the {@link Input} that matches the properties set in the input form. In addition, it is possible to specify
     * the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of inputs that match the criteria
     */
    public List<Input> findInputByCriteria(CriteriaForm<Input> criteria);

    /**
     * Deletes the given {@link Input} from the database, including all existing links to other tables.
     * 
     * @param input
     *            the input to delete
     */
    public void deleteInput(Input input);

    /**
     * Deletes the given list of {@link Input}s from the database, including all existing links to other tables.
     * 
     * @param inputs
     *            the inputs to delete
     */
    public void deleteInputs(List<Input> inputs);

    /**
     * Saves the given {@link InputSet} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param inputSet
     *            the inputSet to save
     */
    public void saveUpdateInputSet(InputSet inputSet);

    /**
     * Finds the {@link InputSet} with the given id.
     * 
     * @param id
     *            the identifier of the inputSet
     * @return the inputSet with the given id or null, if such an entry doesn't exist in the database.
     */
    public InputSet findInputSetById(Integer id);

    /**
     * Finds the {@link InputSet} that matches the properties set in the input form.
     * 
     * @param inputSet
     *            a search form with the properties you want to search by
     * @return a list of inputSets that match the example
     */
    public List<InputSet> findInputSetByExample(InputSet inputSet);

    /**
     * Finds the {@link InputSet} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of inputSets that match the criteria
     */
    public List<InputSet> findInputSetByCriteria(CriteriaForm<InputSet> criteria);

    /**
     * Deletes the given {@link InputSet} from the database, including all existing links to other tables.
     * 
     * @param inputSet
     *            the inputSet to delete
     */
    public void deleteInputSet(InputSet inputSet);

    /**
     * Deletes the given list of {@link InputSet}s from the database, including all existing links to other tables.
     * 
     * @param inputSets
     *            the inputSets to delete
     */
    public void deleteInputSets(List<InputSet> inputSets);

    /**
     * Saves the given {@link ProblemType} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param problemType
     *            the problemType to save
     */
    public void saveUpdateProblemType(ProblemType problemType);

    /**
     * Finds the {@link ProblemType} with the given id.
     * 
     * @param id
     *            the identifier of the problemType
     * @return the problemType with the given id or null, if such an entry doesn't exist in the database.
     */
    public ProblemType findProblemTypeById(Integer id);

    /**
     * Finds the {@link ProblemType} that matches the properties set in the input form.
     * 
     * @param problemType
     *            a search form with the properties you want to search by
     * @return a list of problemTypes that match the example
     */
    public List<ProblemType> findProblemTypeByExample(ProblemType problemType);

    /**
     * Finds the {@link ProblemType} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of problemTypes that match the criteria
     */
    public List<ProblemType> findProblemTypeByCriteria(CriteriaForm<ProblemType> criteria);

    /**
     * Deletes the given {@link ProblemType} from the database, including all existing links to other tables.
     * 
     * @param problemType
     *            the problemType to delete
     */
    public void deleteProblemType(ProblemType problemType);

    /**
     * Deletes the given list of {@link ProblemType}s from the database, including all existing links to other tables.
     * 
     * @param problemTypes
     *            the problemTypes to delete
     */
    public void deleteProblemTypes(List<ProblemType> problemTypes);

    /**
     * Adds a {@link User} to a {@link UserGroup}
     * 
     * @param user
     *            the user to add
     * @param userGroup
     *            the group to add to
     */
    public void addUserToUserGroup(User user, UserGroup userGroup);

    /**
     * Adds a {@link Input} to a {@link InputSet}
     * 
     * @param input
     *            the user to input
     * @param inputSet
     *            the inputSet to add to
     */
    public void addInputToInputSet(Input input, InputSet inputSet);

    /**
     * Adds a {@link InputSet} to a {@link Experiment}
     * 
     * @param inputSet
     *            the inputSet to add
     * @param experiment
     *            the experiment to add to
     */
    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment);

    /**
     * Adds a {@link InputSet} to a {@link Project}
     * 
     * @param inputSet
     *            the inputSet to add
     * @param project
     *            the project to add to
     */
    public void addInputSetToProject(InputSet inputSet, Project project);

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
     * Removes a {@link User} from a {@link UserGroup}
     * 
     * @param user
     *            the user to removew
     * @param userGroup
     *            the group to remove from
     */
    public void removeUserFromUserGroup(User user, UserGroup userGroup);

    /**
     * Removes a {@link Input} from a {@link InputSet}
     * 
     * @param input
     *            the input to remove
     * @param inputSet
     *            the inputSet to remove from
     */
    public void removeInputFromInputSet(Input input, InputSet inputSet);

    /**
     * Removes a {@link InputSet} from a {@link Experiment}
     * 
     * @param inputSet
     *            the inputSet to remove
     * @param experiment
     *            the experiment to remove from
     */
    public void removeInputSetFromExperiment(InputSet inputSet, Experiment experiment);

    /**
     * Removes a {@link InputSet} from a {@link Project}
     * 
     * @param inputSet
     *            the inputSet to remove
     * @param project
     *            the project to remove from
     */
    public void removeInputSetFromProject(InputSet inputSet, Project project);

    /**
     * Removes a {@link ConnectionFarm} from a {@link Experiment}
     * 
     * @param connectionFarm
     *            the connectionFarm to remove
     * @param experiment
     *            the experiment to remove from
     */
    public void removeConnectionFarmFromExperiment(ConnectionFarm connectionFarm, Experiment experiment);
}
