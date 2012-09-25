package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.Program;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.ProgramFormPanel;
import org.experimenter.web.model.ProgramModel;

/**
 * Table listing {@link Program} entities.
 * 
 * @author jakub
 * 
 */
public class ProgramTable extends DataTablePanel<Program> {

    private static final long serialVersionUID = 1L;

    public ProgramTable(String id, IDataProvider<Program> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Program>> getColumns() {
        List<IColumn<Program>> columns = new ArrayList<IColumn<Program>>();

        columns.add(new PropertyColumn<Program>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<Program>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<Program>(new Model<String>("Launch command"), "command"));
        columns.add(new PropertyColumn<Program>(new Model<String>("Project"), "project.name"));

        return columns;
    }

    @Override
    protected EntityFormPanel<Program> createEntityForm(String componentId) {
        return new ProgramFormPanel(componentId, new ProgramModel(new Program()));
    }

    @Override
    protected Program getNewEntity() {
        return new Program();
    }

}
