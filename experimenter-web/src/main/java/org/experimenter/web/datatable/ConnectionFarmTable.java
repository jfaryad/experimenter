package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.web.common.panel.ConnectionFarmFormPanel;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.model.ConnectionFarmModel;
import org.experimenter.web.model.UserGroupModel;

/**
 * Table listing {@link ConnectionFarm} entities.
 * 
 * @author jakub
 * 
 */
public class ConnectionFarmTable extends DataTablePanel<ConnectionFarm> {

    private static final long serialVersionUID = 1L;

    public ConnectionFarmTable(String id, IDataProvider<ConnectionFarm> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<ConnectionFarm>> getColumns() {
        List<IColumn<ConnectionFarm>> columns = new ArrayList<IColumn<ConnectionFarm>>();

        columns.add(new PropertyColumn<ConnectionFarm>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<ConnectionFarm>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<ConnectionFarm>(new Model<String>("User Group"), "userGroup.name"));

        return columns;
    }

    @Override
    protected EntityFormPanel<ConnectionFarm> createEntityForm(String componentId) {
        return new ConnectionFarmFormPanel(componentId, new ConnectionFarmModel(new ConnectionFarm()));
    }

    @Override
    protected ConnectionFarm getNewEntity() {
        ConnectionFarm farm = new ConnectionFarm();
        // TODO set active user group
        UserGroup group = new UserGroupModel(6).getObject();
        farm.setUserGroup(group);
        return farm;
    }

}
