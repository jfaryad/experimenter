package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;

public class ProblemTypeDaoTest extends AbstractDaoTest {

    @Test
    public void insertProblemType() {
        ProblemType problem = new ProblemType();
        problem.setName("3-sat");
        problem.setDescription("k-sat for 3 literals");
        problemTypeDao.insert(problem);
        assertNotNull("problemId is null after insert", problem.getId());
        assertEquals("3-sat", problem.getName());
        assertEquals("k-sat for 3 literals", problem.getDescription());
    }

    @Test
    public void findProblemTypeById() {
        Integer id = 1;
        ProblemType problem = problemTypeDao.findById(id);
        DaoTestHelper.checkProblem1(problem);
    }

    @Test
    public void deleteProblemType() {
        Integer id = 3;
        problemTypeDao.deleteById(id);
        flush();
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
        CriteriaForm<ProblemType> criteria = new CriteriaForm<ProblemType>(model);
        List<ProblemType> problems = problemTypeDao.findByCriteria(criteria);
        assertEquals("wrong number of problems found", 1, problems.size());
        ProblemType problem = problems.get(0);
        DaoTestHelper.checkProblem1(problem);
    }

}
