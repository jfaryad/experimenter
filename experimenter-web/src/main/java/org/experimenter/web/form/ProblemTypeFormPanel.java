package org.experimenter.web.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.ProblemTypeService;
import org.experimenter.web.model.ProblemTypeModel;

/**
 * Simple panel with a form to edit the {@link ProblemType} entity.
 * 
 * @author jfaryad
 * 
 */
public class ProblemTypeFormPanel extends EntityFormPanel<ProblemType> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProblemTypeService problemTypeService;

    public ProblemTypeFormPanel(String id, ProblemTypeModel problemTypeModel) {
        super(id, problemTypeModel);
    }

    @Override
    protected void addFieldsToForm(Form<ProblemType> form) {
        form.add(new RequiredTextField<String>("name"));
        form.add(new TextField<String>("description"));

    }

    @Override
    protected void save(ProblemType problemType) {
        problemTypeService.saveUpdate(problemType);
    }

}
