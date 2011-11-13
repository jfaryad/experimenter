package org.experimenter.repository.dao;

import java.util.List;

import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;

public interface ProjectDao extends BaseDao<Project> {

    /**
     * Find all projects the inputSet belongs to.
     * 
     * @param inputSet
     *            the inputSet to search by
     * @return a list of projects
     */
    public List<Project> findProjectsByInputSet(InputSet inputSet);

}
