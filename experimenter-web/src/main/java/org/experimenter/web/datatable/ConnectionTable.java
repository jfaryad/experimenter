package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.Connection;
import org.experimenter.web.common.panel.ConnectionFormPanel;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.model.ConnectionModel;

/**
 * Table listing {@link Connection} entities.
 * 
 * @author jakub
 * 
 */
public class ConnectionTable extends DataTablePanel<Connection> {

    private static final long serialVersionUID = 1L;

    public ConnectionTable(String id, IDataProvider<Connection> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Connection>> getColumns() {
        List<IColumn<Connection>> columns = new ArrayList<IColumn<Connection>>();

        columns.add(new PropertyColumn<Connection>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<Connection>(new Model<String>("Login"), "login"));
        columns.add(new PropertyColumn<Connection>(new Model<String>("Password"), "password"));
        columns.add(new PropertyColumn<Connection>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<Connection>(new Model<String>("Port"), "port"));
        columns.add(new PropertyColumn<Connection>(new Model<String>("Adress"), "computer.address"));
        columns.add(new PropertyColumn<Connection>(new Model<String>("Farm"), "connectionFarm.name"));

        return columns;
    }

    @Override
    protected EntityFormPanel<Connection> createEntityForm(String componentId) {
        return new ConnectionFormPanel(componentId, new ConnectionModel(new Connection()));
    }

}
