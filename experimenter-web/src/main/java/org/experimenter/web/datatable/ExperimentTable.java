package org.experimenter.web.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.time.Duration;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.service.EntityService;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.web.InputSetPage;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.common.panel.ExperimentFormPanel;
import org.experimenter.web.datatable.column.DatePropertyColumn;
import org.experimenter.web.datatable.column.LinkColumn;
import org.experimenter.web.datatable.column.ResultColumn;
import org.experimenter.web.model.ExperimentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Table listing {@link Experiment} entities.
 * 
 * @author jakub
 * 
 */
public class ExperimentTable extends DataTablePanel<Experiment> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ExperimentTable.class);

    @SpringBean
    private ExperimentService experimentService;
    private AjaxSelfUpdatingTimerBehavior timer;

    public ExperimentTable(String id, IDataProvider<Experiment> dataProvider) {
        super(id, dataProvider);
        table.add(timer = new AjaxSelfUpdatingTimerBehavior(Duration.seconds(10)));
    }

    @Override
    protected List<IColumn<Experiment, String>> getColumns() {
        List<IColumn<Experiment, String>> columns = new ArrayList<IColumn<Experiment, String>>();

        columns.add(new PropertyColumn<Experiment, String>(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn<Experiment, String>(new Model<String>("Description"), "description"));
        columns.add(new PropertyColumn<Experiment, String>(new Model<String>("Program"), "application.fullName"));
        // columns.add(new ProgramVersionColumn(new Model<String>("Program")));
        columns.add(new DatePropertyColumn<Experiment>(new Model<String>("Scheduled time"), "cronExpression"));
        columns.add(new LinkColumn<Experiment>(new Model<String>(""), new Model<String>("input sets")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<Experiment> rowModel) {
                setResponsePage(InputSetPage.class,
                        new PageParameters().set(InputSetPage.PRESELECT_EXPERIMENT_ID, rowModel.getObject().getId()));
            }
        });
        columns.add(new ResultColumn(new Model<String>("Results"), feedback));

        // final AjaxTimerBehavior timer = new AjaxTimerBehavior(this, 5);

        return columns;
    }

    @Override
    protected EntityFormPanel<Experiment> createEntityForm(String componentId) {
        return new ExperimentFormPanel(componentId, new ExperimentModel(new Experiment()));
    }

    @Override
    protected Experiment getNewEntity() {
        return new Experiment();
    }

    @Override
    protected EntityService<Experiment> getEntityService() {
        return experimentService;
    }

    @Override
    protected int getInitialModalWindowHeight() {
        return 550;
    }

    @Override
    protected int getInitialModalWindowWidth() {
        return 970;
    }

    @Override
    protected boolean isCloneable() {
        return true;
    }

    @Override
    protected String[] cloneIgnoredProperties() {
        return new String[] { "results", "connectionFarms", "inputSets" };
    }

    @Override
    protected void afterPropertiesCloned(Experiment source, Experiment target) {
        target.setConnectionFarms(new ArrayList<ConnectionFarm>(source.getConnectionFarms()));
        target.setInputSets(new ArrayList<InputSet>(source.getInputSets()));
    }

    @Override
    protected void showEditDialog(AjaxRequestTarget target, Experiment object) {
        timer.stop(target);
        super.showEditDialog(target, object);
    }

    @Override
    protected void modalDialogClosed(AjaxRequestTarget target) {
        timer.restart(target);
    }

}
