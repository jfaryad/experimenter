package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.model.ConnectionFarm;
import org.experimenter.repository.model.UserGroup;
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
public class ConnectionFarmDaoTest {

    @Autowired
    private ConnectionFarmDao connectionFarmDao;

    @Test
    public void insertConnectionFarm() {
        ConnectionFarm connectionFarm = new ConnectionFarm();
        connectionFarm.setName("connectionFarm1");
        connectionFarm.setDescription("connectionFarm1");
        UserGroup group = new UserGroup();
        group.setUserGroupId(1);
        connectionFarm.setUserGroup(group);
        connectionFarmDao.insert(connectionFarm);
        assertNotNull("connectionFarmId is null after insert", connectionFarm.getConnectionFarmId());
        assertEquals("connectionFarm1", connectionFarm.getName());
        assertEquals("connectionFarm1", connectionFarm.getDescription());
        assertEquals(1, connectionFarm.getUserGroup().getUserGroupId().intValue());
    }

    @Test
    public void findConnectionFarmById() {
        Integer id = 1;
        ConnectionFarm connectionFarm = connectionFarmDao.findById(id);
        DaoTestHelper.checkConnectionFarm1(connectionFarm);
    }

    @Test
    public void deleteConnectionFarm() {
        Integer id = 2;
        connectionFarmDao.deleteById(id);
        assertNull("connectionFarm was not deleted", connectionFarmDao.findById(id));
    }

    @Test
    public void updateConnectionFarm() {
        Integer id = 3;
        ConnectionFarm connectionFarm = connectionFarmDao.findById(id);
        assertNotNull("connectionFarm was not found before update", connectionFarm);
        assertEquals("testFarm3", connectionFarm.getName());
        connectionFarm.setName("newName");
        connectionFarmDao.update(connectionFarm);
        connectionFarm = connectionFarmDao.findById(id);
        assertNotNull("connectionFarm was not found after update", connectionFarm);
        assertEquals("newName", connectionFarm.getName());
    }

    @Test
    public void findConnectionFarmByCriteria() {
        ConnectionFarm model = new ConnectionFarm();
        model.setName("testFarm1");
        CriteriaForm<ConnectionFarm> criteria = new CriteriaForm<ConnectionFarm>(model);
        List<ConnectionFarm> connectionFarms = connectionFarmDao.findByCriteria(criteria);
        assertEquals("wrong number of connectionFarms found", 1, connectionFarms.size());
        ConnectionFarm connectionFarm = connectionFarms.get(0);
        assertNotNull("connectionFarm not found", connectionFarm);
        DaoTestHelper.checkConnectionFarm1(connectionFarm);
    }

}
