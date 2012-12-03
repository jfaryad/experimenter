package org.experimenter.web.datatable.column;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.experimenter.web.model.CastingModel;
import org.experimenter.web.model.ReadOnlyFileModel;

/**
 * Transforms the underlying string property model to a IModel<File> and displays a download link to this file.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public abstract class DownloadLinkPropertyColumn<T> extends PropertyColumn<T, String> {

    private static final long serialVersionUID = 1L;

    private final Component feedbackPanel;

    public DownloadLinkPropertyColumn(IModel<String> headerModel, String propertyExpression, Component feedbackPanel) {
        super(headerModel, propertyExpression);
        this.feedbackPanel = feedbackPanel;
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        cellItem.add(new DownloadLinkCell(componentId,
                new ReadOnlyFileModel(new CastingModel<Object, String>(getDataModel(rowModel))), feedbackPanel,
                getFileName(rowModel)));
    }

    protected abstract IModel<String> getFileName(IModel<T> rowModel);

}
