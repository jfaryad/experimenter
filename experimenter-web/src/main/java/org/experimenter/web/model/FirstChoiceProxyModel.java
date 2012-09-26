package org.experimenter.web.model;

import java.util.List;

import org.apache.wicket.model.IModel;

/**
 * Convenience extension of {@link NthChoiceProxyModel}.<br>
 * A proxy model, that wraps the original supplied model (most probably of a dropdown component) and if that model
 * returns a null object and the choice list is not empty (the choice list will most probably be the model of the
 * choices of the dropdown component), the first choice will be returned as the model object.
 * 
 * @author jfaryad
 */
public class FirstChoiceProxyModel<T> extends NthChoiceProxyModel<T> {

    private static final long serialVersionUID = -5640876123658448636L;

    /**
     * A proxy model, that wraps the original supplied model (most probably of a dropdown component) and if that model
     * returns a null object and the choice list is not empty (the choice list will most probably be the model of the
     * choices of the dropdown component), the first choice will be returned as the model object.
     * 
     * @param selectedModel
     *            the model to wrap
     * @param choices
     *            a model of a list to select the first element from, if the wrapped model returns a null object.
     */
    public FirstChoiceProxyModel(final IModel<T> selectedModel, final IModel<? extends List<? extends T>> choices) {
        super(selectedModel, choices, 0);
    }

}