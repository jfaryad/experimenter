package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;

public interface UserGroupDao extends BaseDao<UserGroup> {

    /**
     * Find all userGroups the user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of userGroups
     */
    public List<UserGroup> findUserGroupsByUser(User user);

}
