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
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.service.ComputerService;
import org.experimenter.repository.service.EntityService;
import org.experimenter.web.ConnectionPage;
import org.experimenter.web.datatable.column.LinkColumn;
import org.experimenter.web.form.ComputerFormPanel;
import org.experimenter.web.form.EntityFormPanel;
import org.experimenter.web.model.ComputerModel;

/**
 * Table listing {@link Computer} entities.
 * 
 * @author jakub
 * 
 */
public class ComputerTable extends DataTablePanel<Computer> {

    private static final long serialVersionUID = 1L;
    @SpringBean
    private ComputerService computerService;

    public ComputerTable(String id, IDataProvider<Computer> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Computer, String>> getColumns() {
        List<IColumn<Computer, String>> columns = new ArrayList<IColumn<Computer, String>>();

        columns.add(new PropertyColumn<Computer, String>(new Model<String>("Adress"), "address"));
        columns.add(new PropertyColumn<Computer, String>(new Model<String>("Description"), "description"));
        columns.add(new LinkColumn<Computer>(new Model<String>(""), new Model<String>("connections")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<Computer> rowModel) {
                setResponsePage(ConnectionPage.class,
                        new PageParameters().set(ConnectionPage.PRESELECT_COMPUTER_ID, rowModel.getObject()
                                .getId()));
            }
        });

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

    @Override
    protected EntityService<Computer> getEntityService() {
        return computerService;
    }

}
