package org.experimenter.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.datatable.ProjectTable;
import org.experimenter.web.model.ProblemTypeModel;
import org.experimenter.web.model.aggregate.AvailableProblemTypes;
import org.experimenter.web.provider.ProjectProvider;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Page for project administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class ProjectPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public static final String PRESELECT_PROBLEM_ID = "problemId";
    private final ProjectTable dataTable;

    public ProjectPage(final PageParameters parameters) {
        super(parameters, "Projects");

        final AjaxDropDownChoice<ProblemType> problemTypeFilter = new AjaxDropDownChoice<ProblemType>(
                "problemTypeFilter",
                new ProblemTypeModel(parameters.get(PRESELECT_PROBLEM_ID).toOptionalInteger()),
                new AvailableProblemTypes(),
                PropertyChoiceRenderer.PROBLEM_TYPE_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(dataTable);
            }
        };
        parameters.remove(PRESELECT_PROBLEM_ID);
        add(problemTypeFilter.setNullValid(true));

        add(dataTable = new ProjectTable("project-table", new ProjectProvider(problemTypeFilter.getModel())));
    }
}
