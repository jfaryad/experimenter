package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ApplicationDao;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.service.ApplicationService;

public class ApplicationServiceImpl extends AbstractService<Application, ApplicationDao> implements ApplicationService {

    @Override
    protected void deleteDependencies(Application application) {
        experimentService.delete(experimentService.findExperimentsByApplication(application));
    }

    @Override
    public List<Application> findApplicationsByProgram(Program program) {
        checkIdNotNull(program);
        return baseDao.findApplicationsByProgram(program);
    }

}
