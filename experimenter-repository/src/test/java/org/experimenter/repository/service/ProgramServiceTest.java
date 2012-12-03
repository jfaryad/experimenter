package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;

public class ProgramServiceTest extends AbstractServiceTest {

    @Test
    public void findProgramsByProjectTest() {
        Project project = projectService.findById(1);
        List<Program> programs = programService.findProgramsByProject(project);
        assertEquals("wrong number of programs found", 3, programs.size());
        Program program = programs.get(0);
        assertNotNull("program not found", project);
        DaoTestHelper.checkProgram1(program);
    }

}
