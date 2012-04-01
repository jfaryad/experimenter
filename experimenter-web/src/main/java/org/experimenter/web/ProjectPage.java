package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.ProjectTable;
import org.experimenter.web.provider.ProjectProvider;

/**
 * Page for project administration.
 * 
 * @author jakub
 * 
 */
public class ProjectPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public ProjectPage(final PageParameters parameters) {
        super(parameters, "Projects");
        add(new ProjectTable("project-table", new ProjectProvider()));
    }
}
