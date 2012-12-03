package org.experimenter.repository.testutil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

public class DaoTestHelper {

    public static final DateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void checkUserGroup1(UserGroup userGroup) {
        assertNotNull("userGroup not found", userGroup);
        assertEquals(1, userGroup.getId().intValue());
        assertEquals("students", userGroup.getName());
    }

    public static void checkProblem1(ProblemType problem) {
        assertNotNull("problem not found", problem);
        assertEquals(1, problem.getId().intValue());
        assertEquals("you know what it means", problem.getDescription());
        assertEquals("3-SAT", problem.getName());
    }

    public static void checkProject1(Project project) {
        assertNotNull("project not found", project);
        assertEquals(1, project.getId().intValue());
        assertEquals("testProject1", project.getName());
        assertEquals("first project", project.getDescription());
        checkUserGroup1(project.getUserGroup());
        checkProblem1(project.getProblem());
    }

    public static void checkUser1(User user) {
        assertEquals(1, user.getId().intValue());
        assertEquals("Tester1", user.getName());
        assertEquals("Exists", user.getSurname());
        assertEquals("tester1", user.getLogin());
        assertEquals("heslo", user.getPassword());
        assertEquals(Boolean.FALSE, user.getIsAdmin());
        assertEquals("tester1@experimenter.org", user.getEmail());
    }

    public static void checkProgram1(Program program) {
        assertNotNull("program not found", program);
        assertEquals("solver1", program.getName());
        assertEquals("program to test find", program.getDescription());
        assertEquals("solver1.sh run", program.getCommand());
        checkProject1(program.getProject());
    }

    public static void checkApplication1(Application application) {
        assertNotNull("application not found", application);
        assertEquals("1.3", application.getVersion());
        assertEquals("solver1_1.3.sh run", application.getExecutable());
        checkProgram1(application.getProgram());
    }

    public static void checkComputer1(Computer computer) {
        assertNotNull("computer not found", computer);
        assertEquals("u-pl20", computer.getAddress());
        assertEquals("computer u-pl20, test exists", computer.getDescription());
    }

    public static void checkConnection1(Connection connection) {
        assertNotNull("connection not found", connection);
        assertEquals("myConn1exists", connection.getName());
        assertEquals("my test connection 1", connection.getDescription());
        assertEquals("test", connection.getLogin());
        assertEquals("test123", connection.getPassword());
        assertEquals(221, connection.getPort().intValue());
        checkComputer1(connection.getComputer());
        checkConnectionFarm1(connection.getConnectionFarm());
    }

    public static void checkConnectionFarm1(ConnectionFarm connectionFarm) {
        assertNotNull("connectionFarm not found", connectionFarm);
        assertEquals("testFarm1", connectionFarm.getName());
        assertEquals("farm to test find", connectionFarm.getDescription());
        checkUserGroup1(connectionFarm.getUserGroup());
    }

    public static void checkExperiment1(Experiment experiment) {
        assertNotNull("experiment not found", experiment);
        assertEquals("experiment1", experiment.getName());
        assertEquals("experiment to test find", experiment.getDescription());
        assertEquals("0/10 * * * * ?", experiment.getCronExpression());
        try {
            assertEquals(timeStampFormat.parse("2008-08-08 20:08:00"), experiment.getScheduledTime());
        } catch (ParseException e) {
            throw new RuntimeException("error parsing time format");
        }
        checkApplication1(experiment.getApplication());
    }

    public static void checkInput1(Input input) {
        assertNotNull("input not found", input);
        assertEquals("testInput1", input.getName());
        assertEquals("data1", input.getData());
        checkProblem1(input.getProblem());
    }

    public static void checkInputSet1(InputSet inputSet) {
        assertNotNull("inputSet not found", inputSet);
        assertEquals("testInputSet1", inputSet.getName());
        assertEquals("basic set of inputs", inputSet.getDescription());
        checkProblem1(inputSet.getProblem());
    }

}
