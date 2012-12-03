package org.experimenter.web.common.panel;

import java.io.File;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.service.ApplicationService;
import org.experimenter.web.component.FinalEntityPropertyDropDownChoice;
import org.experimenter.web.component.FinalRequiredTextField;
import org.experimenter.web.component.TmpFileUploadField;
import org.experimenter.web.model.ApplicationModel;
import org.experimenter.web.model.aggregate.AvailablePrograms;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

/**
 * Simple panel with a form to edit the {@link Application} entity.
 * 
 * @author jfaryad
 * 
 */
public class ApplicationFormPanel extends EntityFormPanel<Application> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ApplicationService applicationService;

    private TmpFileUploadField fileUploadField;

    public ApplicationFormPanel(String id, ApplicationModel applicationModel) {
        super(id, applicationModel);
        getForm().setMultiPart(true);
    }

    @Override
    protected void addFieldsToForm(Form<Application> form) {
        form.add(new FinalEntityPropertyDropDownChoice<Application, Program>("program", new AvailablePrograms(),
                PropertyChoiceRenderer.PROGRAM_RENDERER, form.getModel()).setRequired(true));
        form.add(new FinalRequiredTextField<Application>("version", form.getModel()));
        form.add(fileUploadField = new TmpFileUploadField("executable"));
        fileUploadField.setRequired(true);

    }

    @Override
    protected void save(Application application) {
        File tmpUploadFile = fileUploadField.uploadToTmpFile();
        applicationService.saveWithData(application, tmpUploadFile);
    }

}
