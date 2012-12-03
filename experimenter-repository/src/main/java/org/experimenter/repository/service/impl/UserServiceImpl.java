package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.UserDao;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserService;

public class UserServiceImpl extends AbstractService<User, UserDao> implements UserService {

    @Override
    protected void deleteDependencies(User user) {
        // nothing to do
    }

    @Override
    public void addUserToUserGroup(User user, UserGroup userGroup) {
        checkIdNotNull(user);
        checkIdNotNull(userGroup);
        user.getUserGroups().add(userGroup);
        userGroup.getUsers().add(user);
    }

    @Override
    public void removeUserFromUserGroup(User user, UserGroup userGroup) {
        checkIdNotNull(user);
        checkIdNotNull(userGroup);
        user.getUserGroups().remove(userGroup);
        userGroup.getUsers().remove(user);
    }

    @Override
    public List<User> findUsersByUserGroup(UserGroup userGroup) {
        checkIdNotNull(userGroup);
        return userGroup.getUsers();
    }

    @Override
    protected boolean hasDependencies(User user) {
        return false;
    }

}
