package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.CriteriaForm;

public interface UserGroupService {

    /**
     * Saves the given {@link UserGroup} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param userGroup
     *            the userGroup to save
     */
    public void saveUpdate(UserGroup userGroup);

    /**
     * Finds the {@link UserGroup} with the given id.
     * 
     * @param id
     *            the identifier of the userGroup
     * @return the userGroup with the given id or null, if such an entry doesn't exist in the database.
     */
    public UserGroup findById(Integer id);

    /**
     * Finds the {@link UserGroup} that matches the properties set in the input form.
     * 
     * @param userGroup
     *            a search form with the properties you want to search by
     * @return a list of userGroups that match the example
     */
    public List<UserGroup> findByExample(UserGroup userGroup);

    /**
     * Finds the {@link UserGroup} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of userGroups that match the criteria
     */
    public List<UserGroup> findByCriteria(CriteriaForm<UserGroup> criteria);

    /**
     * Deletes the given {@link UserGroup} from the database, including all existing links to other tables.
     * 
     * @param userGroup
     *            the userGroup to delete
     */
    public void delete(UserGroup userGroup);

    /**
     * Deletes the given list of {@link UserGroup}s from the database, including all existing links to other tables.
     * 
     * @param userGroups
     *            the userGroups to delete
     */
    public void delete(List<UserGroup> userGroups);

    /**
     * Find all userGroups the user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of userGroups
     */
    public List<UserGroup> findUserGroupsByUser(User user);

}
