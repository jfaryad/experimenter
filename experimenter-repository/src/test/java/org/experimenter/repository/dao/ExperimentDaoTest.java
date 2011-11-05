package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.Experiment;
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
public class ExperimentDaoTest {

	@Autowired
	private ExperimentDao experimentDao;

	@Test
	public void insertExperiment() {
		Experiment experiment = new Experiment();
		experiment.setName("exp1");
		experiment.setDescription("exp1 - sat");
		experimentDao.insert(experiment);
		assertNotNull("experimentId is null after insert",
				experiment.getExperimentId());
	}

}
