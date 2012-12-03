package org.experimenter.repository.service.impl;

import org.experimenter.repository.dao.ProblemTypeDao;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.ProblemTypeService;

public class ProblemTypeServiceImpl extends AbstractService<ProblemType, ProblemTypeDao> implements ProblemTypeService {

    @Override
    protected void deleteDependencies(ProblemType problemType) {
        projectService.delete(problemType.getProjects());
        inputService.delete(problemType.getInputs());
        inputSetService.delete(problemType.getInputSets());
    }

    @Override
    protected boolean hasDependencies(ProblemType problemType) {
        return !problemType.getProjects().isEmpty()
                || !problemType.getInputs().isEmpty()
                || !problemType.getInputSets().isEmpty();
    }

}
