package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.service.ApplicationService;

/**
 * Default entity model for the {@link Application} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class ApplicationModel extends EntityModel<Application> {

    @SpringBean
    private ApplicationService applicationService;

    private static final long serialVersionUID = 1L;

    public ApplicationModel(Application application) {
        super(application);
    }

    public ApplicationModel(Integer id) {
        super(id);
    }

    @Override
    protected Application load(Integer id) {
        return applicationService.findById(id);
    }

}
