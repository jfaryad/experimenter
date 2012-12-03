package org.experimenter.web.datatable.column;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.experimenter.repository.entity.Connection;

/**
 * Column containing the address of a computer together with the port
 * 
 * @author jfaryad
 */
public class AddressPortColumn extends AbstractColumn<Connection, String> {

    private static final long serialVersionUID = 1L;

    public AddressPortColumn(IModel<String> headerModel) {
        super(headerModel);
    }

    @Override
    public void populateItem(Item<ICellPopulator<Connection>> cellItem, String componentId,
            final IModel<Connection> rowModel) {
        cellItem.add(new Label(componentId, new LoadableDetachableModel<String>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected String load() {
                if (rowModel.getObject() == null) {
                    return "";
                }
                return rowModel.getObject().getComputer().getAddress() + ":" + rowModel.getObject().getPort();
            }
        }));

    }
}
