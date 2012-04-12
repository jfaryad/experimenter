package org.experimenter.web.common.panel;

import java.text.ParseException;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.UserService;
import org.experimenter.web.model.UserModel;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * Simple panel with a form to edit the {@link User} entity.
 * 
 * @author jfaryad
 * 
 */
public class UserFormPanel extends EntityFormPanel<User> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserService userService;

    @SpringBean
    private Scheduler schedulerFactory;

    public UserFormPanel(String id, UserModel userModel) {
        super(id, userModel);
    }

    @Override
    protected void addFieldsToForm(Form<User> form) {
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("surname"));
        form.add(new RequiredTextField<String>("email"));

    }

    @Override
    protected void save(User user) {
        userService.saveUpdate(user);
        CronTrigger cronTrigger;
        try {
            cronTrigger = (CronTrigger) schedulerFactory.getTrigger("cronTrigger", "DEFAULT");
            String newCronExpression = "0/1 * * * * ?";

            // Creating a new cron trigger
            CronTrigger updatedCronTrigger = new CronTrigger();
            updatedCronTrigger.setJobName(cronTrigger.getJobName());
            updatedCronTrigger.setName("cronTrigger");
            updatedCronTrigger.setCronExpression(newCronExpression);
            // Reschedule the job with updated cron expression
            schedulerFactory.rescheduleJob("cronTrigger", "DEFAULT", updatedCronTrigger);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
