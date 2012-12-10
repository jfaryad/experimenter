package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.CriteriaForm;

/**
 * The service taking care of {@link User} related operations.
 * 
 * @author jfaryad
 */
public interface UserService extends EntityService<User> {

    /**
     * Saves the given {@link User} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param user
     *            the user to save
     */
    @Override
    public void saveUpdate(User user);

    /**
     * Finds the {@link User} with the given id.
     * 
     * @param id
     *            the identifier of the user
     * @return the user with the given id or null, if such an entry doesn't exist in the database.
     */
    @Override
    public User findById(Integer id);

    /**
     * Finds the {@link User} that matches the properties set in the input form.
     * 
     * @param user
     *            a search form with the properties you want to search by
     * @return a list of users that match the example
     */
    @Override
    public List<User> findByExample(User user);

    /**
     * Finds the {@link User} that matches the properties set in the input form. In addition, it is possible to specify
     * the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of users that match the criteria
     */
    @Override
    public List<User> findByCriteria(CriteriaForm<User> criteria);

    /**
     * Deletes the given {@link User} from the database, including all existing links to other tables.
     * 
     * @param user
     *            the user to delete
     */
    @Override
    public void delete(User user);

    /**
     * Deletes the given list of {@link User}s from the database, including all existing links to other tables.
     * 
     * @param users
     *            the users to delete
     */
    @Override
    public void delete(List<User> users);

    /**
     * Adds a {@link User} to a {@link UserGroup}
     * 
     * @param user
     *            the user to add
     * @param userGroup
     *            the group to add to
     */
    public void addUserToUserGroup(User user, UserGroup userGroup);

    /**
     * Removes a {@link User} from a {@link UserGroup}
     * 
     * @param user
     *            the user to remove
     * @param userGroup
     *            the group to remove from
     */
    public void removeUserFromUserGroup(User user, UserGroup userGroup);

    /**
     * Find all users belonging to the given userGroup.
     * 
     * @param userGroup
     *            the userGroup to search by
     * @return a list of users
     */
    public List<User> findUsersByUserGroup(UserGroup userGroup);
}
