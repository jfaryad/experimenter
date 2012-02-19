package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;

public class ConnectionFarmServiceTest extends AbstractServiceTest {

    @Test
    public void findConnectionFarmsByUserGroupTest() {
        UserGroup userGroup = userGroupService.findById(1);
        List<ConnectionFarm> connectionFarms = connectionFarmService.findConnectionFarmsByUserGroup(userGroup);
        assertEquals("wrong number of farms found", 2, connectionFarms.size());
        ConnectionFarm connectionFarm = connectionFarms.get(0);
        assertNotNull("farm not found", connectionFarm);
        DaoTestHelper.checkConnectionFarm1(connectionFarm);
    }

    @Test
    public void findConnectionFarmsByExperimentTest() {
        Experiment experiment = experimentService.findById(1);
        List<ConnectionFarm> connectionFarms = connectionFarmService.findConnectionFarmsByExperiment(experiment);
        assertEquals("wrong number of farms found", 1, connectionFarms.size());
        ConnectionFarm connectionFarm = connectionFarms.get(0);
        assertNotNull("farm not found", connectionFarm);
        DaoTestHelper.checkConnectionFarm1(connectionFarm);
    }

    @Test
    public void addConnectionFarmToExperimentTest() {
        Experiment experiment = experimentService.findById(3);
        assertEquals("experiment is not supposed to have associated connection farms", 0, connectionFarmService
                .findConnectionFarmsByExperiment(experiment).size());
        ConnectionFarm connectionFarm = connectionFarmService.findById(1);
        connectionFarmService.addConnectionFarmToExperiment(connectionFarm, experiment);
        assertEquals("farm hasn't been added to experiment", 1,
                connectionFarmService.findConnectionFarmsByExperiment(experiment).size());
    }

    @Test
    public void removeConnectionFarmFromExperimentTest() {
        Experiment experiment = experimentService.findById(2);
        assertEquals("experiment is  supposed to have an associated connection farm", 1, connectionFarmService
                .findConnectionFarmsByExperiment(experiment).size());
        ConnectionFarm connectionFarm = connectionFarmService.findById(4);
        connectionFarmService.removeConnectionFarmFromExperiment(connectionFarm, experiment);
        assertEquals("farm hasn't been removed from experiment", 0, connectionFarmService
                .findConnectionFarmsByExperiment(experiment).size());
    }

    @Test
    public void testDeleteDependencies() {
        assertNotNull("connection doesn't exist before deleting", connectionService.findById(4));

        connectionFarmService.delete(connectionFarmService.findById(4));

        assertNull("connection wasn't deleted", connectionService.findById(4));
    }
}
