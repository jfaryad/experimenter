package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;

public class ProjectServiceTest extends AbstractServiceTest {

    @Test
    public void findProjectsByUserGroupTest() {
        UserGroup userGroup = userGroupService.findById(1);
        List<Project> projects = projectService.findProjectsByUserGroup(userGroup);
        assertEquals("wrong number of projects found", 4, projects.size());
        Project project = projects.get(0);
        assertNotNull("project not found", project);
        DaoTestHelper.checkProject1(project);
    }

    @Test
    public void findProjectsByProblemTypeTest() {
        ProblemType problem = problemTypeService.findById(1);
        List<Project> projects = projectService.findProjectsByProblemType(problem);
        assertEquals("wrong number of projects found", 2, projects.size());
        Project project = projects.get(0);
        assertNotNull("project not found", project);
        DaoTestHelper.checkProject1(project);
    }

    @Test
    public void findProjectsByInputSetTest() {
        InputSet inputSet = inputSetService.findById(1);
        List<Project> projects = projectService.findProjectsByInputSet(inputSet);
        assertEquals("wrong number of projects found", 1, projects.size());
        Project project = projects.get(0);
        assertNotNull("project not found", project);
        DaoTestHelper.checkProject1(project);
    }

}
