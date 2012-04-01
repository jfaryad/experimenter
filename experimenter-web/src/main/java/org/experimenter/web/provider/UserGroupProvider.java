package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserGroupService;
import org.experimenter.web.model.UserGroupModel;

/**
 * Default provider of the {@link UserGroup} entity.
 * 
 * @author jakub
 * 
 */
public class UserGroupProvider extends EntityDataProvider<UserGroup> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserGroupService userGroupService;

    @Override
    public IModel<UserGroup> model(UserGroup userGroup) {
        return new UserGroupModel(userGroup);
    }

    @Override
    protected List<UserGroup> load() {
        // loads all userGroups
        return userGroupService.findByExample(new UserGroup());
    }

}
