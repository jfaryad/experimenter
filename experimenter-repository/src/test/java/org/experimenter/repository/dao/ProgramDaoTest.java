package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.ModelCriteria;
import org.experimenter.repository.model.Program;
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
public class ProgramDaoTest extends AbstractTest {

    @Autowired
    private ProgramDao programDao;

    @Test
    public void insertProgram() {
        Program program = new Program();
        program.setName("SuperSolver");
        program.setDescription("the fastest solver ever");
        program.setCommand("sh solver.sh -i inputData");
        Project project = new Project();
        project.setProjectId(1);
        program.setProject(project);
        programDao.insert(program);
        assertNotNull("programId is null after insert", program.getProgramId());
        assertEquals("SuperSolver", program.getName());
        assertEquals("the fastest solver ever", program.getDescription());
        assertEquals("sh solver.sh -i inputData", program.getCommand());
        assertEquals(1, program.getProject().getProjectId().intValue());
    }

    @Test
    public void findProgramById() {
        Integer id = 1;
        Program program = programDao.findById(id);
        assertNotNull("program not found", program);
        assertEquals("solver1", program.getName());
        assertEquals("program to test find", program.getDescription());
        assertEquals("solver1.sh run", program.getCommand());
        assertEquals(1, program.getProject().getProjectId().intValue());
    }

    @Test
    public void deleteProgram() {
        Integer id = 3;
        programDao.deleteById(id);
        assertNull("program was not deleted", programDao.findById(id));
    }

    @Test
    public void updateProgram() {
        Integer id = 3;
        Program program = programDao.findById(id);
        assertNotNull("program was not found before update", program);
        assertEquals("solver3", program.getName());
        program.setName("newName");
        programDao.update(program);
        program = programDao.findById(id);
        assertNotNull("program was not found after update", program);
        assertEquals("newName", program.getName());
    }

    @Test
    public void findProgramByCriteria() {
        Program model = new Program();
        model.setCommand("solver1.sh run");
        ModelCriteria<Program> criteria = new ModelCriteria<Program>(model);
        List<Program> programs = programDao.findByCriteria(criteria);
        assertEquals("wrong number of programs found", 1, programs.size());
        Program program = programs.get(0);
        assertNotNull("program not found", program);
        assertEquals("solver1", program.getName());
        assertEquals("program to test find", program.getDescription());
        assertEquals("solver1.sh run", program.getCommand());
        assertEquals(1, program.getProject().getProjectId().intValue());
    }

}
