package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;

public interface ApplicationDao extends BaseDao<Application> {

    /**
     * Find all applications belonging to the given program.
     * 
     * @param program
     *            the program to search by
     * @return a list of applications
     */
    public List<Application> findApplicationsByProgram(Program program);
}
