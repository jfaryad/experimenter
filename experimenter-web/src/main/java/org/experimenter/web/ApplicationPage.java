package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.ApplicationTable;
import org.experimenter.web.provider.ApplicationProvider;

/**
 * Page for application administration.
 * 
 * @author jakub
 * 
 */
public class ApplicationPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public ApplicationPage(final PageParameters parameters) {
        super(parameters, "Applications");
        add(new ApplicationTable("application-table", new ApplicationProvider()));
    }
}
