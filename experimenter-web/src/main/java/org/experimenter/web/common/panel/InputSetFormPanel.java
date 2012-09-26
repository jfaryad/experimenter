package org.experimenter.web.common.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.InputSetService;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.model.InputSetModel;
import org.experimenter.web.model.aggregate.AvailableInputs;
import org.experimenter.web.model.aggregate.AvailableProblemTypes;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

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
    protected void addFieldsToForm(final Form<InputSet> form) {
        form.add(new AjaxDropDownChoice<ProblemType>("problem", new AvailableProblemTypes(),
                PropertyChoiceRenderer.PROBLEM_TYPE_RENDERER) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onChange(AjaxRequestTarget target) {
                target.add(form);
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(((InputSet) InputSetFormPanel.this.getDefaultModel().getObject()).getId() == null);
            }

        });
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("description"));
        form.add(new ListMultipleChoice<Input>("inputs", new AvailableInputs(new PropertyModel<ProblemType>(
                getDefaultModel(), "problem")), PropertyChoiceRenderer.INPUT_RENDERER));

    }

    @Override
    protected void save(InputSet inputSet) {
        inputSetService.saveUpdate(inputSet);
    }

}
