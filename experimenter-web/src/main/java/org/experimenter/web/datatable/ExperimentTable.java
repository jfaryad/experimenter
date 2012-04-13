package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.ExperimentFormPanel;
import org.experimenter.web.model.ExperimentModel;

/**
 * Table listing {@link Experiment} entities.
 * 
 * @author jakub
 * 
 */
public class ExperimentTable extends DataTablePanel<Experiment> {

    private static final long serialVersionUID = 1L;

    public ExperimentTable(String id, IDataProvider<Experiment> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Experiment>> getColumns() {
        List<IColumn<Experiment>> columns = new ArrayList<IColumn<Experiment>>();

        columns.add(new PropertyColumn<Experiment>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<Experiment>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<Experiment>(new Model<String>("Program"), "application.program.name"));
        columns.add(new PropertyColumn<Experiment>(new Model<String>("Application version"), "application.version"));
        columns.add(new PropertyColumn<Experiment>(new Model<String>("Active"), "isActive"));
        columns.add(new PropertyColumn<Experiment>(new Model<String>("Cron expression"), "cronExpression"));

        return columns;
    }

    @Override
    protected EntityFormPanel<Experiment> createEntityForm(String componentId) {
        return new ExperimentFormPanel(componentId, new ExperimentModel(new Experiment()));
    }

}
