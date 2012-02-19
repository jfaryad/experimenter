package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.util.DaoTestHelper;
import org.junit.Test;

public class ExperimentServiceTest extends AbstractServiceTest {

    @Test
    public void findExperimentsByApplicationTest() {
        Application application = applicationService.findById(1);
        List<Experiment> experiments = experimentService.findExperimentsByApplication(application);
        assertEquals("wrong number of experiments found", 1, experiments.size());
        Experiment experiment = experiments.get(0);
        assertNotNull("experiment not found", experiment);
        DaoTestHelper.checkExperiment1(experiment);
    }

    @Test
    public void findExperimentsByInputSetTest() {
        InputSet inputSet = inputSetService.findById(1);
        List<Experiment> experiments = experimentService.findExperimentsByInputSet(inputSet);
        assertEquals("wrong number of experiments found", 1, experiments.size());
        Experiment experiment = experiments.get(0);
        assertNotNull("experiment not found", experiment);
        DaoTestHelper.checkExperiment1(experiment);
    }

    @Test
    public void findExperimentsByConnectionFarmTest() {
        ConnectionFarm connectionFarm = connectionFarmService.findById(1);
        List<Experiment> experiments = experimentService.findExperimentsByConnectionFarm(connectionFarm);
        assertEquals("wrong number of experiments found", 1, experiments.size());
        Experiment experiment = experiments.get(0);
        assertNotNull("experiment not found", experiment);
        DaoTestHelper.checkExperiment1(experiment);
    }

}
