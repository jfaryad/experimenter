package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.service.InputService;
import org.experimenter.web.model.InputModel;

/**
 * Simple panel with a form to edit the {@link Input} entity.
 * 
 * @author jfaryad
 * 
 */
public class InputFormPanel extends EntityFormPanel<Input> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputService inputService;

    public InputFormPanel(String id, InputModel inputModel) {
        super(id, inputModel);
    }

    @Override
    protected void addFieldsToForm(Form<Input> form) {
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("data"));

    }

    @Override
    protected void save(Input input) {
        inputService.saveUpdate(input);
    }

}
