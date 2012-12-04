package org.experimenter.web.common.panel;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Experiment.Status;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.repository.service.SchedulerService;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.component.TimeSchedulePanel;
import org.experimenter.web.model.ExperimentModel;
import org.experimenter.web.model.aggregate.AvailableApplications;
import org.experimenter.web.model.aggregate.AvailableInputSetsByProject;
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

    private TimeSchedulePanel timeSchedulePanel;
    private IModel<Boolean> executeImmediately;

    public ExperimentFormPanel(String id, ExperimentModel experimentModel) {
        super(id, experimentModel);
    }

    @Override
    protected void addFieldsToForm(final Form<Experiment> form) {
        form.add(applicationSelect(form).setRequired(true));
        form.add(new RequiredTextField<String>("name"));
        form.add(new TextField<String>("description"));
        form.add(new TextField<Integer>("maxRunningJobs", Integer.class));

        timeSchedulePanel = new TimeSchedulePanel("scheduledTime",
                new PropertyModel<String>(getDefaultModel(), "cronExpression"),
                new PropertyModel<Date>(getDefaultModel(), "scheduledTime"));
        timeSchedulePanel.setOutputMarkupId(true);
        form.add(timeSchedulePanel);

        form.add(new AjaxCheckBox("immediate", executeImmediately = new Model<Boolean>(false)) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                timeSchedulePanel.setEnabled(!getModelObject());
                target.add(timeSchedulePanel);
            }
        }.setDefaultModel(executeImmediately));

        form.add(new ListMultipleChoice<InputSet>("inputSets", new AvailableInputSetsByProject(
                new PropertyModel<Project>(getDefaultModel(), "application.program.project")),
                PropertyChoiceRenderer.INPUT_SET_RENDERER).setMaxRows(5).setRequired(true));

        form.add(new ListMultipleChoice<ConnectionFarm>("connectionFarms",
                new ConnectionFarmsForExperimentPropertyModel(getDefaultModel()),
                PropertyChoiceRenderer.CONNECTION_FARM_RENDERER).setMaxRows(5).setRequired(true));

        form.add(new FutureScheduleTimeValidator(timeSchedulePanel.getFormComponents()));
    }

    @Override
    protected void save(Experiment experiment) {
        if (experiment.getId() != null &&
                Status.RUNNING.equals(experimentService.getExperimentStatus(experiment))) {
            error("Unable to edit the experiment while it is running");
            return;
        }
        if (executeImmediately.getObject()) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, 1);
            cal.add(Calendar.MINUTE, 1);
            DateUtils.truncate(cal, Calendar.MINUTE);
            timeSchedulePanel.setDefaultModelObject(cal.getTime());
        }
        experimentService.saveUpdate(experiment);
        if (experiment.getCronExpression() != null) {
            schedulerService.saveUpdateJob(experiment);
        } else {
            schedulerService.stopJob(experiment);
        }
    }

    @Override
    protected void onModelChanged() {
        super.onModelChanged();
        timeSchedulePanel.modelChanged();
        executeImmediately.setObject(false);
    }

    private DropDownChoice<Application> applicationSelect(final Form<Experiment> form) {
        return new AjaxDropDownChoice<Application>("application", new AvailableApplications(),
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

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(form);

            }
        };
    }

    private static class ConnectionFarmsForExperimentPropertyModel extends PropertyModel<List<ConnectionFarm>> {

        private static final long serialVersionUID = 1L;

        public ConnectionFarmsForExperimentPropertyModel(Object modelObject) {
            super(modelObject, "application.program.project.userGroup.connectionFarms");
        }

        @Override
        public List<ConnectionFarm> getObject() {
            List<ConnectionFarm> result = super.getObject();
            if (result == null) {
                return Collections.emptyList();
            }
            return result;
        }

    }

    private class FutureScheduleTimeValidator extends AbstractFormValidator {

        private static final long serialVersionUID = 1L;
        List<FormComponent<?>> timeScheduleFormComponents;

        private FutureScheduleTimeValidator(List<FormComponent<?>> timeScheduleFormComponents) {
            this.timeScheduleFormComponents = timeScheduleFormComponents;
        }

        @Override
        public FormComponent<?>[] getDependentFormComponents() {
            return null;
        }

        @Override
        public void validate(Form<?> form) {
            Object date = timeScheduleFormComponents.get(0).getConvertedInput();
            Object hours = timeScheduleFormComponents.get(1).getConvertedInput();
            Object minutes = timeScheduleFormComponents.get(2).getConvertedInput();
            if (date != null && hours != null & minutes != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime((Date) date);
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt((String) hours));
                cal.set(Calendar.MINUTE, Integer.parseInt((String) minutes));
                DateUtils.truncate(date, Calendar.MINUTE);

                Calendar now = Calendar.getInstance();
                if (cal.before(now)) {
                    error(timeScheduleFormComponents.get(0), "past-experiment-schedule-time.error.message");
                }
            }
        }

    }

}
