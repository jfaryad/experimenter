package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.web.model.ConnectionModel;

/**
 * Simple panel with a form to edit the {@link Connection} entity.
 * 
 * @author jfaryad
 * 
 */
public class ConnectionFormPanel extends EntityFormPanel<Connection> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ConnectionService connectionService;

    public ConnectionFormPanel(String id, ConnectionModel connectionModel) {
        super(id, connectionModel);
    }

    @Override
    protected void addFieldsToForm(Form<Connection> form) {
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("login"));
        form.add(new RequiredTextField<String>("password"));
        form.add(new RequiredTextField<String>("port"));
        form.add(new RequiredTextField<String>("description"));

    }

    @Override
    protected void save(Connection connection) {
        connectionService.saveUpdate(connection);
    }

}
