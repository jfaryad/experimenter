package org.experimenter.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.experimenter.repository.dto.ChartSettings;
import org.experimenter.repository.dto.ChartSettings.ChartSize;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.ResultService;
import org.experimenter.web.component.AjaxDownload;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.component.DynamicChartImage;
import org.experimenter.web.component.ResetFiltersLink;
import org.experimenter.web.model.ApplicationModel;
import org.experimenter.web.model.FirstChoiceProxyModel;
import org.experimenter.web.model.ProgramModel;
import org.experimenter.web.model.ProjectModel;
import org.experimenter.web.model.aggregate.AvailableApplications;
import org.experimenter.web.model.aggregate.AvailablePrograms;
import org.experimenter.web.model.aggregate.AvailableProjects;
import org.experimenter.web.model.aggregate.FinishedExperiments;
import org.experimenter.web.renderer.PropertyChoiceRenderer;
import org.jfree.util.Log;

/**
 * Page for generating charts.
 * 
 * @author jfaryad
 * 
 */
@AuthorizeInstantiation("USER")
public class ChartPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public static final String PRESELECT_APPLICATION_ID = "applicationId";
    public static final String PRESELECT_PROJECT_ID = "projectId";
    public static final String PRESELECT_PROGRAM_ID = "programId";
    public static final String PRESELECT_EXPERIMENT_ID = "experimentId";

    private final IModel<Set<Integer>> selectedExperiments = new SelectedExperimentsModel();
    private final IModel<ChartSettings> settings = Model.of(new ChartSettings());

    private static final String DEFAULT_X_AXIS_LABEL = "number of instances solved";

    private DropDownChoice<String> paramFilter;
    private ListView<Experiment> experimentList;
    private TextField<String> yAxisLabel;
    private TextField<String> xAxisLabel;
    private TextField<String> title;
    private DropDownChoice<ChartSize> size;
    private AjaxButton previewButton;
    private AjaxButton generateButton;
    private DynamicChartImage chartPreview;
    private final WebMarkupContainer filterPanel;
    private final WebMarkupContainer chartConfigPanel;
    private final WebMarkupContainer experimentsPanel;
    private final FeedbackPanel feedback;
    private AjaxDropDownChoice<Project> projectFilter;
    private AjaxDropDownChoice<Program> programFilter;
    private AjaxDropDownChoice<Application> applicationFilter;

    @SpringBean
    private ResultService resultService;

    public ChartPage(final PageParameters parameters) {
        super(parameters, "Experimenter Charts");

        feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        add(feedback);

        filterPanel = new WebMarkupContainer("filter");
        filterPanel.setOutputMarkupId(true);
        add(filterPanel);

        chartConfigPanel = new WebMarkupContainer("chart-config");
        chartConfigPanel.setOutputMarkupId(true);
        add(chartConfigPanel);

        experimentsPanel = new WebMarkupContainer("experiments-container");
        experimentsPanel.setOutputMarkupId(true);
        add(experimentsPanel);

        addProjectFilter(parameters);
        addProgramFilter(parameters);
        addApplicationFilter(parameters);
        addExperimentsList();

        addResetFiltersLink();

        Form<ChartSettings> form = new Form<ChartSettings>("form");
        chartConfigPanel.add(form);

        addParamFilter(form);
        addTitleTextField(form);
        addSizeDropDown(form);
        addAxisLabelTextFields(form);
        addPreviewButton(form);

        final IModel<File> pdfModel = new Model<File>((File) null);
        final IModel<String> fileNameModel = new Model<String>("chart-export.pdf");
        final AjaxDownload download = createAjaxDownload(pdfModel, fileNameModel);
        add(download);
        addGenerateButton(form, pdfModel, download);

        addResetSettingsLink(form);

        add(chartPreview = new DynamicChartImage("chart-preview"));
    }

    private void addResetSettingsLink(Form<ChartSettings> form) {
        form.add(new ResetFiltersLink("reset-settings", paramFilter, title, size, xAxisLabel, yAxisLabel)
                .addTargetComponents(chartConfigPanel));
    }

    private void addGenerateButton(Form<ChartSettings> form, final IModel<File> pdfModel, final AjaxDownload download) {
        form.add(generateButton = new AjaxButton("generate", form) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(feedback);
                ChartSettings previewSettings = settings.getObject().clone();
                if (previewSettings.getTitle() == null) {
                    previewSettings.setTitle(previewSettings.getParam() + " results");
                }
                if (previewSettings.getxAxisLabel() == null) {
                    previewSettings.setxAxisLabel(DEFAULT_X_AXIS_LABEL);
                }
                if (previewSettings.getyAxisLabel() == null) {
                    previewSettings.setyAxisLabel(previewSettings.getParam());
                }
                previewSettings.getExperimentIds().addAll(selectedExperiments.getObject());

                try {
                    File pdf = resultService.downloadChartAsPdf(previewSettings);
                    pdfModel.setObject(pdf);
                    download.initiate(target);
                } catch (Exception e) {
                    Log.error("Unable to retrieve the pdf", e);
                    error("Error retrieveing the chart pdf");
                }

            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(settings.getObject().getParam() != null
                        && settings.getObject().getSize() != null
                        && !selectedExperiments.getObject().isEmpty()
                        && !paramFilter.getChoices().isEmpty());
            }
        });
        generateButton.setOutputMarkupId(true);
    }

    private void addPreviewButton(Form<ChartSettings> form) {
        form.add(previewButton = new AjaxButton("preview", form) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(feedback);
                ChartSettings previewSettings = settings.getObject().clone();
                previewSettings.setSize(ChartSize.PREVIEW);
                if (previewSettings.getTitle() == null) {
                    previewSettings.setTitle("Chart preview");
                }
                if (previewSettings.getxAxisLabel() == null) {
                    previewSettings.setxAxisLabel(DEFAULT_X_AXIS_LABEL);
                }
                if (previewSettings.getyAxisLabel() == null) {
                    previewSettings.setyAxisLabel(previewSettings.getParam());
                }
                previewSettings.getExperimentIds().addAll(selectedExperiments.getObject());

                try {
                    BufferedImage image = resultService.getResultChartAsBufferedImage(previewSettings);
                    chartPreview.setImage(image);
                    target.add(chartPreview);
                } catch (Exception e) {
                    Log.error("Unable to retrieve the buffered chart image", e);
                    error("Error retrieveing the chart preview");
                }
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(settings.getObject().getParam() != null
                        && !selectedExperiments.getObject().isEmpty()
                        && !paramFilter.getChoices().isEmpty());
            }
        });
        previewButton.setOutputMarkupId(true);
    }

    private void addAxisLabelTextFields(Form<ChartSettings> form) {
        form.add(xAxisLabel = new TextField<String>("xlabel", new PropertyModel<String>(settings, "xAxisLabel")));
        xAxisLabel.setOutputMarkupId(true);

        form.add(yAxisLabel = new TextField<String>("ylabel", new PropertyModel<String>(settings, "yAxisLabel")));
        yAxisLabel.setOutputMarkupId(true);
    }

    private void addSizeDropDown(Form<ChartSettings> form) {
        IModel<List<? extends ChartSize>> sizeValues = Model.ofList(Arrays.asList(ChartSize.getSelectableValues()));
        form.add(size = new AjaxDropDownChoice<ChartSize>("size", new FirstChoiceProxyModel<ChartSize>(
                new PropertyModel<ChartSize>(settings, "size"), sizeValues),
                sizeValues) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(generateButton);
            }

        });
        size.setOutputMarkupId(true);
    }

    private void addTitleTextField(Form<ChartSettings> form) {
        form.add(title = new TextField<String>("title", new PropertyModel<String>(settings, "title")));
        title.setOutputMarkupId(true);
    }

    private void addParamFilter(Form<ChartSettings> form) {
        form.add(paramFilter = new AjaxDropDownChoice<String>("param", new PropertyModel<String>(settings, "param"),
                new AvailableExperimentParameters()) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                yAxisLabel.setModelObject(getModelObject());
                target.add(yAxisLabel);
                target.add(previewButton);
                target.add(generateButton);
            }

        });
        paramFilter.setOutputMarkupId(true);
    }

    private void addResetFiltersLink() {
        filterPanel.add(new ResetFiltersLink("reset-filters", projectFilter, programFilter, applicationFilter)
                .addTargetComponents(chartConfigPanel, filterPanel, experimentsPanel));
    }

    private void addExperimentsList() {
        experimentsPanel.add(experimentList = new ListView<Experiment>("experiment",
                new FinishedExperiments()
                        .filterBy("application.program", programFilter.getModel())
                        .filterBy("application.program.project", projectFilter.getModel())
                        .filterBy("application", applicationFilter.getModel())) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Experiment> item) {
                item.add(new AjaxCheckBox("experiment-check",
                        new ExperimentCheckboxModel(item.getModelObject().getId())) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        settings.setObject(new ChartSettings());
                        target.add(chartConfigPanel);
                    }
                });
                item.add(new Label("experiment-name", item.getModelObject().getName()));
            }
        });
        experimentList.setOutputMarkupId(true);
    }

    private void addApplicationFilter(final PageParameters parameters) {
        applicationFilter = new AjaxDropDownChoice<Application>(
                "applicationFilter",
                new ApplicationModel(parameters.get(PRESELECT_APPLICATION_ID).toOptionalInteger()),
                new AvailableApplications()
                        .filterBy("program.project", projectFilter.getModel())
                        .filterBy("program", programFilter.getModel()),
                PropertyChoiceRenderer.APPLICATION_FULL_NAME_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                settings.setObject(new ChartSettings());
                target.add(filterPanel);
                target.add(chartConfigPanel);
                target.add(experimentsPanel);
            }
        };
        filterPanel.add(applicationFilter.setNullValid(true));
        parameters.remove(PRESELECT_APPLICATION_ID);
    }

    private AjaxDropDownChoice<Program> addProgramFilter(final PageParameters parameters) {
        programFilter = new AjaxDropDownChoice<Program>("programFilter",
                new ProgramModel(parameters.get(PRESELECT_PROGRAM_ID).toOptionalInteger()),
                new AvailablePrograms().filterBy("project", projectFilter.getModel()),
                PropertyChoiceRenderer.PROGRAM_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                settings.setObject(new ChartSettings());
                target.add(filterPanel);
                target.add(chartConfigPanel);
                target.add(experimentsPanel);
            }
        };
        filterPanel.add(programFilter.setNullValid(true));
        parameters.remove(PRESELECT_PROGRAM_ID);
        return programFilter;
    }

    private void addProjectFilter(final PageParameters parameters) {
        projectFilter = new AjaxDropDownChoice<Project>("projectFilter",
                new ProjectModel(parameters.get(PRESELECT_PROJECT_ID).toOptionalInteger()),
                new AvailableProjects(),
                PropertyChoiceRenderer.PROJECT_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                settings.setObject(new ChartSettings());
                target.add(filterPanel);
                target.add(chartConfigPanel);
                target.add(experimentsPanel);
            }
        };
        filterPanel.add(projectFilter.setNullValid(true));
        parameters.remove(PRESELECT_PROJECT_ID);
    }

    private AjaxDownload createAjaxDownload(final IModel<File> fileModel, final IModel<String> fileName) {
        return new AjaxDownload() {

            private static final long serialVersionUID = -7953430803017800750L;

            @Override
            public void initiate(AjaxRequestTarget target) {
                if (fileModel.getObject() == null || !fileModel.getObject().exists()) {
                    error("Unable to download the requested file.");
                } else {
                    super.initiate(target);
                }
            }

            @Override
            public String getFileName() {
                if (fileName != null && fileName.getObject() != null) {
                    return fileName.getObject();
                } else {
                    return fileModel.getObject().getName();
                }
            }

            @Override
            protected IResourceStream getResourceStream() {
                IResourceStream result = null;
                final File file = fileModel.getObject();
                result = new FileResourceStream(file);
                return result;
            }

            @Override
            protected void onAfterDownload() {
                File file = fileModel.getObject();
                fileModel.detach();
                Files.remove(file);
            }
        };
    }

    /**
     * Helper model, that transforms the boolean flag of every checkbox into the presence of the represented experiment
     * id in the selectedExperiments set.
     * 
     * @author jfaryad
     * 
     */
    private class ExperimentCheckboxModel extends LoadableDetachableModel<Boolean> {

        private static final long serialVersionUID = 1L;
        private final Integer experimentId;

        private ExperimentCheckboxModel(Integer experimentId) {
            this.experimentId = experimentId;
        }

        @Override
        public void setObject(Boolean object) {
            super.setObject(object);
            if (object) {
                selectedExperiments.getObject().add(experimentId);
            } else {
                selectedExperiments.getObject().remove(experimentId);
            }
        }

        @Override
        protected Boolean load() {
            return selectedExperiments.getObject().contains(experimentId);
        }
    }

    private class AvailableExperimentParameters extends LoadableDetachableModel<List<String>> {

        private static final long serialVersionUID = 1L;

        @Override
        protected List<String> load() {
            return resultService.findAllExperimentParameters(selectedExperiments.getObject());
        }
    }

    private class SelectedExperimentsModel extends LoadableDetachableModel<Set<Integer>> {

        private static final long serialVersionUID = 1L;
        private final Set<Integer> selectedExperiments = new HashSet<Integer>();

        @Override
        protected Set<Integer> load() {
            List<Experiment> availableExperiments = experimentList.getModelObject();
            Set<Integer> availableIds = new HashSet<Integer>();
            for (Experiment experiment : availableExperiments) {
                availableIds.add(experiment.getId());
            }
            Integer experimentIdFromPageParams = getPageParameters().get(PRESELECT_EXPERIMENT_ID).toOptionalInteger();
            if (experimentIdFromPageParams != null && availableIds.contains(experimentIdFromPageParams)) {
                selectedExperiments.add(experimentIdFromPageParams);
            }
            for (Iterator<Integer> it = selectedExperiments.iterator(); it.hasNext();) {
                Integer experimentId = it.next();
                if (!availableIds.contains(experimentId)) {
                    it.remove();
                }
            }
            return selectedExperiments;
        }

    }

}
