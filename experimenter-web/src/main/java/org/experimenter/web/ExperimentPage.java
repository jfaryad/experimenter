package org.experimenter.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.component.ResetFiltersLink;
import org.experimenter.web.datatable.ExperimentTable;
import org.experimenter.web.model.ApplicationModel;
import org.experimenter.web.model.ProgramModel;
import org.experimenter.web.model.ProjectModel;
import org.experimenter.web.model.aggregate.AvailableApplications;
import org.experimenter.web.model.aggregate.AvailablePrograms;
import org.experimenter.web.model.aggregate.AvailableProjects;
import org.experimenter.web.provider.ExperimentProvider;
import org.experimenter.web.renderer.PropertyChoiceRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page for experiment administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class ExperimentPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ExperimentPage.class);
    public static final String PRESELECT_APPLICATION_ID = "applicationId";
    private final ExperimentTable dataTable;

    public ExperimentPage(final PageParameters parameters) {
        super(parameters, "Experiments");

        AjaxDropDownChoice<Project> projectFilter = new AjaxDropDownChoice<Project>("projectFilter",
                new ProjectModel((Project) null),
                new AvailableProjects(),
                PropertyChoiceRenderer.PROJECT_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(dataTable);
            }
        };
        add(projectFilter.setNullValid(true));

        AjaxDropDownChoice<Program> programFilter = new AjaxDropDownChoice<Program>("programFilter",
                new ProgramModel((Program) null),
                new AvailablePrograms().filterBy("project", projectFilter.getModel()),
                PropertyChoiceRenderer.PROGRAM_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(dataTable);
            }
        };
        add(programFilter.setNullValid(true));

        final AjaxDropDownChoice<Application> applicationFilter = new AjaxDropDownChoice<Application>(
                "applicationFilter",
                new ApplicationModel(parameters.get(PRESELECT_APPLICATION_ID).toOptionalInteger()),
                new AvailableApplications()
                        .filterBy("program.project", projectFilter.getModel())
                        .filterBy("program", programFilter.getModel()),
                PropertyChoiceRenderer.APPLICATION_FULL_NAME_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(dataTable);
            }
        };
        add(applicationFilter.setNullValid(true));
        parameters.remove(PRESELECT_APPLICATION_ID);

        add(dataTable = new ExperimentTable(
                "experiment-table",
                new ExperimentProvider(projectFilter.getModel(), programFilter.getModel(), applicationFilter.getModel())) {

            private static final long serialVersionUID = 1L;

            @Override
            protected Experiment getNewEntity() {
                Experiment experiment = super.getNewEntity();
                experiment.setApplication(applicationFilter.getModelObject());
                return experiment;
            }
        });
        add(new ResetFiltersLink("reset-filters", projectFilter, programFilter, applicationFilter)
                .addTargetComponents(dataTable));
        // this.add(new AjaxTimerBehavior(dataTable, 5));
    }
}
