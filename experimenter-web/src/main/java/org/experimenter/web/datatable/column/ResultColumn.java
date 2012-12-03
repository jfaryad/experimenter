package org.experimenter.web.datatable.column;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Experiment;

/**
 * Column containing links for displaying/downloading the results of an experiment in various formats.
 * 
 * @author jfaryad
 */
public class ResultColumn extends AbstractColumn<Experiment, String> {

    private static final long serialVersionUID = 1L;
    private final Component feedbackPanel;

    public ResultColumn(IModel<String> headerModel, Component feedbackPanel) {
        super(headerModel);
        this.feedbackPanel = feedbackPanel;
    }

    @Override
    public void populateItem(Item<ICellPopulator<Experiment>> cellItem, String componentId,
            final IModel<Experiment> rowModel) {
        cellItem.add(new ResultLinkCell(componentId, rowModel, feedbackPanel));

    }

}
