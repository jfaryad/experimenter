package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.User;

/**
 * The data access object for the Application entity. Extends {@link BaseDao}.
 * 
 * @author jfaryad
 * 
 */
public interface ApplicationDao extends BaseDao<Application> {

    /**
     * Find all applications belonging to the given program.
     * 
     * @param program
     *            the program to search by
     * @return a list of applications
     */
    public List<Application> findApplicationsByProgram(Program program);

    /**
     * Find all applications belonging to any user group the given user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of applications
     */
    public List<Application> findApplicationsByUser(User user);
}
