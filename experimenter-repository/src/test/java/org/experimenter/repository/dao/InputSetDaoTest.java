package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.InputSet;
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
public class InputSetDaoTest {

	@Autowired
	private InputSetDao setDao;

	@Test
	public void insertInputSet() {
		InputSet set = new InputSet();
		set.setName("set1");
		set.setDescription("basic input set");
		setDao.insert(set);
		assertNotNull("setId is null after insert", set.getInputSetId());
	}

}
