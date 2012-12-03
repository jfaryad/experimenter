package org.experimenter.web.datatable.column;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.experimenter.web.model.CastingModel;
import org.experimenter.web.model.CronExpressionDateModel;

/**
 * An {@link IColumn} that displays a string cron expression property as a formated date
 * 
 * @author jfaryad
 * 
 */
public class DatePropertyColumn<T> extends PropertyColumn<T, String> {

    private static final long serialVersionUID = 1L;

    public DatePropertyColumn(IModel<String> headerModel, String propertyExpression) {
        super(headerModel, propertyExpression);
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        IModel<Date> dataModel = new CronExpressionDateModel(new CastingModel<Object, String>(getDataModel(rowModel)));
        String date;
        if (dataModel.getObject() == null) {
            date = "";
        } else {
            date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(dataModel.getObject());
        }
        cellItem.add(new Label(componentId, date));
    }

}
