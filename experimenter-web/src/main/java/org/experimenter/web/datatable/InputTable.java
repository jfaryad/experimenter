package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.service.EntityService;
import org.experimenter.repository.service.InputService;
import org.experimenter.web.datatable.column.DownloadLinkPropertyColumn;
import org.experimenter.web.datatable.column.FileNamePropertyColumn;
import org.experimenter.web.form.EntityFormPanel;
import org.experimenter.web.form.InputFormPanel;
import org.experimenter.web.model.FileNameModel;
import org.experimenter.web.model.InputModel;

/**
 * Table listing {@link Input} entities.
 * 
 * @author jakub
 * 
 */
public class InputTable extends DataTablePanel<Input> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputService inputService;

    public InputTable(String id, IDataProvider<Input> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Input, String>> getColumns() {
        List<IColumn<Input, String>> columns = new ArrayList<IColumn<Input, String>>();

        columns.add(new PropertyColumn<Input, String>(new Model<String>("Name"), "name"));
        columns.add(new FileNamePropertyColumn<Input>(new Model<String>("File Name"), "data"));
        columns.add(new PropertyColumn<Input, String>(new Model<String>("Problem Type"), "problem.name"));
        columns.add(new DownloadLinkPropertyColumn<Input>(new Model<String>(""), "data", feedback) {

            private static final long serialVersionUID = 1L;

            @Override
            protected IModel<String> getFileName(IModel<Input> rowModel) {
                return new FileNameModel(new PropertyModel<String>(rowModel, "data"));
            }

        });

        return columns;
    }

    @Override
    protected EntityFormPanel<Input> createEntityForm(String componentId) {
        return new InputFormPanel(componentId, new InputModel(new Input()));
    }

    @Override
    protected Input getNewEntity() {
        return new Input();
    }

    @Override
    protected EntityService<Input> getEntityService() {
        return inputService;
    }

    @Override
    protected int getInitialModalWindowHeight() {
        return 300;
    }

    @Override
    protected boolean enableAdding() {
        return false;
    }

    @Override
    protected boolean isEditable() {
        return false;
    }

}
