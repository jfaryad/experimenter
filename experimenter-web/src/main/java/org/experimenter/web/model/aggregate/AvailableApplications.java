package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.service.ApplicationService;
import org.experimenter.web.ExperimenterSession;
import org.experimenter.web.model.FilteredListModel;

public class AvailableApplications extends FilteredListModel<Application> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ApplicationService applicationService;

    public AvailableApplications() {
        Injector.get().inject(this);
    }

    @Override
    protected List<Application> loadUnfiltered() {
        return applicationService.findApplicationsByUser(ExperimenterSession.get().getCurrentUser());
    }

}
