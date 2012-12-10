package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserGroupService;

/**
 * Model of all user groups.
 * 
 * @author jfaryad
 * 
 */
public class AllUserGroups extends LoadableDetachableModel<List<UserGroup>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserGroupService userGroupService;

    public AllUserGroups() {
        Injector.get().inject(this);
    }

    @Override
    protected List<UserGroup> load() {
        return userGroupService.findByExample(new UserGroup());
    }

}
