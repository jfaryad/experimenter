package org.experimenter.web.common.panel;

import java.util.Collection;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserService;
import org.experimenter.web.ComponentAuthorization;
import org.experimenter.web.component.FinalRequiredTextField;
import org.experimenter.web.model.UserModel;
import org.experimenter.web.model.aggregate.AllUserGroups;
import org.experimenter.web.renderer.PropertyChoiceRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

/**
 * Simple panel with a form to edit the {@link User} entity.
 * 
 * @author jfaryad
 * 
 */
public class UserFormPanel extends EntityFormPanel<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserFormPanel.class);
    private static final long serialVersionUID = 1L;
    private final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    @SpringBean
    private UserService userService;

    private PasswordTextField password;

    public UserFormPanel(String id, UserModel userModel) {
        super(id, userModel);
    }

    @Override
    protected void addFieldsToForm(final Form<User> form) {
        form.add(new FinalRequiredTextField<User>("login", form.getModel()));

        password = new PasswordTextField("password", Model.of((String) null)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setRequired(form.getModelObject() != null && form.getModelObject().getId() == null);
            }
        };
        password.setResetPassword(false);
        password.setLabel(Model.of("Password"));
        password.add(new PatternValidator(PASSWORD_PATTERN));
        form.add(password.setRequired(false));

        PasswordTextField rePassword = new PasswordTextField("confirmPassword", Model.of((String) null)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setRequired(form.getModelObject() != null && form.getModelObject().getId() == null);
            }
        };

        rePassword.setResetPassword(false);
        rePassword.setLabel(Model.of("Confirm password"));
        form.add(rePassword.setRequired(false));

        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("surname"));
        form.add(new RequiredTextField<String>("email").add(EmailAddressValidator.getInstance()));
        WebMarkupContainer adminOnly = ComponentAuthorization.adminOnlyWebMarkupContainer("adminOnly");
        form.add(adminOnly);
        adminOnly.add(new CheckBox("isAdmin"));

        ListMultipleChoice<UserGroup> userGroups = new ListMultipleChoice<UserGroup>("userGroups",
                new PropertyModel<Collection<UserGroup>>(getDefaultModel(), "userGroups"),
                new AllUserGroups(),
                PropertyChoiceRenderer.USERGROUP_RENDERER);
        ComponentAuthorization.authorizeEnabling(userGroups, Roles.ADMIN);
        form.add(userGroups);

        form.add(new EqualPasswordInputValidator(password, rePassword));

    }

    @Override
    protected void save(User user) {
        if (password.getModelObject() != null) {
            String hashedPassword = DigestUtils.md5DigestAsHex(password.getModelObject().getBytes());
            user.setPassword(hashedPassword);
        }
        userService.saveUpdate(user);
    }
}
