package org.experimenter.web.component;

import java.util.Collection;
import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserService;

/**
 * A {@link ListMultipleChoice} that displays users per usergroup. These users are taken from the users property of the
 * {@link UserGroup} entity. <br>
 * However, since {@link User} is the owning entity of the hibernate mapping, changing items in the {@link UserGroup}
 * users list doesn't do anything. That's why the old users have to be removed and the new ones have to be added
 * explicitly by calling the {@link UserService}
 * 
 * @author jfaryad
 * 
 */
public class ReverseUserListMultipleChoice extends
        ReverseEntityMappingListMultipleChoice<User, UserGroup> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserService userService;

    /**
     * Constructor.
     * 
     * @param id
     *            wicket id
     * @param model
     *            the model of the users that belong to the given user group
     * @param choices
     *            all available users
     * @param renderer
     *            the renderer to use
     * @param userGroup
     *            the edited user group
     */
    public ReverseUserListMultipleChoice(String id, IModel<? extends Collection<User>> model,
            IModel<? extends List<? extends User>> choices, IChoiceRenderer<? super User> renderer,
            IModel<UserGroup> userGroup) {
        super(id, model, choices, renderer, userGroup);
    }

    @Override
    protected void removeFromList(User user, UserGroup userGroup) {
        userService.removeUserFromUserGroup(user, userGroup);
    }

    @Override
    protected void addToList(User user, UserGroup userGroup) {
        userService.addUserToUserGroup(user, userGroup);
    }
}
