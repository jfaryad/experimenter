package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;

/**
 * The data access object for the User entity. Extends {@link BaseDao}.
 * 
 * @author jfaryad
 * 
 */
public interface UserDao extends BaseDao<User> {

    /**
     * Find all users belonging to the given userGroup.
     * 
     * @param userGroup
     *            the userGroup to search by
     * @return a list of users
     */
    public List<User> findUsersByUserGroup(UserGroup userGroup);

    /**
     * Finds the user with the given credentials.
     * 
     * @param login
     *            login of the user
     * @param password
     *            user's password
     * @return the user or null, if no such entity exists
     */
    public User findUserByLoginAndPassword(String login, String password);

}
