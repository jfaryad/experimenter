package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.ProjectDao;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.User;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Project> findProjectsByUser(User user) {
        logger.debug(">> findProjectsByUser: " + user);
        List<Project> list = getSession().getNamedQuery(Project.Q_GET_BY_USER)
                .setEntity("user", user)
                .list();
        logger.debug("<< findProjectsByUser: number found:" + list.size());
        return list;
    }

}
