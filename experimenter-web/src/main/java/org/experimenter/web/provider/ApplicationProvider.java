package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.service.ApplicationService;
import org.experimenter.web.model.ApplicationModel;

/**
 * Default provider of the {@link Application} entity.
 * 
 * @author jakub
 * 
 */
public class ApplicationProvider extends EntityDataProvider<Application> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ApplicationService applicationService;

    @Override
    public IModel<Application> model(Application application) {
        return new ApplicationModel(application);
    }

    @Override
    protected List<Application> load() {
        // loads all applications
        return applicationService.findByExample(new Application());
    }

}
