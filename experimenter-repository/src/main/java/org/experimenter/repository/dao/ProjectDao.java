package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.User;

/**
 * The data access object for the Project entity. Extends {@link BaseDao}.
 * 
 * @author jfaryad
 * 
 */
public interface ProjectDao extends BaseDao<Project> {

    /**
     * Find all projects belonging to any user group the given user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of projects
     */
    public List<Project> findProjectsByUser(User user);

}
