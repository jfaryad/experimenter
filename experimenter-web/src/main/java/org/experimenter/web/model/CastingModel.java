package org.experimenter.web.model;

import org.apache.wicket.model.IModel;

/**
 * Convenience implemetation of a model, that casts the underlying model object to another type. Can be used when an
 * IModel<Object> needs to be cast to some more specific model.
 * 
 * @author jfaryad
 * 
 * @param <In>
 *            type of the original (inside) model
 * @param <Out>
 *            type of the object to return to the outside
 */
public class CastingModel<In, Out> implements IModel<Out> {

    private static final long serialVersionUID = 1L;
    private final IModel<In> originalModel;

    public CastingModel(IModel<In> originalModel) {
        this.originalModel = originalModel;
    }

    @Override
    public void detach() {
        originalModel.detach();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Out getObject() {
        return (Out) originalModel.getObject();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setObject(Out object) {
        originalModel.setObject((In) object);
    }

}
