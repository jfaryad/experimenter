package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.EntityService;
import org.experimenter.repository.service.UserGroupService;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.UserGroupFormPanel;
import org.experimenter.web.model.UserGroupModel;

/**
 * Table listing {@link UserGroup} entities.
 * 
 * @author jakub
 * 
 */
public class UserGroupTable extends DataTablePanel<UserGroup> {

    private static final long serialVersionUID = 1L;
    @SpringBean
    private UserGroupService userGroupService;

    public UserGroupTable(String id, IDataProvider<UserGroup> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<UserGroup, String>> getColumns() {
        List<IColumn<UserGroup, String>> columns = new ArrayList<IColumn<UserGroup, String>>();

        columns.add(new PropertyColumn<UserGroup, String>(new Model<String>("Name"), "name"));

        return columns;
    }

    @Override
    protected EntityFormPanel<UserGroup> createEntityForm(String componentId) {
        return new UserGroupFormPanel(componentId, new UserGroupModel(new UserGroup()));
    }

    @Override
    protected UserGroup getNewEntity() {
        return new UserGroup();
    }

    @Override
    protected EntityService<UserGroup> getEntityService() {
        return userGroupService;
    }

    @Override
    protected int getInitialModalWindowHeight() {
        return 410;
    }

}
