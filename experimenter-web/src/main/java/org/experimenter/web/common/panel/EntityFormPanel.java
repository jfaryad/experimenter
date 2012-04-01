package org.experimenter.web.common.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Entity;
import org.experimenter.web.model.EntityModel;

/**
 * Parent class for all entity form panels. It wraps the given {@link EntityModel} with a {@link CompoundPropertyModel}
 * to simplify adding entity properties. It contains a form and an abstract method for calling the repository service
 * when persisting the changes.<br>
 * It also adds support for being injected with the @SpringBean annotation.
 * 
 * @author jfaryad
 * 
 * @param <T>
 *            the entity displayed by this panel
 */
public abstract class EntityFormPanel<T extends Entity> extends Panel {
    private static final long serialVersionUID = 1L;

    private Form<T> form;
    private ModalWindow modalContainer;

    /**
     * Creates a new Entity Panel
     * 
     * @param id
     *            wicket id
     * @param model
     *            the model of the entity to be displayed on this panel
     */
    public EntityFormPanel(String id, IModel<T> model) {
        super(id, model);
        Injector.get().inject(this);
        setOutputMarkupId(true);
        form = createForm(model);
        addFieldsToForm(form);
        add(form);
    }

    public void setEntityModel(IModel<T> model) {
        this.form.setModel(new CompoundPropertyModel<T>(model));
    }

    /**
     * Adds all the input fields to the given form.
     * 
     * @param form
     *            the form of the panel to add the fields to.
     */
    protected abstract void addFieldsToForm(Form<T> form);

    /**
     * Calls the repository service and saves the given entity.
     * 
     * @param entity
     *            the entity to save.
     */
    protected abstract void save(T entity);

    public void setModalContainer(ModalWindow modalContainer) {
        this.modalContainer = modalContainer;
    }

    public void onSubmit(AjaxRequestTarget target) {
        if (modalContainer != null)
            modalContainer.close(target);
    }

    public void onCancel(AjaxRequestTarget target) {
        if (modalContainer != null)
            modalContainer.close(target);
    }

    private Form<T> createForm(IModel<T> model) {
        Form<T> form = new Form<T>("entity-form", new CompoundPropertyModel<T>(model)) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
            }
        };
        form.add(new AjaxButton("save") {

            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                save((T) form.getModelObject());
                EntityFormPanel.this.onSubmit(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // TODO Auto-generated method stub

            }
        });
        form.add(new AjaxButton("cancel") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                EntityFormPanel.this.onCancel(target);

            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // TODO Auto-generated method stub

            }
        });
        return form;
    }
}
