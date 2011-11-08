package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.model.ProblemType;
import org.experimenter.repository.model.Project;
import org.experimenter.repository.model.UserGroup;
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
public class ProjectDaoTest extends AbstractTest {

    @Autowired
    private ProjectDao projectDao;

    @Test
    public void insertProject() {
        Project project = new Project();
        project.setName("testProject");
        project.setDescription("project for testing");
        UserGroup group = new UserGroup();
        group.setUserGroupId(1);
        ProblemType problem = new ProblemType();
        problem.setProblemId(1);
        project.setUserGroup(group);
        project.setProblem(problem);
        projectDao.insert(project);
        assertNotNull("projectId is null after insert", project.getProjectId());
        project = projectDao.findById(project.getProjectId());
        assertEquals("testProject", project.getName());
        assertEquals("project for testing", project.getDescription());
        assertEquals(1, project.getUserGroup().getUserGroupId().intValue());
        assertEquals(1, project.getProblem().getProblemId().intValue());
    }

    @Test
    public void findProjectById() {
        Integer id = 1;
        Project project = projectDao.findById(id);
        assertNotNull("project not found", project);
        assertEquals("testProject1", project.getName());
        assertEquals("first project", project.getDescription());
        assertEquals(1, project.getUserGroup().getUserGroupId().intValue());
        assertEquals(1, project.getProblem().getProblemId().intValue());
    }

    @Test
    public void deleteProject() {
        Integer id = 2;
        projectDao.deleteById(id);
        flush();
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
        assertNotNull("project not found", project);
        assertEquals("testProject1", project.getName());
        assertEquals("first project", project.getDescription());
        assertEquals(1, project.getUserGroup().getUserGroupId().intValue());
        assertEquals(1, project.getProblem().getProblemId().intValue());
    }

}
