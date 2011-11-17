package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.InputSetDao;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.service.InputSetService;

public class InputSetServiceImpl extends AbstractService<InputSet, InputSetDao> implements InputSetService {

    @Override
    protected void deleteDependencies(InputSet inputSet) {
        junctionDao.removeInputSetFromProject(inputSet, null);
        junctionDao.removeInputFromInputSet(null, inputSet);
        junctionDao.removeInputSetFromExperiment(inputSet, null);
    }

    @Override
    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment) {
        junctionDao.addInputSetToExperiment(inputSet, experiment);

    }

    @Override
    public void addInputSetToProject(InputSet inputSet, Project project) {
        junctionDao.addInputSetToProject(inputSet, project);

    }

    @Override
    public void removeInputSetFromExperiment(InputSet inputSet, Experiment experiment) {
        junctionDao.removeInputSetFromExperiment(inputSet, experiment);

    }

    @Override
    public void removeInputSetFromProject(InputSet inputSet, Project project) {
        junctionDao.removeInputSetFromProject(inputSet, project);

    }

    @Override
    public List<InputSet> findInputSetsByProblemType(ProblemType problemType) {
        checkIdNotNull(problemType);
        InputSet inputSet = new InputSet();
        inputSet.setProblem(problemType);
        CriteriaForm<InputSet> criteria = new CriteriaForm<InputSet>(inputSet);
        return baseDao.findByCriteria(criteria);
    }

    @Override
    public List<InputSet> findInputSetsByExperiment(Experiment experiment) {
        checkIdNotNull(experiment);
        return baseDao.findInputSetsByExperiment(experiment);
    }

    @Override
    public List<InputSet> findInputSetsByInput(Input input) {
        checkIdNotNull(input);
        return baseDao.findInputSetsByInput(input);
    }

    @Override
    public List<InputSet> findInputSetsByProject(Project project) {
        checkIdNotNull(project);
        return baseDao.findInputSetsByProject(project);
    }

}
