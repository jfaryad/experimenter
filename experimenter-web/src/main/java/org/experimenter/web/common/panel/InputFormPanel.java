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
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.InputService;
import org.experimenter.web.model.InputModel;
import org.experimenter.web.model.aggregate.AvailableProblemTypes;
import org.experimenter.web.renderer.PropertyChoiceRenderer;

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

    private FileUploadField fileUploadField;

    public InputFormPanel(String id, InputModel inputModel) {
        super(id, inputModel);
    }

    @Override
    protected void addFieldsToForm(Form<Input> form) {
        form.add(new DropDownChoice<ProblemType>("problem", new AvailableProblemTypes(),
                PropertyChoiceRenderer.PROBLEM_TYPE_RENDERER));
        form.add(new RequiredTextField<String>("name"));
        form.add(fileUploadField = new FileUploadField("data"));

    }

    @Override
    protected void save(Input input) {
        uploadFile(input);
        inputService.saveUpdate(input);
    }

    private void uploadFile(Input input) {
        final List<FileUpload> uploads = fileUploadField.getFileUploads();
        if (uploads != null && uploads.size() == 1) {
            FileUpload upload = uploads.get(0);
            // Create a new file
            File newFile = new File(getUploadFolder(input), upload.getClientFileName());

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
            input.setData(newFile.getAbsolutePath());

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

    private Folder getUploadFolder(Input input) {
        return new Folder("/home/jfaryad/expstore/input/" + input.getId());
    }

}
