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
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.service.EntityService;
import org.experimenter.repository.service.ProgramService;
import org.experimenter.web.ApplicationPage;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.ProgramFormPanel;
import org.experimenter.web.datatable.column.LinkColumn;
import org.experimenter.web.model.ProgramModel;

/**
 * Table listing {@link Program} entities.
 * 
 * @author jakub
 * 
 */
public class ProgramTable extends DataTablePanel<Program> {

    private static final long serialVersionUID = 1L;
    @SpringBean
    private ProgramService programService;

    public ProgramTable(String id, IDataProvider<Program> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<Program, String>> getColumns() {
        List<IColumn<Program, String>> columns = new ArrayList<IColumn<Program, String>>();

        columns.add(new PropertyColumn<Program, String>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<Program, String>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<Program, String>(new Model<String>("Project"), "project.name"));
        columns.add(new LinkColumn<Program>(new Model<String>(""), new Model<String>("versions")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<Program> rowModel) {
                setResponsePage(ApplicationPage.class,
                        new PageParameters().set(ApplicationPage.PRESELECT_PROGRAM_ID, rowModel.getObject().getId()));
            }
        });

        return columns;
    }

    @Override
    protected EntityFormPanel<Program> createEntityForm(String componentId) {
        return new ProgramFormPanel(componentId, new ProgramModel(new Program()));
    }

    @Override
    protected Program getNewEntity() {
        return new Program();
    }

    @Override
    protected EntityService<Program> getEntityService() {
        return programService;
    }

    @Override
    protected int getInitialModalWindowHeight() {
        return 620;
    }

    @Override
    protected int getInitialModalWindowWidth() {
        return 830;
    }

}
