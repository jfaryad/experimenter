package org.experimenter.repository.service.impl;

import org.experimenter.repository.entity.ProblemType;

public class ProblemTypeServiceImpl extends AbstractService<ProblemType> {

    @Override
    protected void deleteDependencies(ProblemType ProblemType) {
        junctionDao.removeProblemTypeFromProblemTypeGroup(ProblemType, null);
    }

}
