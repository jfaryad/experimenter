package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ProjectDao;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.service.ProjectService;

public class ProjectServiceImpl extends AbstractService<Project, ProjectDao> implements ProjectService {

    @Override
    protected void deleteDependencies(Project project) {
        junctionDao.removeInputSetFromProject(null, project);
        programService.delete(programService.findProgramsByProject(project));
    }

    @Override
    public List<Project> findProjectsByUserGroup(UserGroup userGroup) {
        checkIdNotNull(userGroup);
        Project project = new Project();
        project.setUserGroup(userGroup);
        CriteriaForm<Project> criteria = new CriteriaForm<Project>(project);
        return baseDao.findByCriteria(criteria);
    }

    @Override
    public List<Project> findProjectsByProblemType(ProblemType problemType) {
        checkIdNotNull(problemType);
        Project project = new Project();
        project.setProblem(problemType);
        CriteriaForm<Project> criteria = new CriteriaForm<Project>(project);
        return baseDao.findByCriteria(criteria);
    }

    @Override
    public List<Project> findProjectsByInputSet(InputSet inputSet) {
        checkIdNotNull(inputSet);
        return baseDao.findProjectsByInputSet(inputSet);
    }

}
