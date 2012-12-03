package org.experimenter.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.component.ResetFiltersLink;
import org.experimenter.web.datatable.ConnectionTable;
import org.experimenter.web.model.ComputerModel;
import org.experimenter.web.model.ConnectionFarmModel;
import org.experimenter.web.model.aggregate.AvailableComputers;
import org.experimenter.web.model.aggregate.AvailableConnectionFarms;
import org.experimenter.web.provider.ConnectionProvider;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Page for connection administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class ConnectionPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public static final String PRESELECT_CONNECTION_FARM_ID = "connectionFarmId";
    public static final String PRESELECT_COMPUTER_ID = "computerId";

    private final ConnectionTable dataTable;

    public ConnectionPage(final PageParameters parameters) {
        super(parameters, "Connections");

        final AjaxDropDownChoice<ConnectionFarm> connectionFarmFilter = new AjaxDropDownChoice<ConnectionFarm>(
                "connectionFarmFilter",
                new ConnectionFarmModel(parameters.get(PRESELECT_CONNECTION_FARM_ID).toOptionalInteger()),
                new AvailableConnectionFarms(),
                PropertyChoiceRenderer.CONNECTION_FARM_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(dataTable);
            }
        };
        parameters.remove(PRESELECT_CONNECTION_FARM_ID);
        add(connectionFarmFilter.setNullValid(true));

        final AjaxDropDownChoice<Computer> computerFilter = new AjaxDropDownChoice<Computer>("computerFilter",
                new ComputerModel(parameters.get(PRESELECT_COMPUTER_ID).toOptionalInteger()),
                new AvailableComputers(),
                PropertyChoiceRenderer.COMPUTER_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(dataTable);
            }
        };
        parameters.remove(PRESELECT_COMPUTER_ID);
        add(computerFilter.setNullValid(true));

        add(dataTable = new ConnectionTable("connection-table",
                new ConnectionProvider(connectionFarmFilter.getModel(), computerFilter.getModel())) {

            private static final long serialVersionUID = 1L;

            @Override
            protected Connection getNewEntity() {
                Connection connection = super.getNewEntity();
                connection.setConnectionFarm(connectionFarmFilter.getModelObject());
                connection.setComputer(computerFilter.getModelObject());
                return connection;
            }
        });

        add(new ResetFiltersLink("reset-filters", connectionFarmFilter, computerFilter).addTargetComponents(dataTable));
    }
}
