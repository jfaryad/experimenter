package org.experimenter.web.common.panel;

import static org.experimenter.web.renderer.PropertyChoiceRenderer.USERGROUP_RENDERER;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.ProjectService;
import org.experimenter.web.component.FinalEntityPropertyDropDownChoice;
import org.experimenter.web.model.ProjectModel;
import org.experimenter.web.model.aggregate.AllInputSetsByProblem;
import org.experimenter.web.model.aggregate.AvailableProblemTypes;
import org.experimenter.web.model.aggregate.AvailableUserGroups;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Simple panel with a form to edit the {@link Project} entity.
 * 
 * @author jfaryad
 * 
 */
public class ProjectFormPanel extends EntityFormPanel<Project> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProjectService projectService;

    public ProjectFormPanel(String id, ProjectModel projectModel) {
        super(id, projectModel);
    }

    @Override
    protected void addFieldsToForm(Form<Project> form) {
        form.add(new FinalEntityPropertyDropDownChoice<Project, UserGroup>("userGroup",
                new AvailableUserGroups(), USERGROUP_RENDERER, form.getModel()));
        form.add(new FinalEntityPropertyDropDownChoice<Project, ProblemType>("problem", new AvailableProblemTypes(),
                PropertyChoiceRenderer.PROBLEM_TYPE_RENDERER, form.getModel()));
        form.add(new RequiredTextField<String>("name"));
        form.add(new TextField<String>("description"));
        form.add(new ListMultipleChoice<InputSet>("inputSets", new AllInputSetsByProblem(
                new PropertyModel<ProblemType>(getDefaultModel(), "problem")),
                PropertyChoiceRenderer.INPUT_SET_RENDERER).setMaxRows(5).setRequired(true));

    }

    @Override
    protected void save(Project project) {
        projectService.saveUpdate(project);
    }

}
