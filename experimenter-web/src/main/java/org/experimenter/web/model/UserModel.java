package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.UserService;

/**
 * Default entity model for the {@link User} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class UserModel extends EntityModel<User> {

    @SpringBean
    private UserService userService;

    private static final long serialVersionUID = 1L;

    public UserModel(User user) {
        super(user);
    }

    public UserModel(Integer id) {
        super(id);
    }

    @Override
    protected User loadForId(Integer id) {
        return userService.findById(id);
    }

}
