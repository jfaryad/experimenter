package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.Application;
import org.experimenter.web.common.panel.ApplicationFormPanel;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.model.ApplicationModel;

/**
 * Table listing {@link Application} entities.
 * 
 * @author jakub
 * 
 */
public class ApplicationTable extends DataTablePanel<Application> {

    private static final long serialVersionUID = 1L;

    public ApplicationTable(String id, IDataProvider<Application> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Application>> getColumns() {
        List<IColumn<Application>> columns = new ArrayList<IColumn<Application>>();

        columns.add(new PropertyColumn<Application>(new Model<String>("Executable"), "executable"));
        columns.add(new PropertyColumn<Application>(new Model<String>("Version"), "version"));
        columns.add(new PropertyColumn<Application>(new Model<String>("Program"), "program.name"));

        return columns;
    }

    @Override
    protected EntityFormPanel<Application> createEntityForm(String componentId) {
        return new ApplicationFormPanel(componentId, new ApplicationModel(new Application()));
    }

    @Override
    protected Application getNewEntity() {
        return new Application();
    }

}
