package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ProjectDao;
import org.experimenter.repository.model.Project;

public class ProjectDaoImpl extends AbstractBaseDaoImpl<Project> implements ProjectDao {

	@Override
	public Class<Project> getModelClass() {
		return Project.class;
	}

}
