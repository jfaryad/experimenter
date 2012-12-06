package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    /**
     * finds 2 computers in the pool, with 5 already running jobs together. max jobs is 5, so there are 5 more free
     * slots on those computers. The test then starts 10 threads that want to acquire a connection. the first 5 succeed,
     * the other 5 fail.
     */
    @Test
    public void loadBalancingConnectionsThreadSafetyTest() {
        final List<ConnectionFarm> farms = new ArrayList<ConnectionFarm>();
        farms.add(connectionFarmService.findById(1));
        // assert initial state
        assertEquals(3, computerService.findById(1).getNumberOfRunningJobs().intValue());
        assertEquals(2, computerService.findById(3).getNumberOfRunningJobs().intValue());
        final int maxRunningJobs = 5; // he have 5 "empty slots"
        final CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    Connection connection;
                    if ((connection = connectionService.addJobToLeastLoadedConnection(farms, maxRunningJobs)) != null) {
                        System.out.println(Thread.currentThread().getName() + " obtained connection, going to work");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        return;
                    }
                    System.out.println(Thread.currentThread().getName()
                            + " finished work, releasing connection, acutal countdown: " + latch.getCount());
                    connectionService.removeJobFromConnection(connection);
                    latch.countDown();
                };
            }.start();
        }
        try {
            latch.await(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(5, latch.getCount());
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
