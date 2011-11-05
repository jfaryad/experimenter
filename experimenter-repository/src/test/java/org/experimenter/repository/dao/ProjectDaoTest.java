package org.experimenter.repository.dao;

import static org.junit.Assert.assertNotNull;

import org.experimenter.repository.model.Project;
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
public class ProjectDaoTest {

	@Autowired
	private ProjectDao projectDao;

	@Test
	public void insertProject() {
		Project project = new Project();
		project.setName("testProject");
		project.setDescription("project for testing");
		projectDao.insert(project);
		assertNotNull("projectId is null after insert", project.getProjectId());
	}

}
