package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.ProblemType;
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
public class ProblemTypeDaoTest {

	@Autowired
	private ProblemTypeDao problemDao;

	@Test
	public void insertProblemType() {
		ProblemType problem = new ProblemType();
		problem.setName("3-sat");
		problem.setDescription("k-sat for 3 literals");
		problemDao.insert(problem);
		assertNotNull("problemId is null after insert", problem.getProblemId());
	}

}
