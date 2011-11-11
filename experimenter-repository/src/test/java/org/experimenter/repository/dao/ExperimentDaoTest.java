package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.model.Application;
import org.experimenter.repository.model.Experiment;
import org.experimenter.repository.model.Project;
import org.experimenter.repository.util.DaoTestHelper;
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
public class ExperimentDaoTest {

    @Autowired
    private ExperimentDao experimentDao;

    @Test
    public void insertExperiment() {
        Experiment experiment = new Experiment();
        experiment.setName("exp1");
        experiment.setDescription("exp1 - sat");
        Project project = new Project();
        project.setProjectId(1);
        experiment.setProject(project);
        Application application = new Application();
        application.setApplicationId(1);
        experiment.setApplication(application);
        experimentDao.insert(experiment);
        assertNotNull("experimentId is null after insert", experiment.getExperimentId());
        assertEquals("exp1", experiment.getName());
        assertEquals("exp1 - sat", experiment.getDescription());
        assertEquals(1, experiment.getApplication().getApplicationId().intValue());
        assertEquals(1, experiment.getProject().getProjectId().intValue());
    }

    @Test
    public void findExperimentById() {
        Integer id = 1;
        Experiment experiment = experimentDao.findById(id);
        DaoTestHelper.checkExperiment1(experiment);
    }

    @Test
    public void deleteExperiment() {
        Integer id = 3;
        experimentDao.deleteById(id);
        assertNull("experiment was not deleted", experimentDao.findById(id));
    }

    @Test
    public void updateExperiment() {
        Integer id = 3;
        Experiment experiment = experimentDao.findById(id);
        assertNotNull("experiment was not found before update", experiment);
        assertEquals("experiment3", experiment.getName());
        experiment.setName("newName");
        experimentDao.update(experiment);
        experiment = experimentDao.findById(id);
        assertNotNull("experiment was not found after update", experiment);
        assertEquals("newName", experiment.getName());
    }

    @Test
    public void findExperimentByCriteria() {
        Experiment model = new Experiment();
        model.setName("experiment1");
        CriteriaForm<Experiment> criteria = new CriteriaForm<Experiment>(model);
        List<Experiment> experiments = experimentDao.findByCriteria(criteria);
        assertEquals("wrong number of experiments found", 1, experiments.size());
        Experiment experiment = experiments.get(0);
        DaoTestHelper.checkExperiment1(experiment);
    }

}
