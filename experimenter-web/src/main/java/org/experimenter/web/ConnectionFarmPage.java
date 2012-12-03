package org.experimenter.web;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.ConnectionFarmTable;
import org.experimenter.web.provider.ConnectionFarmProvider;

/**
 * Page for connectionFarm administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class ConnectionFarmPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public ConnectionFarmPage(final PageParameters parameters) {
        super(parameters, "ConnectionFarms");
        add(new ConnectionFarmTable("connectionFarm-table", new ConnectionFarmProvider()));
    }
}
