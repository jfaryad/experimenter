package org.experimenter.web.datatable.column;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.experimenter.repository.entity.Experiment;

/**
 * Column containing the name of a program together with the version
 * 
 * @author jfaryad
 */
public class ProgramVersionColumn extends AbstractColumn<Experiment, String> {

    private static final long serialVersionUID = 1L;

    public ProgramVersionColumn(IModel<String> headerModel) {
        super(headerModel);
    }

    @Override
    public void populateItem(Item<ICellPopulator<Experiment>> cellItem, String componentId,
            final IModel<Experiment> rowModel) {
        cellItem.add(new Label(componentId, new LoadableDetachableModel<String>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected String load() {
                if (rowModel.getObject() == null) {
                    return "";
                }
                return rowModel.getObject().getApplication().getProgram().getName() + " ver. "
                        + rowModel.getObject().getApplication().getVersion();
            }
        }));

    }
}
