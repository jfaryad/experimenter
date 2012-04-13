package org.experimenter.repository.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;

public class ExperimentDaoTest extends AbstractDaoTest {

    @Test
    public void insertExperiment() {
        Experiment experiment = new Experiment();
        experiment.setName("exp1");
        experiment.setDescription("exp1 - sat");
        experiment.setIsActive(false);
        Application application = new Application();
        application.setId(1);
        experiment.setApplication(application);
        experimentDao.insert(experiment);
        assertNotNull("experimentId is null after insert", experiment.getId());
        assertEquals("exp1", experiment.getName());
        assertEquals("exp1 - sat", experiment.getDescription());
        assertEquals(1, experiment.getApplication().getId().intValue());
    }

    @Test
    public void findExperimentById() {
        Integer id = 1;
        Experiment experiment = experimentDao.findById(id);
        DaoTestHelper.checkExperiment1(experiment);
    }

    @Test
    public void deleteExperiment() {
        Integer id = 2;
        assertEquals(2, applicationDao.findById(2).getExperiments().size());
        experimentDao.deleteById(id);
        flush();
        assertNull("experiment was not deleted", experimentDao.findById(id));
        assertEquals(1, applicationDao.findById(2).getExperiments().size());
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
