package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.InputTable;
import org.experimenter.web.provider.InputProvider;

/**
 * Page for input administration.
 * 
 * @author jakub
 * 
 */
public class InputPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public InputPage(final PageParameters parameters) {
        super(parameters, "Inputs");
        add(new InputTable("input-table", new InputProvider()));
    }
}
