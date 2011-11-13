package org.experimenter.repository.service.impl;

import org.experimenter.repository.entity.InputSet;

public class InputSetServiceImpl extends AbstractService<InputSet> {

    @Override
    protected void deleteDependencies(InputSet InputSet) {
        junctionDao.removeInputSetFromInputSetGroup(InputSet, null);
    }

}
