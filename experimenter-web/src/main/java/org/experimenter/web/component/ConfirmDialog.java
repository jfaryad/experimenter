package org.experimenter.web.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

/**
 * A simple confirmation dialog.
 * 
 * @author jfaryad
 * 
 */
public abstract class ConfirmDialog<T> extends ModalWindow {

    private static final long serialVersionUID = 1L;

    public ConfirmDialog(String id, IModel<T> model, IModel<String> message) {
        super(id, model);
        WebMarkupContainer content = new WebMarkupContainer(getContentId());
        content.add(new Label("message", message));
        content.add(new AjaxButton("confirm") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                ConfirmDialog.this.onConfirm(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                close(target);
            }
        });
        content.add(new AjaxButton("cancel") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                ConfirmDialog.this.onCancel(target);
                close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                close(target);
            }

        });
        setContent(content);
    }

    /**
     * Is called when the user clicks the confirm button
     * 
     * @param target
     */
    protected abstract void onConfirm(final AjaxRequestTarget target);

    /**
     * Is called when the user clicks the cancel button
     * 
     * @param target
     */
    protected abstract void onCancel(final AjaxRequestTarget target);
}
