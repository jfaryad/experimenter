package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.ProblemTypeFormPanel;
import org.experimenter.web.model.ProblemTypeModel;

/**
 * Table listing {@link ProblemType} entities.
 * 
 * @author jakub
 * 
 */
public class ProblemTypeTable extends DataTablePanel<ProblemType> {

    private static final long serialVersionUID = 1L;

    public ProblemTypeTable(String id, IDataProvider<ProblemType> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<ProblemType>> getColumns() {
        List<IColumn<ProblemType>> columns = new ArrayList<IColumn<ProblemType>>();

        columns.add(new PropertyColumn<ProblemType>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<ProblemType>(new Model<String>("Description"), "description"));

        return columns;
    }

    @Override
    protected EntityFormPanel<ProblemType> createEntityForm(String componentId) {
        return new ProblemTypeFormPanel(componentId, new ProblemTypeModel(new ProblemType()));
    }

    @Override
    protected ProblemType getNewEntity() {
        return new ProblemType();
    }

}
