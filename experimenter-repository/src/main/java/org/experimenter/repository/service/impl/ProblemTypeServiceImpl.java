package org.experimenter.repository.service.impl;

import org.experimenter.repository.dao.ProblemTypeDao;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.ProblemTypeService;

public class ProblemTypeServiceImpl extends AbstractService<ProblemType, ProblemTypeDao> implements ProblemTypeService {

    @Override
    protected void deleteDependencies(ProblemType problemType) {
        projectService.delete(projectService.findProjectsByProblemType(problemType));
        inputService.delete(inputService.findInputsByProblemType(problemType));
        inputSetService.delete(inputSetService.findInputSetsByProblemType(problemType));
    }

}
