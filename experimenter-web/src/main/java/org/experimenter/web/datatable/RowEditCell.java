package org.experimenter.web.datatable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Entity;

public abstract class RowEditCell<T extends Entity> extends Panel {

    private static final long serialVersionUID = 1L;

    public RowEditCell(String id, final IModel<T> rowModel) {
        super(id);
        AjaxLink<String> link = new AjaxLink<String>("edit-link") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                RowEditCell.this.onClick(target, rowModel);
            }
        };
        add(link);
    }

    protected abstract void onClick(AjaxRequestTarget target, IModel<T> rowModel);

}
