package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
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
public class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;

    @Test
    public void findApplicationsByProgramTest() {
        Program program = new Program(1);
        List<Application> applications = applicationService.findApplicationsByProgram(program);
        assertEquals("wrong number of applications found.", 3, applications.size());
    }

}
