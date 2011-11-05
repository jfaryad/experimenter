package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.Application;
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
public class ApplicationDaoTest {

	@Autowired
	private ApplicationDao applicationDao;

	@Test
	public void insertApplication() {
		Application application = new Application();
		application.setVersion("1.2");
		applicationDao.insert(application);
		assertNotNull("applicationId is null after insert",
				application.getApplicationId());
	}

}
