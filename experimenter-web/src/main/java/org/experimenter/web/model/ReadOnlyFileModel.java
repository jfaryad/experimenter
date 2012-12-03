package org.experimenter.web.model;

import java.io.File;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.lang.Args;

/**
 * A simple read only model that converts the underlying string model of a file path into a file. <br>
 * Throws {@link UnsupportedOperationException} upon {@link #setObject(File)}
 * 
 * @author jfaryad
 * 
 */
public class ReadOnlyFileModel extends LoadableDetachableModel<File> {

    private static final long serialVersionUID = 1L;
    private final IModel<String> filePath;

    public ReadOnlyFileModel(IModel<String> filePath) {
        this.filePath = Args.notNull(filePath, "filePath");
    }

    @Override
    protected File load() {
        if (filePath.getObject() != null) {
            return new File(filePath.getObject());
        } else {
            return null;
        }
    }

    @Override
    public void setObject(File object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void detach() {
        super.detach();
        filePath.detach();
    }

}
