package org.experimenter.web.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.wicket.model.IModel;

/**
 * Model wrapping a string model of a cron expression but on the outside acting as a model of the date represented by
 * the given cron expression.
 * 
 * @author jfaryad
 * 
 */
public class CronExpressionDateModel implements IModel<Date> {

    private static final long serialVersionUID = 1L;

    private final IModel<String> cronExpression;

    /**
     * Constructor.
     * 
     * @param cronExpression
     *            the cron expression to wrap
     */
    public CronExpressionDateModel(IModel<String> cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public Date getObject() {
        if (cronExpression.getObject() == null) {
            return null;
        } else {
            String old = cronExpression.getObject();
            Calendar cal = DateUtils.truncate(Calendar.getInstance(), Calendar.MINUTE);
            cal.set(Calendar.YEAR, Integer.parseInt(old.substring(17)));
            cal.set(Calendar.MONTH, Integer.parseInt(old.substring(12, 14)) - 1);
            cal.set(Calendar.DATE, Integer.parseInt(old.substring(9, 11)));
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(old.substring(6, 8)));
            cal.set(Calendar.MINUTE, Integer.parseInt(old.substring(3, 5)));
            return cal.getTime();
        }
    }

    @Override
    public void setObject(Date object) {
        if (object != null) {
            String year = new SimpleDateFormat("yyyy").format(object);
            String month = new SimpleDateFormat("MM").format(object);
            String day = new SimpleDateFormat("dd").format(object);
            String hours = new SimpleDateFormat("HH").format(object);
            String minutes = new SimpleDateFormat("mm").format(object);
            cronExpression.setObject("00 " + minutes + " " + hours + " " + day + " " + month + " ? " + year);
        }
    }

    @Override
    public void detach() {
        // do nothing
    }

}