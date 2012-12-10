package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.repository.service.EntityService;
import org.experimenter.web.datatable.column.AddressPortColumn;
import org.experimenter.web.form.ConnectionFormPanel;
import org.experimenter.web.form.EntityFormPanel;
import org.experimenter.web.model.ConnectionModel;

/**
 * Table listing {@link Connection} entities.
 * 
 * @author jakub
 * 
 */
public class ConnectionTable extends DataTablePanel<Connection> {

    private static final long serialVersionUID = 1L;
    @SpringBean
    private ConnectionService connectionService;

    public ConnectionTable(String id, IDataProvider<Connection> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Connection, String>> getColumns() {
        List<IColumn<Connection, String>> columns = new ArrayList<IColumn<Connection, String>>();

        columns.add(new PropertyColumn<Connection, String>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<Connection, String>(new Model<String>("Login"), "login"));
        columns.add(new AddressPortColumn(new Model<String>("Address")));
        columns.add(new PropertyColumn<Connection, String>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<Connection, String>(new Model<String>("Farm"), "connectionFarm.name"));

        return columns;
    }

    @Override
    protected EntityFormPanel<Connection> createEntityForm(String componentId) {
        return new ConnectionFormPanel(componentId, new ConnectionModel(new Connection()));
    }

    @Override
    protected Connection getNewEntity() {
        return new Connection();
    }

    @Override
    protected EntityService<Connection> getEntityService() {
        return connectionService;
    }

    @Override
    protected int getInitialModalWindowHeight() {
        return 450;
    }

    @Override
    protected boolean isCloneable() {
        return true;
    }

}
