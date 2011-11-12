package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.ProjectDao;
import org.experimenter.repository.entity.Project;

public class ProjectDaoImpl extends AbstractBaseDaoImpl<Project> implements ProjectDao {

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }

    @Override
    public String getTableName() {
        return Constants.PROJECT;
    }

}
