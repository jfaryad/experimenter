package org.experimenter.web;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.ComputerTable;
import org.experimenter.web.provider.ComputerProvider;

/**
 * Page for computer administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class ComputerPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public ComputerPage(final PageParameters parameters) {
        super(parameters, "Computers");
        add(new ComputerTable("computer-table", new ComputerProvider()));
    }
}
