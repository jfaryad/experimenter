package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.UserService;
import org.experimenter.web.model.UserModel;

/**
 * Default provider of the {@link User} entity.
 * 
 * @author jakub
 * 
 */
public class UserProvider extends EntityDataProvider<User> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserService userService;

    @Override
    public IModel<User> model(User user) {
        return new UserModel(user);
    }

    @Override
    protected List<User> load() {
        // loads all users
        return userService.findByExample(new User());
    }

}
