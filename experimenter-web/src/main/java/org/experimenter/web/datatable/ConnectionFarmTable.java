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
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.service.ConnectionFarmService;
import org.experimenter.repository.service.EntityService;
import org.experimenter.web.ConnectionPage;
import org.experimenter.web.datatable.column.LinkColumn;
import org.experimenter.web.form.ConnectionFarmFormPanel;
import org.experimenter.web.form.EntityFormPanel;
import org.experimenter.web.model.ConnectionFarmModel;

/**
 * Table listing {@link ConnectionFarm} entities.
 * 
 * @author jakub
 * 
 */
public class ConnectionFarmTable extends DataTablePanel<ConnectionFarm> {

    private static final long serialVersionUID = 1L;
    @SpringBean
    private ConnectionFarmService connectionFarmService;

    public ConnectionFarmTable(String id, IDataProvider<ConnectionFarm> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<ConnectionFarm, String>> getColumns() {
        List<IColumn<ConnectionFarm, String>> columns = new ArrayList<IColumn<ConnectionFarm, String>>();

        columns.add(new PropertyColumn<ConnectionFarm, String>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<ConnectionFarm, String>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<ConnectionFarm, String>(new Model<String>("User Group"), "userGroup.name"));
        columns.add(new LinkColumn<ConnectionFarm>(new Model<String>(""), new Model<String>("connections")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<ConnectionFarm> rowModel) {
                setResponsePage(ConnectionPage.class,
                        new PageParameters().set(ConnectionPage.PRESELECT_CONNECTION_FARM_ID, rowModel.getObject()
                                .getId()));
            }
        });

        return columns;
    }

    @Override
    protected EntityFormPanel<ConnectionFarm> createEntityForm(String componentId) {
        return new ConnectionFarmFormPanel(componentId, new ConnectionFarmModel(new ConnectionFarm()));
    }

    @Override
    protected ConnectionFarm getNewEntity() {
        return new ConnectionFarm();
    }

    @Override
    protected EntityService<ConnectionFarm> getEntityService() {
        return connectionFarmService;
    }

}
