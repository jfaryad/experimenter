package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.User;

public interface ProgramDao extends BaseDao<Program> {

    /**
     * Find all programs belonging to any user group the given user belongs to.
     * 
     * @param user
     *            the user to search by
     * @return a list of programs
     */
    public List<Program> findProgramsByUser(User user);

}
