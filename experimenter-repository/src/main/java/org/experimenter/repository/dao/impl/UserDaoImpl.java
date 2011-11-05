package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.UserDao;
import org.experimenter.repository.model.User;

public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	@Override
	public Class<User> getModelClass() {
		return User.class;
	}

}
