package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;

public class InputSetDaoTest extends AbstractDaoTest {

    @Test
    public void insertInputSet() {
        InputSet inputSet = new InputSet();
        inputSet.setName("set1");
        inputSet.setDescription("basic input set");
        ProblemType problem = new ProblemType();
        problem.setId(1);
        inputSet.setProblem(problem);
        inputSetDao.insert(inputSet);
        assertNotNull("setId is null after insert", inputSet.getId());
        assertEquals("set1", inputSet.getName());
        assertEquals("basic input set", inputSet.getDescription());
        assertEquals(1, inputSet.getProblem().getId().intValue());
    }

    @Test
    public void findInputSetById() {
        Integer id = 1;
        InputSet inputSet = inputSetDao.findById(id);
        DaoTestHelper.checkInputSet1(inputSet);
    }

    @Test
    public void deleteInputSet() {
        Integer id = 2;
        inputSetDao.deleteById(id);
        flush();
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
        CriteriaForm<InputSet> criteria = new CriteriaForm<InputSet>(model);
        List<InputSet> inputSets = inputSetDao.findByCriteria(criteria);
        assertEquals("wrong number of inputSets found", 1, inputSets.size());
        InputSet inputSet = inputSets.get(0);
        DaoTestHelper.checkInputSet1(inputSet);
    }

}
