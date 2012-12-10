package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ProjectDao;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.ProjectService;

/**
 * Default implementation of the related service interface.
 * 
 * @author jfaryad
 */
public class ProjectServiceImpl extends AbstractService<Project, ProjectDao> implements ProjectService {

    @Override
    protected void deleteDependencies(Project project) {
        programService.delete(project.getPrograms());
    }

    @Override
    public List<Project> findProjectsByUserGroup(UserGroup userGroup) {
        checkIdNotNull(userGroup);
        return userGroup.getProjects();
    }

    @Override
    public List<Project> findProjectsByProblemType(ProblemType problemType) {
        checkIdNotNull(problemType);
        return problemType.getProjects();
    }

    @Override
    public List<Project> findProjectsByInputSet(InputSet inputSet) {
        checkIdNotNull(inputSet);
        return inputSet.getProjects();
    }

    @Override
    public List<Project> findProjectsByUser(User user) {
        checkIdNotNull(user);
        return baseDao.findProjectsByUser(user);
    }

    @Override
    protected boolean hasDependencies(Project project) {
        return !project.getPrograms().isEmpty();
    }

}
