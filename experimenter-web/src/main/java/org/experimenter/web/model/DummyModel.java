package org.experimenter.web.model;

import org.apache.wicket.model.IModel;

/**
 * A model that does nothing and serves only to override the compound property model lookup in entity forms. <br>
 * Especially for fields like FileUploadField,that don't depend on any property.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public class DummyModel<T> implements IModel<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void detach() {

    }

    @Override
    public T getObject() {
        return null;
    }

    @Override
    public void setObject(T object) {
    }

}
