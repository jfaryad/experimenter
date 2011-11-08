package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.model.Input;
import org.experimenter.repository.model.ProblemType;
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
public class InputDaoTest extends AbstractTest {

    @Autowired
    private InputDao inputDao;

    @Test
    public void insertInput() {
        Input input = new Input();
        input.setName("sampleInput");
        input.setData("input for testing");
        ProblemType problem = new ProblemType();
        problem.setProblemId(1);
        input.setProblem(problem);
        inputDao.insert(input);
        assertNotNull("inputId is null after insert", input.getInputId());
        assertEquals("sampleInput", input.getName());
        assertEquals("input for testing", input.getData());
        assertEquals(1, input.getProblem().getProblemId().intValue());
    }

    @Test
    public void findInputById() {
        Integer id = 1;
        Input input = inputDao.findById(id);
        assertNotNull("input not found", input);
        assertEquals("testInput1", input.getName());
        assertEquals("data1", input.getData());
        assertEquals(1, input.getProblem().getProblemId().intValue());
    }

    @Test
    public void deleteInput() {
        Integer id = 2;
        inputDao.deleteById(id);
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
        assertNotNull("input not found", input);
        assertEquals("testInput1", input.getName());
        assertEquals("data1", input.getData());
        assertEquals(1, input.getProblem().getProblemId().intValue());
    }

}
