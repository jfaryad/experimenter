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
    private final Label failedLabel;

    /**
     * constructor.
     * 
     * @param id
     *            wicket id
     * @param experiment
     *            experiment model (the current row)
     * @param feedbackPanel
     *            feedback panel to update on error
     */
    public ResultLinkCell(String id, final IModel<Experiment> experiment, Component feedbackPanel) {
        super(id);
        this.experiment = experiment;
        Injector.get().inject(this);

        results = new ResultLinksPanel("content", experiment, feedbackPanel);
        scheduledLabel = new Label("content", "scheduled");
        runningLabel = new Label("content", "running");
        failedLabel = new Label("content", "failed");

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
        } else if (Status.FAILED.equals(status)) {
            actualContent.replaceWith(failedLabel);
            actualContent = failedLabel;
        }
    }

}
