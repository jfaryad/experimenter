package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ComputerDao;
import org.experimenter.repository.model.Computer;

public class ComputerDaoImpl extends AbstractBaseDaoImpl<Computer> implements ComputerDao {

	@Override
	public Class<Computer> getModelClass() {
		return Computer.class;
	}

}
