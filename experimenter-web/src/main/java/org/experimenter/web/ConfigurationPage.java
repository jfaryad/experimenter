package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.form.UserFormPanel;
import org.experimenter.web.model.CurrentUserModel;

/**
 * Page for administration of the currently signed in user.
 * 
 * @author jakub
 * 
 */
public class ConfigurationPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public ConfigurationPage(final PageParameters parameters) {
        super(parameters, "Settings");
        add(new UserFormPanel("userSettings", new CurrentUserModel()));
    }
}
