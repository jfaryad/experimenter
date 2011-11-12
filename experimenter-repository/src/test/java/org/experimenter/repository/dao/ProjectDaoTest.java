package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.CriteriaForm;
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
public class ProjectDaoTest {

    @Autowired
    private ProjectDao projectDao;

    @Test
    public void insertProject() {
        Project project = new Project();
        project.setName("testProject");
        project.setDescription("project for testing");
        UserGroup group = new UserGroup();
        group.setId(1);
        ProblemType problem = new ProblemType();
        problem.setId(1);
        project.setUserGroup(group);
        project.setProblem(problem);
        projectDao.insert(project);
        assertNotNull("projectId is null after insert", project.getId());
        project = projectDao.findById(project.getId());
        assertEquals("testProject", project.getName());
        assertEquals("project for testing", project.getDescription());
        assertEquals(1, project.getUserGroup().getId().intValue());
        assertEquals("students", project.getUserGroup().getName());
        assertEquals(1, project.getProblem().getId().intValue());
        assertEquals("3-SAT", project.getProblem().getName());
    }

    @Test
    public void findProjectById() {
        Integer id = 1;
        Project project = projectDao.findById(id);
        DaoTestHelper.checkProject1(project);
    }

    @Test
    public void deleteProject() {
        Integer id = 2;
        projectDao.deleteById(id);
        assertNull("project was not deleted", projectDao.findById(id));
    }

    @Test
    public void updateProject() {
        Integer id = 3;
        Project project = projectDao.findById(id);
        assertNotNull("project was not found before update", project);
        assertEquals("testProject3", project.getName());
        project.setName("newName");
        projectDao.update(project);
        project = projectDao.findById(id);
        assertNotNull("project was not found after update", project);
        assertEquals("newName", project.getName());
    }

    @Test
    public void findProjectByCriteria() {
        Project model = new Project();
        model.setName("testProject1");
        CriteriaForm<Project> criteria = new CriteriaForm<Project>(model);
        List<Project> projects = projectDao.findByCriteria(criteria);
        assertEquals("wrong number of projects found", 1, projects.size());
        Project project = projects.get(0);
        DaoTestHelper.checkProject1(project);
    }

}
