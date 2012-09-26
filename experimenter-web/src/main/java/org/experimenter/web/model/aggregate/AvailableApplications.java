package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.service.ApplicationService;

public class AvailableApplications extends LoadableDetachableModel<List<Application>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ApplicationService applicationService;

    public AvailableApplications() {
        Injector.get().inject(this);
    }

    @Override
    protected List<Application> load() {
        return applicationService.findByExample(new Application());
    }

}
