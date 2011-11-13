package org.experimenter.repository.service.impl;

import org.experimenter.repository.entity.Input;

public class InputServiceImpl extends AbstractService<Input> {

    @Override
    protected void deleteDependencies(Input Input) {
        junctionDao.removeInputFromInputGroup(Input, null);
    }

}
