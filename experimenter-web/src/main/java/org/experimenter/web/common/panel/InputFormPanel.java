package org.experimenter.web.common.panel;

import java.io.File;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.InputService;
import org.experimenter.web.component.FinalEntityPropertyDropDownChoice;
import org.experimenter.web.component.TmpFileUploadField;
import org.experimenter.web.model.InputModel;
import org.experimenter.web.model.aggregate.AvailableProblemTypes;
import org.experimenter.web.renderer.PropertyChoiceRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple panel with a form to edit the {@link Input} entity.
 * 
 * @author jfaryad
 * 
 */
public class InputFormPanel extends EntityFormPanel<Input> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(InputFormPanel.class);

    @SpringBean
    private InputService inputService;

    private TmpFileUploadField fileUploadField;

    public InputFormPanel(String id, InputModel inputModel) {
        super(id, inputModel);
    }

    @Override
    protected void addFieldsToForm(Form<Input> form) {
        form.add(new FinalEntityPropertyDropDownChoice<Input, ProblemType>("problem", new AvailableProblemTypes(),
                PropertyChoiceRenderer.PROBLEM_TYPE_RENDERER, form.getModel()));
        form.add(new RequiredTextField<String>("name"));
        form.add(fileUploadField = new TmpFileUploadField("data"));
        fileUploadField.setRequired(true);

    }

    @Override
    protected void save(Input input) {
        File tmpUploadFile = fileUploadField.uploadToTmpFile();
        inputService.saveWithData(input, tmpUploadFile);
    }

}
