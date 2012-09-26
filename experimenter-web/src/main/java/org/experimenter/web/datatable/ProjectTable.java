package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.ProjectFormPanel;
import org.experimenter.web.model.ProjectModel;
import org.experimenter.web.model.UserGroupModel;

/**
 * Table listing {@link Project} entities.
 * 
 * @author jakub
 * 
 */
public class ProjectTable extends DataTablePanel<Project> {

    private static final long serialVersionUID = 1L;

    public ProjectTable(String id, IDataProvider<Project> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Project>> getColumns() {
        List<IColumn<Project>> columns = new ArrayList<IColumn<Project>>();

        columns.add(new PropertyColumn<Project>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<Project>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<Project>(new Model<String>("User group"), "userGroup.name"));
        columns.add(new PropertyColumn<Project>(new Model<String>("Problem type"), "problem.name"));

        return columns;
    }

    @Override
    protected EntityFormPanel<Project> createEntityForm(String componentId) {
        return new ProjectFormPanel(componentId, new ProjectModel(new Project()));
    }

    @Override
    protected Project getNewEntity() {
        Project project = new Project();
        // TODO set active user group
        UserGroup group = new UserGroupModel(6).getObject();
        project.setUserGroup(group);
        group.getProjects().add(project);
        return project;
    }
}
