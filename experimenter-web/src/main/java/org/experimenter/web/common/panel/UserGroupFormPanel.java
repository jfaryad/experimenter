package org.experimenter.web.common.panel;

import java.util.Collection;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserGroupService;
import org.experimenter.web.component.ReverseUserListMultipleChoice;
import org.experimenter.web.model.UserGroupModel;
import org.experimenter.web.model.aggregate.AvailableUsers;
import org.experimenter.web.renderer.PropertyChoiceRenderer;
import org.experimenter.web.validator.NiceStringValidator;

/**
 * Simple panel with a form to edit the {@link UserGroup} entity.
 * 
 * @author jfaryad
 * 
 */
public class UserGroupFormPanel extends EntityFormPanel<UserGroup> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserGroupService userGroupService;

    public UserGroupFormPanel(String id, UserGroupModel userGroupModel) {
        super(id, userGroupModel);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addFieldsToForm(Form<UserGroup> form) {
        form.add(new RequiredTextField<String>("name").setLabel(Model.of("Name")).add(new NiceStringValidator(30)));
        form.add(new ReverseUserListMultipleChoice("users", new PropertyModel<Collection<User>>(
                getDefaultModel(), "users"), new AvailableUsers(), PropertyChoiceRenderer.USER_RENDERER,
                (IModel<UserGroup>) getDefaultModel()).setMaxRows(10));

    }

    @Override
    protected void save(UserGroup userGroup) {
        userGroupService.saveUpdate(userGroup);
    }

}
