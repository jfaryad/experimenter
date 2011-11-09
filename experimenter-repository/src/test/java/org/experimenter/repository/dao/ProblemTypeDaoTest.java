package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.ModelCriteria;
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
public class ProblemTypeDaoTest extends AbstractTest {

    @Autowired
    private ProblemTypeDao problemTypeDao;

    @Test
    public void insertProblemType() {
        ProblemType problem = new ProblemType();
        problem.setName("3-sat");
        problem.setDescription("k-sat for 3 literals");
        problemTypeDao.insert(problem);
        assertNotNull("problemId is null after insert", problem.getProblemId());
        assertEquals("3-sat", problem.getName());
        assertEquals("k-sat for 3 literals", problem.getDescription());
    }

    @Test
    public void findProblemTypeById() {
        Integer id = 1;
        ProblemType problem = problemTypeDao.findById(id);
        assertNotNull("problem not found", problem);
        assertEquals("3-SAT", problem.getName());
        assertEquals("you know what it means", problem.getDescription());
    }

    @Test
    public void deleteProblemType() {
        Integer id = 3;
        problemTypeDao.deleteById(id);
        assertNull("problem was not deleted", problemTypeDao.findById(id));
    }

    @Test
    public void updateProblemType() {
        Integer id = 2;
        ProblemType problem = problemTypeDao.findById(id);
        assertNotNull("problem was not found before update", problem);
        assertEquals("4-SAT", problem.getName());
        problem.setName("newProblem");
        problemTypeDao.update(problem);
        problem = problemTypeDao.findById(id);
        assertNotNull("problem was not found after update", problem);
        assertEquals("newProblem", problem.getName());
    }

    @Test
    public void findProblemTypeByCriteria() {
        ProblemType model = new ProblemType();
        model.setName("3-SAT");
        ModelCriteria<ProblemType> criteria = new ModelCriteria<ProblemType>(model);
        List<ProblemType> problems = problemTypeDao.findByCriteria(criteria);
        assertEquals("wrong number of problems found", 1, problems.size());
        ProblemType problem = problems.get(0);
        assertNotNull("problem not found", problem);
        assertEquals("3-SAT", problem.getName());
        assertEquals("you know what it means", problem.getDescription());
    }

}
