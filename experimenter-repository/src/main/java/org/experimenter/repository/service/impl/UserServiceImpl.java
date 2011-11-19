package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.UserDao;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserService;

public class UserServiceImpl extends AbstractService<User, UserDao> implements UserService {

    @Override
    protected void deleteDependencies(User user) {
        junctionDao.removeUserFromUserGroup(user, null);
    }

    @Override
    public void addUserToUserGroup(User user, UserGroup userGroup) {
        checkIdNotNull(user);
        checkIdNotNull(userGroup);
        junctionDao.addUserToUserGroup(user, userGroup);
    }

    @Override
    public void removeUserFromUserGroup(User user, UserGroup userGroup) {
        checkIdNotNull(user);
        checkIdNotNull(userGroup);
        junctionDao.removeUserFromUserGroup(user, userGroup);
    }

    @Override
    public List<User> findUsersByUserGroup(UserGroup userGroup) {
        checkIdNotNull(userGroup);
        return baseDao.findUsersByUserGroup(userGroup);
    }

}
