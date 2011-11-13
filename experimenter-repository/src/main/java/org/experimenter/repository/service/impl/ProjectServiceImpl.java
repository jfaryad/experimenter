package org.experimenter.repository.service.impl;

import org.experimenter.repository.entity.Project;

public class ProjectServiceImpl extends AbstractService<Project> {

    @Override
    protected void deleteDependencies(Project Project) {
        junctionDao.removeProjectFromProjectGroup(Project, null);
    }

}
