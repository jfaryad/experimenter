package org.experimenter.web.component;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;
import org.experimenter.repository.entity.Entity;

/**
 * A {@link DropDownChoice} that cannot be edited once entity whose property is being displayed in this drop down is
 * saved (it has an id)
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public class FinalEntityPropertyDropDownChoice<E extends Entity, T> extends AjaxDropDownChoice<T> {

    private static final long serialVersionUID = 1L;
    private final IModel<E> entity;

    /**
     * Constructor.
     * 
     * @param id
     *            wicket id
     * @param choices
     *            model of the available choices
     * @param renderer
     *            choice renderer
     * @param entity
     *            the entity whose property is being displayed in the dropdown
     */
    public FinalEntityPropertyDropDownChoice(String id, IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer, IModel<E> entity) {
        super(id, choices, renderer);
        Args.notNull(entity, "entity");
        this.entity = entity;
        setRequired(true);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        if (entity.getObject() != null && entity.getObject().getId() != null) {
            setEnabled(false);
        } else {
            setEnabled(true);
        }
    }

    @Override
    public void onChange(AjaxRequestTarget target) {
        // do nothing, override if needed
    }
}
