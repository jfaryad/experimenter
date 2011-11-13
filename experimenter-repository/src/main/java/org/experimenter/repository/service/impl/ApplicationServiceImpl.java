package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.service.ApplicationService;

public class ApplicationServiceImpl extends AbstractService<Application> implements ApplicationService {

    @Override
    protected void deleteDependencies(Application application) {
        experimentService.delete(experimentService.findExperimentsByApplication(application));
    }

    @Override
    public List<Application> findApplicationsByProgram(Program program) {
        Application application = new Application();
        application.setProgram(program);
        CriteriaForm<Application> criteria = new CriteriaForm<Application>(application);
        return baseDao.findByCriteria(criteria);
    }

}
