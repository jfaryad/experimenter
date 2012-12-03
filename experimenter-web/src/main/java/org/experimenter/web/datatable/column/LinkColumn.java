package org.experimenter.web.datatable.column;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Entity;

/**
 * Column with a link in a data table
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public abstract class LinkColumn<T extends Entity> extends AbstractColumn<T, String> {

    private static final long serialVersionUID = 1L;

    private final IModel<String> linkText;

    public LinkColumn(IModel<String> headerModel, final IModel<String> linkText) {
        super(headerModel);
        this.linkText = linkText;
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, final IModel<T> rowModel) {
        cellItem.add(new LinkCell<T>(componentId, rowModel, linkText) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(IModel<T> rowModel) {
                LinkColumn.this.onClick(rowModel);
            }

        });

    }

    protected abstract void onClick(final IModel<T> rowModel);
}
