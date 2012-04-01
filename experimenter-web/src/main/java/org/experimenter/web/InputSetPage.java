package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.InputSetTable;
import org.experimenter.web.provider.InputSetProvider;

/**
 * Page for inputSet administration.
 * 
 * @author jakub
 * 
 */
public class InputSetPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public InputSetPage(final PageParameters parameters) {
        super(parameters, "InputSets");
        add(new InputSetTable("inputSet-table", new InputSetProvider()));
    }
}
