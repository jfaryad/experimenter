package org.experimenter.web.datatable.column;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.experimenter.repository.entity.Entity;

/**
 * Column containing the clone link in a data table
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public abstract class TableRowCloneColumn<T extends Entity> extends AbstractColumn<T, String> {

    private static final long serialVersionUID = 1L;

    public TableRowCloneColumn() {
        super(new Model<String>(""));
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, final IModel<T> rowModel) {
        cellItem.add(new RowCloneCell<T>(componentId, rowModel) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(AjaxRequestTarget target, IModel<T> rowModel) {
                TableRowCloneColumn.this.onClick(target, rowModel);
            }
        });

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