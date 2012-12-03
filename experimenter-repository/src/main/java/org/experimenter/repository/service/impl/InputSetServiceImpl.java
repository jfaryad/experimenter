package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.InputSetDao;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.InputSetService;

public class InputSetServiceImpl extends AbstractService<InputSet, InputSetDao> implements InputSetService {

    @Override
    protected void deleteDependencies(InputSet inputSet) {
        // nothing to do
    }

    @Override
    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment) {
        inputSet.getExperiments().add(experiment);
        experiment.getInputSets().add(inputSet);

    }

    @Override
    public void addInputSetToProject(InputSet inputSet, Project project) {
        inputSet.getProjects().add(project);
        project.getInputSets().add(inputSet);

    }

    @Override
    public void removeInputSetFromExperiment(InputSet inputSet, Experiment experiment) {
        inputSet.getExperiments().remove(experiment);
        experiment.getInputSets().remove(inputSet);

    }

    @Override
    public void removeInputSetFromProject(InputSet inputSet, Project project) {
        inputSet.getProjects().remove(project);
        project.getInputSets().remove(inputSet);

    }

    @Override
    public List<InputSet> findInputSetsByProblemType(ProblemType problemType) {
        checkIdNotNull(problemType);
        return problemType.getInputSets();
    }

    @Override
    public List<InputSet> findInputSetsByExperiment(Experiment experiment) {
        checkIdNotNull(experiment);
        return experiment.getInputSets();
    }

    @Override
    public List<InputSet> findInputSetsByInput(Input input) {
        checkIdNotNull(input);
        return input.getInputSets();
    }

    @Override
    public List<InputSet> findInputSetsByProject(Project project) {
        checkIdNotNull(project);
        return project.getInputSets();
    }

    @Override
    protected boolean hasDependencies(InputSet inputSet) {
        return !inputSet.getProjects().isEmpty() || !inputSet.getExperiments().isEmpty();
    }

}
