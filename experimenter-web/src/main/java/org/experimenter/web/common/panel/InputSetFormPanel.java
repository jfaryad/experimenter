package org.experimenter.web.common.panel;

import java.io.File;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.exception.ZipException;
import org.experimenter.repository.service.InputService;
import org.experimenter.repository.service.InputSetService;
import org.experimenter.web.component.AjaxDropDownChoice;
import org.experimenter.web.component.TmpFileUploadField;
import org.experimenter.web.model.InputSetModel;
import org.experimenter.web.model.aggregate.AvailableProblemTypes;
import org.experimenter.web.renderer.PropertyChoiceRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple panel with a form to edit the {@link InputSet} entity.
 * 
 * @author jfaryad
 * 
 */
public class InputSetFormPanel extends EntityFormPanel<InputSet> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(InputSetFormPanel.class);

    @SpringBean
    private InputSetService inputSetService;

    @SpringBean
    private InputService inputService;

    private TmpFileUploadField fileUploadField;

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
        form.add(new TextField<String>("description"));

        form.add(fileUploadField = new TmpFileUploadField("data") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setEnabled(isUnsavedOrHasNoInputs(form.getModelObject()));
                setRequired(isUnsavedOrHasNoInputs(form.getModelObject()));
            }

        });

    }

    @Override
    protected void save(InputSet inputSet) {
        inputSetService.saveUpdate(inputSet);
        File tmpUploadFile = null;
        try {
            tmpUploadFile = fileUploadField.uploadToTmpFile();
        } catch (Exception e) {
            error("An error occured while uploading the file. Please, try again.");
            return;
        }
        try {
            inputService.saveFromZipArchive(inputSet, tmpUploadFile);
        } catch (ZipException e) {
            LOG.error("An error occured while unzipping the inputs", e);
            error("Unable to extract the archive. Please check that it is correctly compressed.");
        } catch (Exception e) {
            LOG.error("An error occured while saving the inputs", e);
            error("An error occured while saving the inputs. Please, try again.");
        }
    }

    private boolean isUnsavedOrHasNoInputs(InputSet inputSet) {
        return (inputSet.getId() == null || (inputSet.getInputs() != null && inputSet.getInputs().isEmpty()));
    }

}
