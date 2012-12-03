package org.experimenter.web.common.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
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

    private final Form<T> form;
    private ModalWindow modalContainer;
    private FeedbackPanel feedbackPanel;

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

    public void setFormObject(T object) {
        this.form.setModelObject(object);
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

    protected Form<T> getForm() {
        return form;
    }

    private Form<T> createForm(IModel<T> model) {
        Form<T> form = new Form<T>("entity-form", new CompoundPropertyModel<T>(model)) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
            }
        };
        feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
        form.add(new AjaxButton("save") {

            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                save((T) form.getModelObject());
                target.add(feedbackPanel);
                EntityFormPanel.this.onSubmit(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
            }
        });
        form.add(new AjaxLink<String>("cancel") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                EntityFormPanel.this.onCancel(target);

            }
        });
        return form;
    }
}
