package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.ExperimentTable;
import org.experimenter.web.provider.ExperimentProvider;

/**
 * Page for experiment administration.
 * 
 * @author jakub
 * 
 */
public class ExperimentPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public ExperimentPage(final PageParameters parameters) {
        super(parameters, "Experiments");
        add(new ExperimentTable("experiment-table", new ExperimentProvider()));
    }
}
