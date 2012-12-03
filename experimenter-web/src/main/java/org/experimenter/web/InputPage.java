package org.experimenter.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.component.ResetFiltersLink;
import org.experimenter.web.datatable.InputTable;
import org.experimenter.web.model.InputSetModel;
import org.experimenter.web.model.ProblemTypeModel;
import org.experimenter.web.model.aggregate.AvailableInputSets;
import org.experimenter.web.model.aggregate.AvailableProblemTypes;
import org.experimenter.web.provider.InputProvider;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Page for input administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class InputPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public static final String PRESELECT_PROBLEM_ID = "problemId";
    public static final String PRESELECT_INPUTSET_ID = "inputSetId";

    private final InputTable dataTable;

    public InputPage(final PageParameters parameters) {
        super(parameters, "Inputs");

        final AjaxDropDownChoice<ProblemType> problemTypeFilter = new AjaxDropDownChoice<ProblemType>(
                "problemTypeFilter",
                new ProblemTypeModel(parameters.get(PRESELECT_PROBLEM_ID).toOptionalInteger()),
                new AvailableProblemTypes(),
                PropertyChoiceRenderer.PROBLEM_TYPE_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(InputPage.this);
            }
        };
        parameters.remove(PRESELECT_PROBLEM_ID);
        add(problemTypeFilter.setNullValid(true));

        final AjaxDropDownChoice<InputSet> inputSetFilter = new AjaxDropDownChoice<InputSet>(
                "inputSetFilter",
                new InputSetModel(parameters.get(PRESELECT_INPUTSET_ID).toOptionalInteger()),
                new AvailableInputSets().filterBy("problem", problemTypeFilter.getModel()),
                PropertyChoiceRenderer.INPUT_SET_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(InputPage.this);
            }
        };
        parameters.remove(PRESELECT_INPUTSET_ID);
        add(inputSetFilter.setNullValid(true));

        add(dataTable = new InputTable("input-table", new InputProvider(problemTypeFilter.getModel(),
                inputSetFilter.getModel())));

        add(new ResetFiltersLink("reset-filters", problemTypeFilter, inputSetFilter).addTargetComponents(dataTable));
    }
}
