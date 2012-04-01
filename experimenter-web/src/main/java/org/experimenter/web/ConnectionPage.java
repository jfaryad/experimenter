package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.ConnectionTable;
import org.experimenter.web.provider.ConnectionProvider;

/**
 * Page for connection administration.
 * 
 * @author jakub
 * 
 */
public class ConnectionPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public ConnectionPage(final PageParameters parameters) {
        super(parameters, "Connections");
        add(new ConnectionTable("connection-table", new ConnectionProvider()));
    }
}
