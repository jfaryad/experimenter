package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ProgramDao;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.ProgramService;

/**
 * Default implementation of the related service interface.
 * 
 * @author jfaryad
 */
public class ProgramServiceImpl extends AbstractService<Program, ProgramDao> implements ProgramService {

    @Override
    protected void deleteDependencies(Program program) {
        applicationService.delete(program.getApplications());
    }

    @Override
    public List<Program> findProgramsByProject(Project project) {
        checkIdNotNull(project);
        return project.getPrograms();
    }

    @Override
    public List<Program> findProgramsByUser(User user) {
        checkIdNotNull(user);
        return baseDao.findProgramsByUser(user);
    }

    @Override
    protected boolean hasDependencies(Program program) {
        return !program.getApplications().isEmpty();
    }
}
