package org.experimenter.web.component;

import java.io.File;
import java.util.List;

import org.apache.maven.model.Extension;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.experimenter.repository.service.StorageService;
import org.experimenter.web.model.DummyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Extension} of {@link FileUploadField} that adds a method for uploading the file to a temporary location and
 * returning it
 * 
 * @author jfaryad
 * 
 */
public class TmpFileUploadField extends FileUploadField {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(TmpFileUploadField.class);

    @SpringBean
    private StorageService storageService;

    public TmpFileUploadField(String id) {
        super(id, new DummyModel<List<FileUpload>>());
        Injector.get().inject(this);
    }

    /**
     * Uploads the file to some tmp file on the server and returns that file
     * 
     */
    public File uploadToTmpFile() {
        final List<FileUpload> uploads = getFileUploads();
        if (uploads != null && uploads.size() == 1) {
            FileUpload upload = uploads.get(0);
            // Create a new file
            File tmpFile = new File(getTmpUploadFolder(), upload.getClientFileName());

            // Check new file, delete if it already existed
            checkFileExists(tmpFile);
            try {
                // Save to new file
                tmpFile.getParentFile().mkdirs();
                tmpFile.createNewFile();
                upload.writeTo(tmpFile);
                LOG.debug("Uploaded file copied to temporary location: " + tmpFile.getAbsolutePath());
            } catch (Exception e) {
                throw new IllegalStateException("Unable to write file", e);
            }
            return tmpFile;
        }
        return null;
    }

    /**
     * Check whether the file allready exists, and if so, try to delete it.
     * 
     * @param newFile
     *            the file to check
     */
    private void checkFileExists(File newFile) {
        if (newFile.exists()) {
            if (!Files.remove(newFile)) {
                throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
            }
        }
    }

    private Folder getTmpUploadFolder() {
        return new Folder(storageService.getUniqueTemporaryFolderPath());
    }

}
