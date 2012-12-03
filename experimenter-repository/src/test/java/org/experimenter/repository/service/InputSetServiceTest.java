package org.experimenter.repository.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.testutil.DaoTestHelper;
import org.junit.Test;

public class InputSetServiceTest extends AbstractServiceTest {

    @Test
    public void findInputSetsByProblemTypeTest() {
        ProblemType problem = problemTypeService.findById(1);
        List<InputSet> inputSets = inputSetService.findInputSetsByProblemType(problem);
        assertEquals("wrong number of inputSets found", 1, inputSets.size());
        InputSet inputSet = inputSets.get(0);
        assertNotNull("inputSet not found", inputSet);
        DaoTestHelper.checkInputSet1(inputSet);
    }

    @Test
    public void findInputSetsByExperimentTest() {
        Experiment experiment = experimentService.findById(1);
        List<InputSet> inputSets = inputSetService.findInputSetsByExperiment(experiment);
        assertEquals("wrong number of inputSets found", 1, inputSets.size());
        InputSet inputSet = inputSets.get(0);
        assertNotNull("inputSet not found", inputSet);
        DaoTestHelper.checkInputSet1(inputSet);
    }

    @Test
    public void findInputSetsByInputTest() {
        Input input = inputService.findById(1);
        List<InputSet> inputSets = inputSetService.findInputSetsByInput(input);
        assertEquals("wrong number of inputSets found", 1, inputSets.size());
        InputSet inputSet = inputSets.get(0);
        assertNotNull("inputSet not found", input);
        DaoTestHelper.checkInputSet1(inputSet);
    }

    @Test
    public void findInputSetsByProjectTest() {
        Project project = projectService.findById(1);
        List<InputSet> inputSets = inputSetService.findInputSetsByProject(project);
        assertEquals("wrong number of inputSets found", 1, inputSets.size());
        InputSet inputSet = inputSets.get(0);
        assertNotNull("inputSet not found", inputSet);
        DaoTestHelper.checkInputSet1(inputSet);
    }

    @Test
    public void addInputSetToExperimentTest() {
        Experiment experiment = experimentService.findById(3);
        assertEquals("experiment is not supposed to have associated inputSets", 0, inputSetService
                .findInputSetsByExperiment(experiment).size());
        InputSet inputSet = inputSetService.findById(2);
        inputSetService.addInputSetToExperiment(inputSet, experiment);
        assertEquals("inputSet hasn't been added to experiment", 1,
                inputSetService.findInputSetsByExperiment(experiment).size());
    }

    @Test
    public void removeInputSetFromExperimentTest() {
        Experiment experiment = experimentService.findById(4);
        assertEquals("experiment is supposed to have an associated inputSet", 1, inputSetService
                .findInputSetsByExperiment(experiment).size());
        InputSet inputSet = inputSetService.findById(4);
        inputSetService.removeInputSetFromExperiment(inputSet, experiment);
        assertEquals("inputSet hasn't been removed from experiment", 0,
                inputSetService.findInputSetsByExperiment(experiment).size());
    }

    @Test
    public void addInputSetToProjectTest() {
        Project project = projectService.findById(4);
        assertEquals("project is not supposed to have associated inputSets", 0,
                inputSetService.findInputSetsByProject(project).size());
        InputSet inputSet = inputSetService.findById(1);
        inputSetService.addInputSetToProject(inputSet, project);
        assertEquals("inputSet hasn't been added to project", 1, inputSetService.findInputSetsByProject(project).size());
    }

    @Test
    public void removeInputSetFromProjectTest() {
        Project project = projectService.findById(5);
        assertEquals("project is supposed to have an associated inputSet", 1,
                inputSetService.findInputSetsByProject(project).size());
        InputSet inputSet = inputSetService.findById(3);
        inputSetService.removeInputSetFromProject(inputSet, project);
        assertEquals("inputSet hasn't been removed from project", 0, inputSetService.findInputSetsByProject(project)
                .size());
    }
}
