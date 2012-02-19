package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.junit.Test;

public class ApplicationServiceTest extends AbstractServiceTest {

    @Test
    public void findApplicationsByProgramTest() {
        Program program = programService.findById(1);
        List<Application> applications = applicationService.findApplicationsByProgram(program);
        assertEquals("wrong number of applications found.", 3, applications.size());
    }

}
