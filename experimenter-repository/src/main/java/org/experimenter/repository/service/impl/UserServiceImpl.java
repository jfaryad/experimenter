package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserService;

public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Override
    protected void deleteDependencies(User user) {
        junctionDao.removeUserFromUserGroup(user, null);
    }

    @Override
    public void addUserToUserGroup(User user, UserGroup userGroup) {
        junctionDao.addUserToUserGroup(user, userGroup);
    }

    @Override
    public void removeUserFromUserGroup(User user, UserGroup userGroup) {
        junctionDao.removeUserFromUserGroup(user, userGroup);
    }

    @Override
    public List<User> findUsersByUserGroup(UserGroup userGroup) {
        // TODO Auto-generated method stub
        return null;
    }

}
