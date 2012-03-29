package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.experimenter.repository.entity.User;
import org.experimenter.web.model.UserModel;

/**
 * Simple panel displaying properties of the {@link User} entity.
 * 
 * @author jfaryad
 * 
 */
public class UserPanel extends EntityPanel<User> {

    private static final long serialVersionUID = 1L;

    public UserPanel(String id, UserModel userModel) {
        super(id, userModel);
    }

    @Override
    protected void initializeComponents() {
        add(new Label("name"));
        add(new Label("surname"));
        add(new Label("email"));
    }

}
