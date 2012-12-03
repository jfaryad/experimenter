package org.experimenter.web.datatable.column;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Experiment.Status;
import org.experimenter.repository.service.ExperimentService;

/**
 * The cell in a data table containing links for displaying/downloading the results of an experiment in various formats.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public class ResultLinkCell extends Panel {

    private static final long serialVersionUID = 1L;

    private final IModel<Experiment> experiment;

    @Inject
    private ExperimentService experimentService;

    private final ResultLinksPanel results;
    private Component actualContent;
    private final Label scheduledLabel;
    private final Label runningLabel;

    public ResultLinkCell(String id, final IModel<Experiment> experiment, Component feedbackPanel) {
        super(id);
        this.experiment = experiment;
        Injector.get().inject(this);

        results = new ResultLinksPanel("content", experiment, feedbackPanel);
        scheduledLabel = new Label("content", "scheduled");
        runningLabel = new Label("content", "running");
        actualContent = scheduledLabel;
        add(actualContent);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        Status status = experimentService.getExperimentStatus(experiment.getObject());
        if (Status.FINISHED.equals(status)) {
            actualContent.replaceWith(results);
            actualContent = results;
        } else if (Status.NEW.equals(status)) {
            actualContent.replaceWith(scheduledLabel);
            actualContent = scheduledLabel;
        } else if (Status.RUNNING.equals(status)) {
            actualContent.replaceWith(runningLabel);
            actualContent = runningLabel;
        }
    }

}
