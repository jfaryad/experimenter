package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserGroupService;
import org.experimenter.web.ExperimenterSession;

/**
 * Model of a list of user groups which the current user belongs to.
 * 
 * @author jfaryad
 * 
 */
public class AvailableUserGroups extends LoadableDetachableModel<List<UserGroup>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserGroupService userGroupService;

    public AvailableUserGroups() {
        Injector.get().inject(this);
    }

    @Override
    protected List<UserGroup> load() {
        User user = ExperimenterSession.get().getCurrentUser();
        return userGroupService.findUserGroupsByUser(user);
    }

}
