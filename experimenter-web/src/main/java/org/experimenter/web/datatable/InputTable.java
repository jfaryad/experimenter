package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.Input;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.InputFormPanel;
import org.experimenter.web.model.InputModel;

/**
 * Table listing {@link Input} entities.
 * 
 * @author jakub
 * 
 */
public class InputTable extends DataTablePanel<Input> {

    private static final long serialVersionUID = 1L;

    public InputTable(String id, IDataProvider<Input> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Input>> getColumns() {
        List<IColumn<Input>> columns = new ArrayList<IColumn<Input>>();

        columns.add(new PropertyColumn<Input>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<Input>(new Model<String>("Path to data"), "data"));
        columns.add(new PropertyColumn<Input>(new Model<String>("Problem Type"), "problem.name"));

        return columns;
    }

    @Override
    protected EntityFormPanel<Input> createEntityForm(String componentId) {
        return new InputFormPanel(componentId, new InputModel(new Input()));
    }

}
