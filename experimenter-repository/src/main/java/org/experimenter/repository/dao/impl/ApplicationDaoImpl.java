package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ApplicationDao;
import org.experimenter.repository.model.Application;

public class ApplicationDaoImpl extends AbstractDaoImpl<Application> implements ApplicationDao {

	@Override
	public Class<Application> getModelClass() {
		return Application.class;
	}

}
