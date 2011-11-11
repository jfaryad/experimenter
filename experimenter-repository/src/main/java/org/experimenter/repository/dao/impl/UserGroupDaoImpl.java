package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.UserGroupDao;
import org.experimenter.repository.model.UserGroup;

public class UserGroupDaoImpl extends AbstractBaseDaoImpl<UserGroup> implements UserGroupDao {

	@Override
	public Class<UserGroup> getModelClass() {
		return UserGroup.class;
	}

}
