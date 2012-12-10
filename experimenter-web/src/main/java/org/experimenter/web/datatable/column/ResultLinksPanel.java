package org.experimenter.web.datatable.column;

import java.io.File;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.service.ResultService;
import org.experimenter.web.ChartPage;
import org.experimenter.web.component.AjaxDownloadLink;

/**
 * A panel containing the links that are shown in the results column in the experiment table.
 * 
 * @author jfaryad
 * 
 */
public class ResultLinksPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @Inject
    private ResultService resultService;

    private final IModel<Experiment> experiment;

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
    public ResultLinksPanel(String id, final IModel<Experiment> experiment, Component feedbackPanel) {
        super(id);
        this.experiment = experiment;
        Injector.get().inject(this);

        final IModel<File> csvModel = new CsvDownloadModel();
        add(new AjaxDownloadLink("csv", csvModel, Model.of("results.csv"), feedbackPanel).setDeleteAfterDownload(true));

        final IModel<File> zipModel = new ZipDownloadModel();
        add(new AjaxDownloadLink("zip", zipModel, Model.of("results.zip"), feedbackPanel).setDeleteAfterDownload(true));

        add(new Link<String>("chart") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                Integer projectId = experiment.getObject().getApplication().getProgram().getProject().getId();
                setResponsePage(ChartPage.class, new PageParameters()
                        .set(ChartPage.PRESELECT_PROJECT_ID, projectId)
                        .set(ChartPage.PRESELECT_EXPERIMENT_ID, experiment.getObject().getId()));
            }

        });

    }

    private class CsvDownloadModel extends LoadableDetachableModel<File> {

        private static final long serialVersionUID = 1L;

        @Override
        protected File load() {
            try {
                return resultService.downloadResultsAsCsv(experiment.getObject());
            } catch (RuntimeException ex) {
                return null;
            }
        }

    }

    private class ZipDownloadModel extends LoadableDetachableModel<File> {

        private static final long serialVersionUID = 1L;

        @Override
        protected File load() {
            try {
                return resultService.downloadRawResultsAsZip(experiment.getObject());
            } catch (RuntimeException ex) {
                return null;
            }
        }

    }
}
