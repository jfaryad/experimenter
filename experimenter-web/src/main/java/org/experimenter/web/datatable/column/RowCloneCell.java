package org.experimenter.web.datatable.column;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Entity;

/**
 * The cell in a entity data table containing the clone link.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public abstract class RowCloneCell<T extends Entity> extends Panel {

    private static final long serialVersionUID = 1L;

    public RowCloneCell(String id, final IModel<T> rowModel) {
        super(id);
        AjaxLink<String> link = new AjaxLink<String>("edit-link") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                RowCloneCell.this.onClick(target, rowModel);
            }
        };

        add(link);
    }

    /**
     * Callback for when the link is clicked
     * 
     * @param target
     *            the {@link AjaxRequestTarget}
     * @param rowModel
     *            the model of the clicked row
     */
    protected abstract void onClick(AjaxRequestTarget target, IModel<T> rowModel);

}
