package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.service.ProgramService;
import org.experimenter.web.model.ProgramModel;

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

    @Override
    protected void addFieldsToForm(Form<Program> form) {
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("description"));
        form.add(new RequiredTextField<String>("command"));

    }

    @Override
    protected void save(Program program) {
        programService.saveUpdate(program);
    }

}
