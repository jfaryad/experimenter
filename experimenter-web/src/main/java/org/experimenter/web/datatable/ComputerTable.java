package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.Computer;
import org.experimenter.web.common.panel.ComputerFormPanel;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.model.ComputerModel;

/**
 * Table listing {@link Computer} entities.
 * 
 * @author jakub
 * 
 */
public class ComputerTable extends DataTablePanel<Computer> {

    private static final long serialVersionUID = 1L;

    public ComputerTable(String id, IDataProvider<Computer> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Computer>> getColumns() {
        List<IColumn<Computer>> columns = new ArrayList<IColumn<Computer>>();

        columns.add(new PropertyColumn<Computer>(new Model<String>("Adress"), "address"));
        columns.add(new PropertyColumn<Computer>(new Model<String>("Description"), "description"));

        return columns;
    }

    @Override
    protected EntityFormPanel<Computer> createEntityForm(String componentId) {
        return new ComputerFormPanel(componentId, new ComputerModel(new Computer()));
    }

    @Override
    protected Computer getNewEntity() {
        return new Computer();
    }

}
