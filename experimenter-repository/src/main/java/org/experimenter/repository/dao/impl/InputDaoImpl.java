package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.model.Input;

public class InputDaoImpl extends AbstractDaoImpl<Input> implements InputDao {

	@Override
	public Class<Input> getModelClass() {
		return Input.class;
	}

}
