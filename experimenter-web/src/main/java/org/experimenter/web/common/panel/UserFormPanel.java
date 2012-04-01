package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.UserService;
import org.experimenter.web.model.UserModel;

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
    }

}
