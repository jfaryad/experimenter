package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.ModelCriteria;
import org.experimenter.repository.model.InputSet;
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
public class InputSetDaoTest extends AbstractTest {

    @Autowired
    private InputSetDao inputSetDao;

    @Test
    public void insertInputSet() {
        InputSet inputSet = new InputSet();
        inputSet.setName("set1");
        inputSet.setDescription("basic input set");
        ProblemType problem = new ProblemType();
        problem.setProblemId(1);
        inputSet.setProblem(problem);
        inputSetDao.insert(inputSet);
        assertNotNull("setId is null after insert", inputSet.getInputSetId());
        assertEquals("set1", inputSet.getName());
        assertEquals("basic input set", inputSet.getDescription());
        assertEquals(1, inputSet.getProblem().getProblemId().intValue());
    }

    @Test
    public void findInputSetById() {
        Integer id = 1;
        InputSet inputSet = inputSetDao.findById(id);
        assertNotNull("inputSet not found", inputSet);
        assertEquals("testInputSet1", inputSet.getName());
        assertEquals("basic set of inputs", inputSet.getDescription());
        assertEquals(1, inputSet.getProblem().getProblemId().intValue());
    }

    @Test
    public void deleteInputSet() {
        Integer id = 2;
        inputSetDao.deleteById(id);
        assertNull("inputSet was not deleted", inputSetDao.findById(id));
    }

    @Test
    public void updateInputSet() {
        Integer id = 3;
        InputSet inputSet = inputSetDao.findById(id);
        assertNotNull("inputSet was not found before update", inputSet);
        assertEquals("testInputSet3", inputSet.getName());
        inputSet.setName("newName");
        inputSetDao.update(inputSet);
        inputSet = inputSetDao.findById(id);
        assertNotNull("inputSet was not found after update", inputSet);
        assertEquals("newName", inputSet.getName());
    }

    @Test
    public void findInputSetByCriteria() {
        InputSet model = new InputSet();
        model.setName("testInputSet1");
        ModelCriteria<InputSet> criteria = new ModelCriteria<InputSet>(model);
        List<InputSet> inputSets = inputSetDao.findByCriteria(criteria);
        assertEquals("wrong number of inputSets found", 1, inputSets.size());
        InputSet inputSet = inputSets.get(0);
        assertNotNull("inputSet not found", inputSet);
        assertEquals("testInputSet1", inputSet.getName());
        assertEquals("basic set of inputs", inputSet.getDescription());
        assertEquals(1, inputSet.getProblem().getProblemId().intValue());
    }

}
