package org.experimenter.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.datatable.ProgramTable;
import org.experimenter.web.model.ProjectModel;
import org.experimenter.web.model.aggregate.AvailableProjects;
import org.experimenter.web.provider.ProgramProvider;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Page for program administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class ProgramPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public static final String PRESELECT_PROJECT_ID = "projectId";

    public ProgramPage(final PageParameters parameters) {
        super(parameters, "Programs");
        final AjaxDropDownChoice<Project> projectFilter = new AjaxDropDownChoice<Project>("projectFilter",
                new ProjectModel(parameters.get(PRESELECT_PROJECT_ID).toOptionalInteger()),
                new AvailableProjects(),
                PropertyChoiceRenderer.PROJECT_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(ProgramPage.this);
            }
        };
        parameters.remove(PRESELECT_PROJECT_ID);
        add(projectFilter.setNullValid(true));
        add(new ProgramTable("program-table", new ProgramProvider(projectFilter.getModel())) {

            private static final long serialVersionUID = 1L;

            @Override
            protected Program getNewEntity() {
                Program program = super.getNewEntity();
                program.setProject(projectFilter.getModelObject());
                return program;
            }
        });
    }
}
