package org.experimenter.repository.service.impl;

import java.io.File;
import java.util.List;

import org.experimenter.repository.dao.ApplicationDao;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.ApplicationService;

public class ApplicationServiceImpl extends AbstractService<Application, ApplicationDao> implements ApplicationService {

    @Override
    public void saveWithData(Application application, File tmpDataFile) {
        checkNotNull(application);
        application.setExecutable("tmpValue");
        saveUpdate(application);
        application.setExecutable(storageService.saveApplication(application.getId(), tmpDataFile));
        saveUpdate(application);
    }

    @Override
    protected void deleteDependencies(Application application) {
        experimentService.delete(experimentService.findExperimentsByApplication(application));
    }

    @Override
    public List<Application> findApplicationsByProgram(Program program) {
        checkIdNotNull(program);
        return baseDao.findApplicationsByProgram(program);
    }

    @Override
    public List<Application> findApplicationsByUser(User user) {
        checkIdNotNull(user);
        return baseDao.findApplicationsByUser(user);
    }

    @Override
    protected boolean hasDependencies(Application application) {
        return !application.getExperiments().isEmpty();
    }

}
