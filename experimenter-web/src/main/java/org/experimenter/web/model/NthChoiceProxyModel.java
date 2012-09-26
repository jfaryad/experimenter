package org.experimenter.web.model;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * A proxy model, that wraps the original supplied model (most probably of a dropdown component) and if that model
 * returns a null object and the choice list is not empty (the choice list will most probably be the model of the
 * choices of the dropdown component), the nth (starting from 0) choice will be returned as the model object. <br>
 * If there are not so many choices, null will be returned.
 * 
 * @author jfaryad
 */
public class NthChoiceProxyModel<T> extends PreselectChoiceProxyModel<T> {

    private static final long serialVersionUID = 2129644360633767687L;
    private final int indexToChoose;

    /**
     * A proxy model, that wraps the original supplied model (most probably of a dropdown component) and if that model
     * returns a null object and the choice list is not empty (the choice list will most probably be the model of the
     * choices of the dropdown component), the nth (starting from 0) choice will be returned as the model object. <br>
     * If there are not so many choices, null will be returned.
     * 
     * @param selectedModel
     *            the model to wrap
     * @param choices
     *            a model of a list to select the nth element from, if the wrapped model returns a null object.
     * @param indexToChoose
     *            the index (0-based) of the choice from the list of choices to chose, if the original model returns
     *            null.
     */
    public NthChoiceProxyModel(final IModel<T> selectedModel, final IModel<? extends List<? extends T>> choices,
            final int indexToChoose) {
        super(selectedModel, choices);
        Args.isTrue(indexToChoose >= 0, "Index to choose must not be negative.");
        this.indexToChoose = indexToChoose;
    }

    @Override
    protected T pickPreselection(final IModel<? extends List<? extends T>> choices) {
        if (choices.getObject().size() > indexToChoose) {
            return choices.getObject().get(indexToChoose);
        } else {
            return null;
        }
    }

}
