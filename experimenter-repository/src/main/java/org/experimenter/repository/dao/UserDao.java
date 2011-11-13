package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;

public interface UserDao extends BaseDao<User> {

    /**
     * Find all users belonging to the given userGroup.
     * 
     * @param userGroup
     *            the userGroup to search by
     * @return a list of users
     */
    public List<User> findUsersByUserGroup(UserGroup userGroup);

}
