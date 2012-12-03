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
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.EntityService;
import org.experimenter.repository.service.ProblemTypeService;
import org.experimenter.web.InputSetPage;
import org.experimenter.web.ProjectPage;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.ProblemTypeFormPanel;
import org.experimenter.web.datatable.column.LinkColumn;
import org.experimenter.web.model.ProblemTypeModel;

/**
 * Table listing {@link ProblemType} entities.
 * 
 * @author jakub
 * 
 */
public class ProblemTypeTable extends DataTablePanel<ProblemType> {

    private static final long serialVersionUID = 1L;
    @SpringBean
    private ProblemTypeService problemTypeService;

    public ProblemTypeTable(String id, IDataProvider<ProblemType> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected List<IColumn<ProblemType, String>> getColumns() {
        List<IColumn<ProblemType, String>> columns = new ArrayList<IColumn<ProblemType, String>>();

        columns.add(new PropertyColumn<ProblemType, String>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<ProblemType, String>(new Model<String>("Description"), "description"));
        columns.add(new LinkColumn<ProblemType>(new Model<String>(""), new Model<String>("input sets")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<ProblemType> rowModel) {
                setResponsePage(InputSetPage.class,
                        new PageParameters().set(InputSetPage.PRESELECT_PROBLEM_ID, rowModel.getObject().getId()));
            }
        });
        columns.add(new LinkColumn<ProblemType>(new Model<String>(""), new Model<String>("projects")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<ProblemType> rowModel) {
                setResponsePage(ProjectPage.class,
                        new PageParameters().set(ProjectPage.PRESELECT_PROBLEM_ID, rowModel.getObject().getId()));
            }
        });

        return columns;
    }

    @Override
    protected EntityFormPanel<ProblemType> createEntityForm(String componentId) {
        return new ProblemTypeFormPanel(componentId, new ProblemTypeModel(new ProblemType()));
    }

    @Override
    protected ProblemType getNewEntity() {
        return new ProblemType();
    }

    @Override
    protected EntityService<ProblemType> getEntityService() {
        return problemTypeService;
    }

}
