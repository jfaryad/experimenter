package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.InputSetFormPanel;
import org.experimenter.web.model.InputSetModel;

/**
 * Table listing {@link InputSet} entities.
 * 
 * @author jakub
 * 
 */
public class InputSetTable extends DataTablePanel<InputSet> {

    private static final long serialVersionUID = 1L;

    public InputSetTable(String id, IDataProvider<InputSet> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<InputSet>> getColumns() {
        List<IColumn<InputSet>> columns = new ArrayList<IColumn<InputSet>>();

        columns.add(new PropertyColumn<InputSet>(new Model<String>("name"), "name"));
        columns.add(new PropertyColumn<InputSet>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<InputSet>(new Model<String>("Problem Type"), "problem.name"));

        return columns;
    }

    @Override
    protected EntityFormPanel<InputSet> createEntityForm(String componentId) {
        return new InputSetFormPanel(componentId, new InputSetModel(new InputSet()));
    }

}
