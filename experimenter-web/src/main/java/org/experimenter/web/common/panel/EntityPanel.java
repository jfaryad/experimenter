package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.experimenter.repository.entity.Entity;
import org.experimenter.web.model.EntityModel;

/**
 * Parent class for all entity panels. It wraps the given {@link EntityModel} with a {@link CompoundPropertyModel} to
 * simplify adding entity properties.
 * 
 * @author jfaryad
 * 
 * @param <T>
 *            the entity displayed by this panel
 */
public abstract class EntityPanel<T extends Entity> extends Panel {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new Entity Panel
     * 
     * @param id
     *            wicket id
     * @param model
     *            the model of the entity to be displayed on this panel
     */
    public EntityPanel(String id, EntityModel<T> model) {
        super(id, new CompoundPropertyModel<T>(model));
        initializeComponents();
    }

    /**
     * Adds all the sub-components of the panel.
     */
    protected abstract void initializeComponents();

}
