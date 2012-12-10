package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.UserService;

/**
 * Model of all users.
 * 
 * @author jfaryad
 * 
 */
public class AvailableUsers extends LoadableDetachableModel<List<User>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserService userService;

    public AvailableUsers() {
        Injector.get().inject(this);
    }

    @Override
    protected List<User> load() {
        return userService.findByExample(new User());
    }

}
