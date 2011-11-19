package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.ProjectDao;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.form.SimpleForm;
import org.sqlproc.engine.SqlSession;

public class ProjectDaoImpl extends AbstractBaseDaoImpl<Project> implements ProjectDao {

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }

    @Override
    public String getTableName() {
        return Constants.PROJECT;
    }

    @Override
    public List<Project> findProjectsByInputSet(InputSet inputSet) {
        logger.debug(">> findProjectsByInputSet: " + inputSet);
        SqlSession session = getSqlSession();
        String engineName = "FIND_PROJECT_BY_INPUT_SET";
        List<Project> projects = getQueryEngine(engineName).query(session, getEntityClass(),
                new SimpleForm(inputSet.getId()));
        logger.debug("<< findProjectsByInputSet: number of projects found:" + projects.size());
        return projects;
    }

}
