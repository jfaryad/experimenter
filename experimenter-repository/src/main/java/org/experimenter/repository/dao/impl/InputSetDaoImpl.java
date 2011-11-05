package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.InputSetDao;
import org.experimenter.repository.model.InputSet;

public class InputSetDaoImpl extends AbstractDaoImpl<InputSet> implements InputSetDao {

	@Override
	public Class<InputSet> getModelClass() {
		return InputSet.class;
	}

}
