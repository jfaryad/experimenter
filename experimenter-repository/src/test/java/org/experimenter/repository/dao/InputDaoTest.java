package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.Input;
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
public class InputDaoTest {

	@Autowired
	private InputDao inputDao;

	@Test
	public void insertInput() {
		Input input = new Input();
		input.setName("sampleData");
		inputDao.insert(input);
		assertNotNull("inputId is null after insert", input.getInputId());
	}

}
