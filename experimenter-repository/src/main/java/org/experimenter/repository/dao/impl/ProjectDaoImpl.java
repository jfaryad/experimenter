package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ProjectDao;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;

public class ProjectDaoImpl extends AbstractBaseDaoImpl<Project> implements ProjectDao {

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }

    @Override
    public void removeFromAssociations(Project project) {
        project.getUserGroup().getProjects().remove(project);
        project.getProblem().getProjects().remove(project);
        for (InputSet inputSet : project.getInputSets()) {
            inputSet.getProjects().remove(project);
        }
    }

    // @Override
    // public List<Project> findProjectsByInputSet(InputSet inputSet) {
    // logger.debug(">> findProjectsByInputSet: " + inputSet);
    // SqlSession session = getSqlSession();
    // String engineName = "FIND_PROJECT_BY_INPUT_SET";
    // List<Project> projects = getQueryEngine(engineName).query(session, getEntityClass(),
    // new SimpleForm(inputSet.getId()));
    // logger.debug("<< findProjectsByInputSet: number of projects found:" + projects.size());
    // return projects;
    // }

}
