package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.repository.service.SchedulerService;
import org.experimenter.web.model.ExperimentModel;
import org.experimenter.web.model.aggregate.AvailableApplications;
import org.experimenter.web.model.aggregate.AvailableInputSets;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Simple panel with a form to edit the {@link Experiment} entity.
 * 
 * @author jfaryad
 * 
 */
public class ExperimentFormPanel extends EntityFormPanel<Experiment> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ExperimentService experimentService;

    @SpringBean
    private SchedulerService schedulerService;

    public ExperimentFormPanel(String id, ExperimentModel experimentModel) {
        super(id, experimentModel);
    }

    @Override
    protected void addFieldsToForm(Form<Experiment> form) {
        form.add(new DropDownChoice<Application>("application", new AvailableApplications(),
                new IChoiceRenderer<Application>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Object getDisplayValue(Application object) {
                        return object.getProgram().getName() + " ver. " + object.getVersion();
                    }

                    @Override
                    public String getIdValue(Application object, int index) {
                        return object.getId().toString();
                    }

                }) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(((Experiment) ExperimentFormPanel.this.getDefaultModel().getObject()).getId() == null);
            }
        });
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("description"));
        form.add(new CheckBox("isActive"));
        form.add(new TextField<String>("cronExpression"));
        form.add(new ListMultipleChoice<InputSet>("inputSets", new AvailableInputSets(new PropertyModel<ProblemType>(
                getDefaultModel(), "application.program.project.problem")), PropertyChoiceRenderer.INPUT_SET_RENDERER));
    }

    @Override
    protected void save(Experiment experiment) {
        experimentService.saveUpdate(experiment);
        if (experiment.getIsActive()) {
            schedulerService.saveUpdateJob(experiment);
        } else {
            schedulerService.stopJob(experiment);
        }
    }

}
