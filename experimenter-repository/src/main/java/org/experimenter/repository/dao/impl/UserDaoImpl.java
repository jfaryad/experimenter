package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.UserDao;
import org.experimenter.repository.entity.User;

public class UserDaoImpl extends AbstractBaseDaoImpl<User> implements UserDao {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public String getTableName() {
        return Constants.USER;
    }

}
