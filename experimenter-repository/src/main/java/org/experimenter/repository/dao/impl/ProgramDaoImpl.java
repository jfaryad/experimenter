package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.ProgramDao;
import org.experimenter.repository.entity.Program;

public class ProgramDaoImpl extends AbstractBaseDaoImpl<Program> implements ProgramDao {

    @Override
    public Class<Program> getEntityClass() {
        return Program.class;
    }

    @Override
    public String getTableName() {
        return Constants.PROGRAM;
    }

}
