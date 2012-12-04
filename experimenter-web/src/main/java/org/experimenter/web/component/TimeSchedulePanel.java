package org.experimenter.web.component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.experimenter.web.model.CronExpressionDateModel;

/**
 * Panel that enables editing a cron expression.
 * 
 * @author jfaryad
 * 
 */
public class TimeSchedulePanel extends Panel {

    private static final long serialVersionUID = 1L;

    private final IModel<Date> scheduledTime;
    private final IModel<Date> cronExpressionConversionModel;
    private final DoubleScheduledTimeModel doubleModel;
    private final IModel<String> hour;
    private final IModel<String> minute;
    private final List<FormComponent<?>> formComponents = new ArrayList<FormComponent<?>>();

    /**
     * Constructor.
     * 
     * @param id
     *            wicket id
     * @param cronExpression
     *            model of the cronExpression to edit
     * @param model
     *            of the scheduled time
     */
    public TimeSchedulePanel(String id, IModel<String> cronExpression, IModel<Date> scheduledTime) {
        super(id);
        this.scheduledTime = scheduledTime;
        cronExpressionConversionModel = new CronExpressionDateModel(cronExpression);
        doubleModel = new DoubleScheduledTimeModel();
        this.setDefaultModel(doubleModel);
        hour = new HourModel(doubleModel);
        minute = new MinuteModel(doubleModel);

        TextField<Date> date = new DateTextField("date", doubleModel, "dd.MM.yyyy");
        date.setRequired(true);
        date.add(new DatePicker());
        add(date);

        DropDownChoice<String> hours = new DropDownChoice<String>("hours", hour, getHours());
        DropDownChoice<String> minutes = new DropDownChoice<String>("minutes", minute, getMinutes());
        add(hours);
        add(minutes);

        formComponents.add(date);
        formComponents.add(hours);
        formComponents.add(minutes);
    }

    // XXX uncomment for disabling the fields past schedule times
    // @Override
    // protected void onConfigure() {
    // super.onConfigure();
    // boolean enabled = scheduledTime.getObject() == null
    // || scheduledTime.getObject().after(Calendar.getInstance().getTime());
    //
    // for (FormComponent<?> component : formComponents) {
    // component.setEnabled(enabled);
    // }
    // }

    @Override
    protected void onModelChanged() {
        super.onModelChanged();
        for (FormComponent<?> component : formComponents) {
            component.modelChanged();
        }
    }

    public List<FormComponent<?>> getFormComponents() {
        return formComponents;
    }

    private static List<String> getMinutes() {
        return getNumberList(0, 60, 1);
    }

    private static List<String> getHours() {
        return getNumberList(0, 24, 1);
    }

    private static List<String> getNumberList(int start, int end, int step) {
        final List<String> res = new ArrayList<String>();
        for (int i = start; i < end; i += step) {
            res.add(i < 10 ? "0" + i : "" + i);
        }
        return res;
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        doubleModel.detach();
        scheduledTime.detach();
        cronExpressionConversionModel.detach();
    }

    /**
     * propagates setObject to both the Date model as well as the cron expression model
     * 
     * @author jfaryad
     * 
     */
    private class DoubleScheduledTimeModel implements IModel<Date> {

        private static final long serialVersionUID = 1L;

        @Override
        public void detach() {
        }

        @Override
        public Date getObject() {
            return scheduledTime.getObject();
        }

        @Override
        public void setObject(Date object) {
            scheduledTime.setObject(object);
            cronExpressionConversionModel.setObject(object);
        }

    }

    private static class HourModel implements IModel<String> {

        private static final long serialVersionUID = 1L;

        private final IModel<Date> time;

        private HourModel(IModel<Date> time) {
            this.time = time;
        }

        @Override
        public String getObject() {
            return time.getObject() == null ? getHours().get(0) : new SimpleDateFormat("HH").format(time.getObject());
        }

        @Override
        public void setObject(String object) {
            if (object != null && time.getObject() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(time.getObject());
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(object));
                time.setObject(cal.getTime());
            }
        }

        @Override
        public void detach() {
            // do nothing
        }

    }

    private static class MinuteModel implements IModel<String> {

        private static final long serialVersionUID = 1L;

        private final IModel<Date> time;

        private MinuteModel(IModel<Date> time) {
            this.time = time;
        }

        @Override
        public String getObject() {
            return time.getObject() == null ? getMinutes().get(0) : new SimpleDateFormat("mm").format(time.getObject());
        }

        @Override
        public void setObject(String object) {
            if (object != null && time.getObject() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(time.getObject());
                cal.set(Calendar.MINUTE, Integer.parseInt(object));
                time.setObject(cal.getTime());
            }
        }

        @Override
        public void detach() {
            // do nothing
        }

    }
}
