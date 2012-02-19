package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;

public class ProgramDaoTest extends AbstractDaoTest {

    @Test
    public void insertProgram() {
        Program program = new Program();
        program.setName("SuperSolver");
        program.setDescription("the fastest solver ever");
        program.setCommand("sh solver.sh -i inputData");
        Project project = new Project();
        project.setId(1);
        program.setProject(project);
        programDao.insert(program);
        assertNotNull("programId is null after insert", program.getId());
        assertEquals("SuperSolver", program.getName());
        assertEquals("the fastest solver ever", program.getDescription());
        assertEquals("sh solver.sh -i inputData", program.getCommand());
        assertEquals(1, program.getProject().getId().intValue());
    }

    @Test
    public void findProgramById() {
        Integer id = 1;
        Program program = programDao.findById(id);
        DaoTestHelper.checkProgram1(program);
    }

    @Test
    public void deleteProgram() {
        Integer id = 3;
        programDao.deleteById(id);
        flush();
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
        CriteriaForm<Program> criteria = new CriteriaForm<Program>(model);
        List<Program> programs = programDao.findByCriteria(criteria);
        assertEquals("wrong number of programs found", 1, programs.size());
        Program program = programs.get(0);
        DaoTestHelper.checkProgram1(program);
    }

}
