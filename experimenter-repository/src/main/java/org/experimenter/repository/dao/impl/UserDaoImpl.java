package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.UserDao;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;

public class UserDaoImpl extends AbstractBaseDaoImpl<User> implements UserDao {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public void removeFromAssociations(User user) {
        for (UserGroup userGroup : user.getUserGroups())
            userGroup.getUsers().remove(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUsersByUserGroup(UserGroup userGroup) {
        logger.debug(">> findUsersByUserGroup: " + userGroup);
        List<User> users = getSession().getNamedQuery(User.Q_GET_BY_USERGROUP).setEntity("userGroup", userGroup).list();
        logger.debug("<< findUsersByUserGroup: number of users found:" + users.size());
        return users;
    }

}
