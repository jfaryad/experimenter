package org.experimenter.web.common.panel;

import java.io.File;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.service.ApplicationService;
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

    private FileUploadField fileUploadField;

    public ApplicationFormPanel(String id, ApplicationModel applicationModel) {
        super(id, applicationModel);
        getForm().setMultiPart(true);
    }

    @Override
    protected void addFieldsToForm(Form<Application> form) {
        form.add(new RequiredTextField<String>("version"));
        form.add(fileUploadField = new FileUploadField("executable"));
        form.add(new DropDownChoice<Program>("program", new AvailablePrograms(),
                PropertyChoiceRenderer.PROGRAM_RENDERER));

    }

    @Override
    protected void save(Application application) {
        uploadFile(application);
        applicationService.saveUpdate(application);
    }

    private void uploadFile(Application application) {
        final List<FileUpload> uploads = fileUploadField.getFileUploads();
        if (uploads != null && uploads.size() == 1) {
            FileUpload upload = uploads.get(0);
            // Create a new file
            File newFile = new File(getUploadFolder(application), upload.getClientFileName());

            // Check new file, delete if it already existed
            checkFileExists(newFile);
            try {
                // Save to new file
                newFile.getParentFile().mkdirs();
                newFile.createNewFile();
                upload.writeTo(newFile);
            } catch (Exception e) {
                throw new IllegalStateException("Unable to write file", e);
            }
            application.setExecutable(newFile.getAbsolutePath());

        }
    }

    /**
     * Check whether the file allready exists, and if so, try to delete it.
     * 
     * @param newFile
     *            the file to check
     */
    private void checkFileExists(File newFile)
    {
        if (newFile.exists()) {
            if (!Files.remove(newFile)) {
                throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
            }
        }
    }

    private Folder getUploadFolder(Application application) {
        return new Folder("/home/jfaryad/expstore/executables/" + application.getProgram().getProject().getId() + "/"
                + application.getProgram().getId() + "/"
                + application.getVersion());
    }

}
