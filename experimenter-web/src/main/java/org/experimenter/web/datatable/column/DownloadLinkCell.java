package org.experimenter.web.datatable.column;

import java.io.File;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.experimenter.web.component.AjaxDownloadLink;

/**
 * The cell in a data table containing a download.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public class DownloadLinkCell extends Panel {

    private static final long serialVersionUID = 1L;

    public DownloadLinkCell(String id, final IModel<File> fileModel, Component feedbackPanel, IModel<String> fileName) {
        super(id);

        add(new AjaxDownloadLink("link", fileModel, fileName, feedbackPanel).setDeleteAfterDownload(true));
    }

}
