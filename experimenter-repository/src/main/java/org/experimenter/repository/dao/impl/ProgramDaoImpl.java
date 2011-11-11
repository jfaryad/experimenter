package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ProgramDao;
import org.experimenter.repository.model.Program;

public class ProgramDaoImpl extends AbstractBaseDaoImpl<Program> implements ProgramDao {

	@Override
	public Class<Program> getModelClass() {
		return Program.class;
	}

}
