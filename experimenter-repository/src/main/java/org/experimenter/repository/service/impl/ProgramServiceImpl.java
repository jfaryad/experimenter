package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ProgramDao;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.service.ProgramService;

public class ProgramServiceImpl extends AbstractService<Program, ProgramDao> implements ProgramService {

    @Override
    protected void deleteDependencies(Program program) {
        applicationService.delete(applicationService.findApplicationsByProgram(program));
    }

    @Override
    public List<Program> findProgramsByProject(Project project) {
        checkIdNotNull(project);
        Program program = new Program();
        program.setProject(project);
        CriteriaForm<Program> criteria = new CriteriaForm<Program>(program);
        return baseDao.findByCriteria(criteria);
    }
}
