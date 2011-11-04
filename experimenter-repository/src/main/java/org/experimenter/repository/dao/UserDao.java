package org.experimenter.repository.dao;

import org.experimenter.repository.model.User;

public interface UserDao extends BaseDao<User> {

    @Override
    public void insert(User user);
}
