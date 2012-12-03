package org.experimenter.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.component.ResetFiltersLink;
import org.experimenter.web.datatable.ApplicationTable;
import org.experimenter.web.model.ProgramModel;
import org.experimenter.web.model.ProjectModel;
import org.experimenter.web.model.aggregate.AvailablePrograms;
import org.experimenter.web.model.aggregate.AvailableProjects;
import org.experimenter.web.provider.ApplicationProvider;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Page for application administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class ApplicationPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public static final String PRESELECT_PROGRAM_ID = "programId";
    private final ApplicationTable dataTable;

    public ApplicationPage(final PageParameters parameters) {
        super(parameters, "Applications");

        AjaxDropDownChoice<Project> projectFilter = new AjaxDropDownChoice<Project>("projectFilter",
                new ProjectModel((Project) null),
                new AvailableProjects(),
                PropertyChoiceRenderer.PROJECT_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(ApplicationPage.this);
            }
        };
        add(projectFilter.setNullValid(true));
        final AjaxDropDownChoice<Program> programFilter = new AjaxDropDownChoice<Program>("programFilter",
                new ProgramModel(parameters.get(PRESELECT_PROGRAM_ID).toOptionalInteger()),
                new AvailablePrograms().filterBy("project", projectFilter.getModel()),
                PropertyChoiceRenderer.PROGRAM_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(ApplicationPage.this);
            }
        };
        parameters.remove(PRESELECT_PROGRAM_ID);
        add(programFilter.setNullValid(true));

        add(dataTable = new ApplicationTable("application-table",
                new ApplicationProvider(projectFilter.getModel(), programFilter.getModel())) {

            private static final long serialVersionUID = 1L;

            @Override
            protected Application getNewEntity() {
                Application application = super.getNewEntity();
                application.setProgram(programFilter.getModelObject());
                return application;
            }
        });

        add(new ResetFiltersLink("reset-filters", projectFilter, programFilter).addTargetComponents(dataTable));
    }
}
