package org.experimenter.web.model;

import org.apache.commons.io.FilenameUtils;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * A model, that converts the underlying full file path into the short file name.
 * 
 * @author jfaryad
 * 
 */
public class FileNameModel extends LoadableDetachableModel<String> {

    private static final long serialVersionUID = 1L;
    private final IModel<String> fullFileNameModel;

    public FileNameModel(IModel<String> fullFileNameModel) {
        this.fullFileNameModel = fullFileNameModel;
    }

    @Override
    protected String load() {
        String fullPath = fullFileNameModel.getObject();
        if (fullPath == null) {
            return null;
        }
        return FilenameUtils.getName(fullPath);
    }

    @Override
    public void detach() {
        super.detach();
        fullFileNameModel.detach();
    }
}
