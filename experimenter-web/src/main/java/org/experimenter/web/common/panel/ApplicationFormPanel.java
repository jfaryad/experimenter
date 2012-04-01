package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.service.ApplicationService;
import org.experimenter.web.model.ApplicationModel;

/**
 * Simple panel with a form to edit the {@link Application} entity.
 * 
 * @author jfaryad
 * 
 */
public class ApplicationFormPanel extends EntityFormPanel<Application> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ApplicationService applicationService;

    public ApplicationFormPanel(String id, ApplicationModel applicationModel) {
        super(id, applicationModel);
    }

    @Override
    protected void addFieldsToForm(Form<Application> form) {
        form.add(new RequiredTextField<String>("version"));
        form.add(new RequiredTextField<String>("executable"));

    }

    @Override
    protected void save(Application application) {
        applicationService.saveUpdate(application);
    }

}
