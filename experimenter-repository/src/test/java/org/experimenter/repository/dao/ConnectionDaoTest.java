package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:repositoryContextTest.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ConnectionDaoTest {

    @Autowired
    private ConnectionDao connectionDao;

    @Test
    public void insertConnection() {
        Connection connection = new Connection();
        connection.setName("lab1");
        connection.setDescription("lab1");
        connection.setLogin("pepa");
        connection.setPassword("pepa123");
        connection.setPort((short) 221);
        Computer computer = new Computer();
        computer.setId(1);
        connection.setComputer(computer);
        ConnectionFarm connectionFarm = new ConnectionFarm();
        connectionFarm.setId(1);
        connection.setConnectionFarm(connectionFarm);
        connectionDao.insert(connection);
        assertNotNull("connectionId is null after insert", connection.getId());
        assertEquals("lab1", connection.getName());
        assertEquals("lab1", connection.getDescription());
        assertEquals("pepa", connection.getLogin());
        assertEquals("pepa123", connection.getPassword());
        assertEquals(221, connection.getPort().intValue());
        assertEquals(1, connection.getComputer().getId().intValue());
        assertEquals(1, connection.getConnectionFarm().getId().intValue());
    }

    @Test
    public void findConnectionById() {
        Integer id = 1;
        Connection connection = connectionDao.findById(id);
        DaoTestHelper.checkConnection1(connection);
    }

    @Test
    public void deleteConnection() {
        Integer id = 2;
        connectionDao.deleteById(id);
        assertNull("connection was not deleted", connectionDao.findById(id));
    }

    @Test
    public void updateConnection() {
        Integer id = 3;
        Connection connection = connectionDao.findById(id);
        assertNotNull("connection was not found before update", connection);
        assertEquals("myConn3update", connection.getName());
        connection.setName("newName");
        connectionDao.update(connection);
        connection = connectionDao.findById(id);
        assertNotNull("connection was not found after update", connection);
        assertEquals("newName", connection.getName());
    }

    @Test
    public void findConnectionByCriteria() {
        Connection model = new Connection();
        model.setLogin("test");
        CriteriaForm<Connection> criteria = new CriteriaForm<Connection>(model);
        List<Connection> connections = connectionDao.findByCriteria(criteria);
        assertEquals("wrong number of connections found", 1, connections.size());
        Connection connection = connections.get(0);
        DaoTestHelper.checkConnection1(connection);
    }

}
