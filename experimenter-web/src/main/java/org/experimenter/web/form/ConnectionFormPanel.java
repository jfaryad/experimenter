package org.experimenter.web.form;

import static org.experimenter.web.renderer.PropertyChoiceRenderer.COMPUTER_RENDERER;
import static org.experimenter.web.renderer.PropertyChoiceRenderer.CONNECTION_FARM_RENDERER;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.web.component.FinalEntityPropertyDropDownChoice;
import org.experimenter.web.model.ConnectionModel;
import org.experimenter.web.model.aggregate.AvailableComputers;
import org.experimenter.web.model.aggregate.AvailableConnectionFarms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple panel with a form to edit the {@link Connection} entity.
 * 
 * @author jfaryad
 * 
 */
public class ConnectionFormPanel extends EntityFormPanel<Connection> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionFormPanel.class);

    @SpringBean
    private ConnectionService connectionService;

    public ConnectionFormPanel(String id, ConnectionModel connectionModel) {
        super(id, connectionModel);
    }

    @Override
    protected void addFieldsToForm(Form<Connection> form) {
        form.add(new FinalEntityPropertyDropDownChoice<Connection, Computer>("computer", new AvailableComputers(),
                COMPUTER_RENDERER, form.getModel()));
        form.add(new FinalEntityPropertyDropDownChoice<Connection, ConnectionFarm>("connectionFarm",
                new AvailableConnectionFarms(),
                CONNECTION_FARM_RENDERER, form.getModel()));
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("login"));

        PasswordTextField password = new PasswordTextField("password");
        password.setResetPassword(false);
        form.add(password);

        form.add(new RequiredTextField<String>("port"));
        form.add(new AjaxSubmitLink("test-connection", form) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(getFeedbackPanel());
                try {
                    Connection connection = (Connection) form.getModelObject();
                    if (connectionService.testConnection(connection.getComputer().getAddress(),
                            connection.getLogin(), connection.getPassword(), connection.getPort())) {
                        info("Connection test successfull");
                        return;
                    }
                } catch (Exception e) {
                    LOG.error("Connection test failed", e);
                }
                error("Connection test failed");
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(getFeedbackPanel());
            }
        });
        form.add(new TextField<String>("description"));

    }

    @Override
    protected void save(Connection connection) {
        connectionService.saveUpdate(connection);
    }

}