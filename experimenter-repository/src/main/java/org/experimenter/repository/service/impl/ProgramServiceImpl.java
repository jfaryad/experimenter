package org.experimenter.repository.service.impl;

import org.experimenter.repository.entity.Program;

public class ProgramServiceImpl extends AbstractService<Program> {

    @Override
    protected void deleteDependencies(Program Program) {
        junctionDao.removeProgramFromProgramGroup(Program, null);
    }

}
