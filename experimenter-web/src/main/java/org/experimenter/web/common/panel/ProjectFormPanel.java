package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.ProjectService;
import org.experimenter.web.model.ProjectModel;
import org.experimenter.web.model.aggregate.AvailableProblemTypes;
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
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("description"));
        form.add(new DropDownChoice<ProblemType>("problem", new AvailableProblemTypes(),
                PropertyChoiceRenderer.PROBLEM_TYPE));

    }

    @Override
    protected void save(Project project) {
        projectService.saveUpdate(project);
    }

}
