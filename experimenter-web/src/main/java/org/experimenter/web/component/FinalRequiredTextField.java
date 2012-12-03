package org.experimenter.web.component;

import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;
import org.experimenter.repository.entity.Entity;

/**
 * A textfield of a property of an entity, that is enabled only as log as the entity is not persisted.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public class FinalRequiredTextField<T extends Entity> extends RequiredTextField<String> {

    private static final long serialVersionUID = 1L;
    private final IModel<T> entityModel;

    public FinalRequiredTextField(String id, IModel<T> entityModel) {
        super(id);
        Args.notNull(entityModel, "entityModel");
        this.entityModel = entityModel;
    }

    public FinalRequiredTextField(String id, IModel<String> model, IModel<T> entityModel) {
        super(id, model);
        Args.notNull(entityModel, "entityModel");
        this.entityModel = entityModel;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        setEnabled(entityModel.getObject() != null && entityModel.getObject().getId() == null);
    }

}
