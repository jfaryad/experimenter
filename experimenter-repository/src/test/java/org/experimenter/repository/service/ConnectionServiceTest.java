package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;

public class ConnectionServiceTest extends AbstractServiceTest {

    @Test
    public void findConnectionsByConnectionFarmTest() {
        ConnectionFarm connectionFarm = connectionFarmService.findById(1);
        List<Connection> connections = connectionService.findConnectionsByConnectionFarm(connectionFarm);
        assertEquals("wrong number of connections found", 3, connections.size());
        Connection connection = connections.get(0);
        assertNotNull("connection not found", connection);
        DaoTestHelper.checkConnection1(connection);
    }

    @Test
    public void findConnectionsByComputerTest() {
        Computer computer = computerService.findById(1);
        List<Connection> connections = connectionService.findConnectionsByComputer(computer);
        assertEquals("wrong number of connections found", 2, connections.size());
        Connection connection = connections.get(0);
        assertNotNull("connection not found", connection);
        DaoTestHelper.checkConnection1(connection);
    }

    @Test
    public void addJobToLeastLoadedConnection() {
        List<ConnectionFarm> farms = new ArrayList<ConnectionFarm>();
        farms.add(connectionFarmService.findById(1));
        assertEquals(3, computerService.findById(1).getNumberOfRunningJobs().intValue());
        assertEquals(2, computerService.findById(3).getNumberOfRunningJobs().intValue());
        connectionService.addJobToLeastLoadedConnection(farms, 5);
        assertEquals(3, computerService.findById(1).getNumberOfRunningJobs().intValue());
        assertEquals(3, computerService.findById(3).getNumberOfRunningJobs().intValue());
        connectionService.addJobToLeastLoadedConnection(farms, 5);
        assertEquals(4, computerService.findById(1).getNumberOfRunningJobs().intValue());
        assertEquals(3, computerService.findById(3).getNumberOfRunningJobs().intValue());
        connectionService.addJobToLeastLoadedConnection(farms, 5);
        assertEquals(4, computerService.findById(1).getNumberOfRunningJobs().intValue());
        assertEquals(4, computerService.findById(3).getNumberOfRunningJobs().intValue());
    }

    @Test
    public void addJobToLeastLoadedConnectionTooRestrictive() {
        List<ConnectionFarm> farms = new ArrayList<ConnectionFarm>();
        farms.add(connectionFarmService.findById(1));
        Connection connection = connectionService.addJobToLeastLoadedConnection(farms, 1);
        assertNull(connection);
    }

}
