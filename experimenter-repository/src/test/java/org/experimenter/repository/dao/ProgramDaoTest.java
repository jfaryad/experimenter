package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.Program;
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
public class ProgramDaoTest {

	@Autowired
	private ProgramDao programDao;

	@Test
	public void insertProgram() {
		Program program = new Program();
		program.setName("SuperSolver");
		program.setDescription("the fastest solver ever");
		program.setCommand("sh solver.sh -i inputData");
		programDao.insert(program);
		assertNotNull("programId is null after insert", program.getProgramId());
	}

}
