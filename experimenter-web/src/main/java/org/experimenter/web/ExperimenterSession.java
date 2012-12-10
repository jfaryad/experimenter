package org.experimenter.web;

import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.UserService;
import org.springframework.util.DigestUtils;

/**
 * Custom implementation of {@link AuthenticatedWebSession} to work with our user databse.
 * 
 * @author jfaryad
 * 
 */
public class ExperimenterSession extends AuthenticatedWebSession {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserService userService;

    private Integer currentUserId;
    private transient User currentUser;

    /**
     * @return Current {@link ExperimenterSession}
     */
    public static ExperimenterSession get()
    {
        return (ExperimenterSession) Session.get();
    }

    public ExperimenterSession(Request request) {
        super(request);
        Injector.get().inject(this);
    }

    @Override
    public boolean authenticate(String username, String password) {
        if (currentUserId == null) {
            User searchForm = new User();
            searchForm.setLogin(username);
            searchForm.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            List<User> users = userService.findByExample(searchForm);
            if (!users.isEmpty()) {
                currentUserId = users.get(0).getId();
            }
        }
        return currentUserId != null;
    }

    @Override
    public Roles getRoles() {
        return new Roles(new String[] { Roles.USER, Roles.ADMIN });
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        }
        if (currentUserId != null) {
            return currentUser = userService.findById(currentUserId);
        }
        return null;
    }

    @Override
    public void detach() {
        super.detach();
        currentUser = null;
    }

}
