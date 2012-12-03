package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.ProgramService;
import org.experimenter.web.component.FinalEntityPropertyDropDownChoice;
import org.experimenter.web.model.ProgramModel;
import org.experimenter.web.model.aggregate.AvailableProjects;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Simple panel with a form to edit the {@link Program} entity.
 * 
 * @author jfaryad
 * 
 */
public class ProgramFormPanel extends EntityFormPanel<Program> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProgramService programService;

    public ProgramFormPanel(String id, ProgramModel programModel) {
        super(id, programModel);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addFieldsToForm(Form<Program> form) {
        form.add(new FinalEntityPropertyDropDownChoice<Program, Project>("project", new AvailableProjects(),
                PropertyChoiceRenderer.PROJECT_RENDERER, (IModel<Program>) getDefaultModel()));
        form.add(new RequiredTextField<String>("name"));
        form.add(new TextField<String>("description"));
        form.add(new TextArea<String>("command").setRequired(true));

    }

    @Override
    protected void save(Program program) {
        programService.saveUpdate(program);
    }

}
