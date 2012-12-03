package org.experimenter.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.component.ResetFiltersLink;
import org.experimenter.web.datatable.InputSetTable;
import org.experimenter.web.model.ExperimentModel;
import org.experimenter.web.model.ProblemTypeModel;
import org.experimenter.web.model.ProjectModel;
import org.experimenter.web.model.aggregate.AvailableProblemTypes;
import org.experimenter.web.model.aggregate.AvailableProjects;
import org.experimenter.web.provider.InputSetProvider;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Page for inputSet administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class InputSetPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public static final String PRESELECT_PROBLEM_ID = "problemId";
    public static final String PRESELECT_PROJECT_ID = "projectId";
    public static final String PRESELECT_EXPERIMENT_ID = "experimentId";

    private final InputSetTable dataTable;
    private final IModel<Experiment> experimentModel;

    public InputSetPage(final PageParameters parameters) {
        super(parameters, "InputSets");

        final AjaxDropDownChoice<ProblemType> problemTypeFilter = new AjaxDropDownChoice<ProblemType>(
                "problemTypeFilter",
                new ProblemTypeModel(parameters.get(PRESELECT_PROBLEM_ID).toOptionalInteger()),
                new AvailableProblemTypes(),
                PropertyChoiceRenderer.PROBLEM_TYPE_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                experimentModel.setObject(null);
                target.add(InputSetPage.this);
            }
        };
        parameters.remove(PRESELECT_PROBLEM_ID);
        add(problemTypeFilter.setNullValid(true));

        final AjaxDropDownChoice<Project> projectFilter = new AjaxDropDownChoice<Project>("projectFilter",
                new ProjectModel(parameters.get(PRESELECT_PROJECT_ID).toOptionalInteger()),
                new AvailableProjects(),
                PropertyChoiceRenderer.PROJECT_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                experimentModel.setObject(null);
                target.add(InputSetPage.this);
            }
        };
        parameters.remove(PRESELECT_PROJECT_ID);
        add(projectFilter.setNullValid(true));

        experimentModel = new ExperimentModel(parameters.get(PRESELECT_EXPERIMENT_ID).toOptionalInteger());

        parameters.remove(PRESELECT_EXPERIMENT_ID);

        WebMarkupContainer experimentFilterContainer = new WebMarkupContainer("experimentFilterContainer") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisibilityAllowed(experimentModel.getObject() != null);
            }
        };
        experimentFilterContainer.setOutputMarkupId(true);
        TextField<String> experimentFilter = new TextField<String>("experimentName", new SelectedExperimentNameModel());
        experimentFilter.setEnabled(false);
        experimentFilterContainer.add(experimentFilter);
        add(experimentFilterContainer);

        add(dataTable = new InputSetTable("inputSet-table",
                new InputSetProvider(problemTypeFilter.getModel(), projectFilter.getModel(), experimentModel)) {

            private static final long serialVersionUID = 1L;

            @Override
            protected InputSet getNewEntity() {
                InputSet inputSet = super.getNewEntity();
                inputSet.setProblem(problemTypeFilter.getModelObject());
                return inputSet;
            }

        });

        add(new ResetFiltersLink("reset-filters", projectFilter, problemTypeFilter) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                super.onClick(target);
                experimentModel.setObject(null);
            };

        }.addTargetComponents(dataTable, experimentFilterContainer));
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        experimentModel.detach();
    }

    private class SelectedExperimentNameModel extends LoadableDetachableModel<String> {

        private static final long serialVersionUID = 1L;

        @Override
        protected String load() {
            if (experimentModel.getObject() != null) {
                return experimentModel.getObject().getName();
            } else {
                return "";
            }
        }

    }
}
