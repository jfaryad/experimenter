package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.ConnectionFarm;
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
	private ConnectionFarmDao farmDao;

	@Test
	public void insertConnectionFarm() {
		ConnectionFarm farm = new ConnectionFarm();
		farm.setName("farm1");
		farm.setDescription("farm1");
		farmDao.insert(farm);
		assertNotNull("farmId is null after insert", farm.getConnectionFarmId());
	}

}
