package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.service.InputSetService;
import org.experimenter.web.model.InputSetModel;

/**
 * Simple panel with a form to edit the {@link InputSet} entity.
 * 
 * @author jfaryad
 * 
 */
public class InputSetFormPanel extends EntityFormPanel<InputSet> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputSetService inputSetService;

    public InputSetFormPanel(String id, InputSetModel inputSetModel) {
        super(id, inputSetModel);
    }

    @Override
    protected void addFieldsToForm(Form<InputSet> form) {
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("description"));

    }

    @Override
    protected void save(InputSet inputSet) {
        inputSetService.saveUpdate(inputSet);
    }

}
