package org.experimenter.web.model;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * A proxy model, that wraps the original supplied model (most probably of a dropdown component) and if that model
 * returns a null object and the choice list is not empty and doesn't contain null (the choice list will most probably
 * be the model of the choices of the dropdown component), a choice from the list will be picked and returned as the
 * model object. <br>
 * The pick is specified in the {@link #pickPreselection(IModel)}
 * 
 * @author jfaryad
 */
public abstract class PreselectChoiceProxyModel<T> implements IModel<T> {
    private static final long serialVersionUID = -6157632479084541119L;
    private final IModel<T> wrappedModel;
    private final IModel<? extends List<? extends T>> choices;
    private transient T selectedObject;

    /**
     * Creates an instance.
     * 
     * @param originalModel
     *            the model to wrap
     * @param choices
     *            a model of a list to select the an element from, if the wrapped model returns a null object.
     */
    public PreselectChoiceProxyModel(final IModel<T> originalModel, final IModel<? extends List<? extends T>> choices) {
        Args.notNull(originalModel, "The original model passed to a PreselectChoiceProxyModel cannot be null!");
        Args.notNull(choices, "The model of choices passed to a PreselectChoiceProxyModel cannot be null!");
        this.wrappedModel = originalModel;
        this.choices = choices;
    }

    @Override
    public void detach() {
        wrappedModel.detach();
        selectedObject = null;
    }

    @Override
    public T getObject() {
        if (selectedObject != null) {
            return selectedObject;
        }
        if (choiceListNotEmpty()) {
            final T objectFromOriginalModel = wrappedModel.getObject();
            if (isAValidChoice(objectFromOriginalModel)) {
                selectedObject = objectFromOriginalModel;
            } else {
                selectedObject = pickPreselection(choices);
            }
        }
        return selectedObject;
    }

    @Override
    public void setObject(final T object) {
        this.wrappedModel.setObject(object);
        this.selectedObject = object;
    }

    /**
     * Picks a choice from the non-empty list of choices.
     * 
     * @param choices
     *            a non-empty list of choices to pick from
     */
    protected abstract T pickPreselection(final IModel<? extends List<? extends T>> choices);

    protected boolean isAValidChoice(final T object) {
        return choices.getObject().contains(object);
    }

    private boolean choiceListNotEmpty() {
        final List<? extends T> availableChoices = choices.getObject();
        return availableChoices != null && !availableChoices.isEmpty();
    }

}