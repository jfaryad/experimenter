package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.service.EntityService;
import org.experimenter.repository.service.InputSetService;
import org.experimenter.web.InputPage;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.InputSetFormPanel;
import org.experimenter.web.datatable.column.LinkColumn;
import org.experimenter.web.model.InputSetModel;

/**
 * Table listing {@link InputSet} entities.
 * 
 * @author jakub
 * 
 */
public class InputSetTable extends DataTablePanel<InputSet> {

    private static final long serialVersionUID = 1L;
    @SpringBean
    private InputSetService inputSetService;

    public InputSetTable(String id, IDataProvider<InputSet> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<InputSet, String>> getColumns() {
        List<IColumn<InputSet, String>> columns = new ArrayList<IColumn<InputSet, String>>();

        columns.add(new PropertyColumn<InputSet, String>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<InputSet, String>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<InputSet, String>(new Model<String>("Problem Type"), "problem.name"));
        columns.add(new LinkColumn<InputSet>(new Model<String>(""), new Model<String>("inputs")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<InputSet> rowModel) {
                setResponsePage(InputPage.class,
                        new PageParameters().set(InputPage.PRESELECT_INPUTSET_ID, rowModel.getObject().getId()));
            }
        });

        return columns;
    }

    @Override
    protected EntityFormPanel<InputSet> createEntityForm(String componentId) {
        return new InputSetFormPanel(componentId, new InputSetModel(new InputSet()));
    }

    @Override
    protected InputSet getNewEntity() {
        return new InputSet();
    }

    @Override
    protected EntityService<InputSet> getEntityService() {
        return inputSetService;
    }

    @Override
    protected int getInitialModalWindowHeight() {
        return 410;
    }

}
