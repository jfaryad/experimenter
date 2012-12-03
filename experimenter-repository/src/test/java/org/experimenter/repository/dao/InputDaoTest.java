package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;

public class InputDaoTest extends AbstractDaoTest {

    @Test
    public void insertInput() {
        Input input = new Input();
        input.setName("sampleInput");
        input.setData("input for testing");
        input.setChecksum("checksum");
        ProblemType problem = new ProblemType();
        problem.setId(1);
        input.setProblem(problem);
        inputDao.insert(input);
        assertNotNull("inputId is null after insert", input.getId());
        assertEquals("sampleInput", input.getName());
        assertEquals("input for testing", input.getData());
        assertEquals(1, input.getProblem().getId().intValue());
    }

    @Test
    public void findInputById() {
        Integer id = 1;
        Input input = inputDao.findById(id);
        DaoTestHelper.checkInput1(input);
    }

    @Test
    public void deleteInput() {
        Integer id = 2;
        inputDao.deleteById(id);
        flush();
        assertNull("input was not deleted", inputDao.findById(id));
    }

    @Test
    public void updateInput() {
        Integer id = 3;
        Input input = inputDao.findById(id);
        assertNotNull("input was not found before update", input);
        assertEquals("testInput3", input.getName());
        input.setName("newName");
        inputDao.update(input);
        input = inputDao.findById(id);
        assertNotNull("input was not found after update", input);
        assertEquals("newName", input.getName());
    }

    @Test
    public void findInputByCriteria() {
        Input model = new Input();
        model.setName("testInput1");
        CriteriaForm<Input> criteria = new CriteriaForm<Input>(model);
        List<Input> inputs = inputDao.findByCriteria(criteria);
        assertEquals("wrong number of inputs found", 1, inputs.size());
        Input input = inputs.get(0);
        DaoTestHelper.checkInput1(input);
    }

}
