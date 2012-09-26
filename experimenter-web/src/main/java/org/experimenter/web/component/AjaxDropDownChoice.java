package org.experimenter.web.component;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

/**
 * Convenience extension of {@link DropDownChoice} that adds an onchange {@link AjaxFormComponentUpdatingBehavior}
 * 
 * @author jfaryad
 */
public abstract class AjaxDropDownChoice<T> extends DropDownChoice<T> {

    private static final long serialVersionUID = 4040832604291533426L;

    /**
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String)
     */
    public AjaxDropDownChoice(final String id) {
        super(id);
        addAjaxBehavior();
    }

    /**
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, List)
     */
    public AjaxDropDownChoice(final String id, final List<? extends T> choices) {
        super(id, choices);
        addAjaxBehavior();
    }

    /**
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, List,IChoiceRenderer)
     */
    public AjaxDropDownChoice(final String id, final List<? extends T> data, final IChoiceRenderer<? super T> renderer) {
        super(id, data, renderer);
        addAjaxBehavior();
    }

    /**
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel, List)
     */
    public AjaxDropDownChoice(final String id, final IModel<T> model, final List<? extends T> choices) {
        super(id, model, choices);
        addAjaxBehavior();
    }

    /**
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel, List, IChoiceRenderer)
     */
    public AjaxDropDownChoice(final String id, final IModel<T> model, final List<? extends T> data,
            final IChoiceRenderer<? super T> renderer) {
        super(id, model, data, renderer);
        addAjaxBehavior();
    }

    /**
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel)
     */
    public AjaxDropDownChoice(final String id, final IModel<? extends List<? extends T>> choices) {
        super(id, choices);
        addAjaxBehavior();
    }

    /**
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel,IModel)
     */
    public AjaxDropDownChoice(final String id, final IModel<T> model, final IModel<? extends List<? extends T>> choices) {
        super(id, model, choices);
        addAjaxBehavior();
    }

    /**
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel,IChoiceRenderer)
     */
    public AjaxDropDownChoice(final String id, final IModel<? extends List<? extends T>> choices,
            final IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
        addAjaxBehavior();
    }

    /**
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, IModel, IModel,IChoiceRenderer)
     */
    public AjaxDropDownChoice(final String id, final IModel<T> model,
            final IModel<? extends List<? extends T>> choices, final IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
        addAjaxBehavior();
    }

    public abstract void onChange(AjaxRequestTarget target);

    protected void addAjaxBehavior() {
        add(new AjaxFormComponentUpdatingBehavior("onchange") {
            private static final long serialVersionUID = 4657014017866082308L;

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                onChange(target);
            }
        });
    }

}
