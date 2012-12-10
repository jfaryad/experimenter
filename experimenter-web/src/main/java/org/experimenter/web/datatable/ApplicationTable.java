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
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.service.ApplicationService;
import org.experimenter.repository.service.EntityService;
import org.experimenter.web.ExperimentPage;
import org.experimenter.web.datatable.column.FileNamePropertyColumn;
import org.experimenter.web.datatable.column.LinkColumn;
import org.experimenter.web.form.ApplicationFormPanel;
import org.experimenter.web.form.EntityFormPanel;
import org.experimenter.web.model.ApplicationModel;

/**
 * Table listing {@link Application} entities.
 * 
 * @author jakub
 * 
 */
public class ApplicationTable extends DataTablePanel<Application> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ApplicationService applicationService;

    public ApplicationTable(String id, IDataProvider<Application> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Application, String>> getColumns() {
        List<IColumn<Application, String>> columns = new ArrayList<IColumn<Application, String>>();

        columns.add(new PropertyColumn<Application, String>(new Model<String>("Program"), "program.name"));
        columns.add(new PropertyColumn<Application, String>(new Model<String>("Version"), "version"));
        columns.add(new FileNamePropertyColumn<Application>(new Model<String>("Executable"), "executable"));
        columns.add(new LinkColumn<Application>(new Model<String>(""), new Model<String>("experiments")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<Application> rowModel) {
                setResponsePage(ExperimentPage.class,
                        new PageParameters().set(ExperimentPage.PRESELECT_APPLICATION_ID, rowModel.getObject().getId()));
            }
        });

        return columns;
    }

    @Override
    protected EntityFormPanel<Application> createEntityForm(String componentId) {
        return new ApplicationFormPanel(componentId, new ApplicationModel(new Application()));
    }

    @Override
    protected Application getNewEntity() {
        return new Application();
    }

    @Override
    protected EntityService<Application> getEntityService() {
        return applicationService;
    }

    @Override
    protected boolean isCloneable() {
        return true;
    }

    @Override
    protected String[] cloneIgnoredProperties() {
        return new String[] { "executable", "experiments" };
    }

}
