package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.UserTable;
import org.experimenter.web.provider.UserProvider;

/**
 * Page for user administration.
 * 
 * @author jakub
 * 
 */
public class UserPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public UserPage(final PageParameters parameters) {
        super(parameters, "Users");
        add(new UserTable("user-table", new UserProvider()));
    }
}
