package org.experimenter.web.datatable.column;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Entity;

/**
 * The cell in a entity data table containing the delete link.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public abstract class RowDeleteCell<T extends Entity> extends Panel {

    private static final long serialVersionUID = 1L;

    public RowDeleteCell(String id, final IModel<T> rowModel) {
        super(id);
        AjaxLink<String> link = new AjaxLink<String>("delete-link") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                RowDeleteCell.this.onClick(target, rowModel);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
            {
                super.updateAjaxAttributes(attributes);

                AjaxCallListener ajaxCallListener = new AjaxCallListener();
                ajaxCallListener.onPrecondition("return confirm('Do you really want to delete this row?');");
                attributes.getAjaxCallListeners().add(ajaxCallListener);
            }
        };

        add(link);
    }

    protected abstract void onClick(AjaxRequestTarget target, IModel<T> rowModel);

}
