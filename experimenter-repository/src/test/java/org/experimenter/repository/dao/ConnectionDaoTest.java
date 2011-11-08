package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.Connection;
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
public class ConnectionDaoTest extends AbstractTest {

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
        connectionDao.insert(connection);
        assertNotNull("connectionId is null after insert", connection.getConnectionId());
    }

}
