package org.experimenter.web.component;

import java.io.File;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

/**
 * A link containing a download behavior.
 * 
 * @author jfaryad
 * 
 */
public class AjaxDownloadLink extends AjaxLink<Void> {

    private static final long serialVersionUID = 1L;
    private final Component feedbackComponent;
    private final AjaxDownload download;
    private final IModel<File> fileModel;
    private boolean deleteAfter = false;

    /**
     * Creates the link.
     * 
     * @param id
     *            wicket id
     * @param fileModel
     *            model of the file to download
     * @param fileName
     *            name of the file to download
     * @param feedbackComponent
     *            to feedback panel to update, if anything goes wrong.
     */
    public AjaxDownloadLink(String id, final IModel<File> fileModel, final IModel<String> fileName,
            final Component feedbackComponent) {
        super(id);
        this.feedbackComponent = feedbackComponent;
        this.fileModel = fileModel;
        download = new AjaxDownload() {

            private static final long serialVersionUID = -7953430803017800750L;

            @Override
            public void initiate(AjaxRequestTarget target) {
                if (fileModel.getObject() == null || !fileModel.getObject().exists()) {
                    error("Unable to download the requested file.");
                } else {
                    super.initiate(target);
                }
            }

            @Override
            public String getFileName() {
                if (fileName != null && fileName.getObject() != null) {
                    return fileName.getObject();
                } else {
                    return fileModel.getObject().getName();
                }
            }

            @Override
            protected IResourceStream getResourceStream() {
                IResourceStream result = null;
                final File file = fileModel.getObject();
                result = new FileResourceStream(file);
                return result;
            }

            @Override
            protected void onAfterDownload() {
                File file = fileModel.getObject();
                fileModel.detach();
                if (deleteAfter) {
                    Files.remove(file);
                }
            }
        };
        add(download);
    }

    @Override
    public void onClick(AjaxRequestTarget target) {
        target.add(feedbackComponent);
        download.initiate(target);
    }

    public AjaxDownloadLink setDeleteAfterDownload(boolean deleteAfter) {
        this.deleteAfter = deleteAfter;
        return this;
    }

}
