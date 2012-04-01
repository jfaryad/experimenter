package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.User;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.UserFormPanel;
import org.experimenter.web.model.UserModel;

/**
 * Table listing {@link User} entities.
 * 
 * @author jakub
 * 
 */
public class UserTable extends DataTablePanel<User> {

    private static final long serialVersionUID = 1L;

    public UserTable(String id, IDataProvider<User> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<User>> getColumns() {
        List<IColumn<User>> columns = new ArrayList<IColumn<User>>();

        columns.add(new PropertyColumn<User>(new Model<String>("Username"), "login"));
        columns.add(new PropertyColumn<User>(new Model<String>("First Name"), "name"));
        columns.add(new PropertyColumn<User>(new Model<String>("Last Name"), "surname"));
        columns.add(new PropertyColumn<User>(new Model<String>("Email"), "email"));

        return columns;
    }

    @Override
    protected EntityFormPanel<User> createEntityForm(String componentId) {
        return new UserFormPanel(componentId, new UserModel(new User()));
    }
}
