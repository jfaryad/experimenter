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
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.EntityService;
import org.experimenter.repository.service.ProjectService;
import org.experimenter.web.InputSetPage;
import org.experimenter.web.ProgramPage;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.ProjectFormPanel;
import org.experimenter.web.datatable.column.LinkColumn;
import org.experimenter.web.model.ProjectModel;

/**
 * Table listing {@link Project} entities.
 * 
 * @author jakub
 * 
 */
public class ProjectTable extends DataTablePanel<Project> {

    private static final long serialVersionUID = 1L;
    @SpringBean
    private ProjectService projectService;

    public ProjectTable(String id, IDataProvider<Project> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Project, String>> getColumns() {
        List<IColumn<Project, String>> columns = new ArrayList<IColumn<Project, String>>();

        columns.add(new PropertyColumn<Project, String>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<Project, String>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<Project, String>(new Model<String>("User group"), "userGroup.name"));
        columns.add(new PropertyColumn<Project, String>(new Model<String>("Problem type"), "problem.name"));
        columns.add(new LinkColumn<Project>(new Model<String>(""), new Model<String>("programs")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<Project> rowModel) {
                setResponsePage(ProgramPage.class,
                        new PageParameters().set(ProgramPage.PRESELECT_PROJECT_ID, rowModel.getObject().getId()));
            }
        });
        columns.add(new LinkColumn<Project>(new Model<String>(""), new Model<String>("input sets")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<Project> rowModel) {
                setResponsePage(InputSetPage.class,
                        new PageParameters().set(InputSetPage.PRESELECT_PROJECT_ID, rowModel.getObject().getId()));
            }
        });

        return columns;
    }

    @Override
    protected EntityFormPanel<Project> createEntityForm(String componentId) {
        return new ProjectFormPanel(componentId, new ProjectModel(new Project()));
    }

    @Override
    protected Project getNewEntity() {
        return new Project();
    }

    @Override
    protected EntityService<Project> getEntityService() {
        return projectService;
    }

    @Override
    protected int getInitialModalWindowHeight() {
        return 430;
    }

    @Override
    protected boolean isCloneable() {
        return true;
    }

    @Override
    protected String[] cloneIgnoredProperties() {
        return new String[] { "programs", "inputSets" };
    }
}
