package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.UserGroupDao;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;

/**
 * Default implementation of {@link UserGroupDao}
 * 
 * @author jfaryad
 * 
 */
public class UserGroupDaoImpl extends AbstractBaseDaoImpl<UserGroup> implements UserGroupDao {

    @Override
    public Class<UserGroup> getEntityClass() {
        return UserGroup.class;
    }

    @Override
    public void removeFromAssociations(UserGroup userGroup) {
        for (User user : userGroup.getUsers())
            user.getUserGroups().remove(userGroup);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserGroup> findUserGroupsByUser(User user) {
        logger.debug(">> findUserGroupsByUser: " + user);
        List<UserGroup> userGroups = getSession().getNamedQuery(UserGroup.Q_GET_BY_USER).setEntity("user", user).list();
        logger.debug("<< findUserGroupsByUser: number of userGroups found:" + userGroups.size());
        return userGroups;
    }
}
