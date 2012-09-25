package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserGroupService;

/**
 * Default entity model for the {@link UserGroup} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class UserGroupModel extends EntityModel<UserGroup> {

    @SpringBean
    private UserGroupService UserGroupService;

    private static final long serialVersionUID = 1L;

    public UserGroupModel(UserGroup UserGroup) {
        super(UserGroup);
    }

    public UserGroupModel(Integer id) {
        super(id);
    }

    @Override
    protected UserGroup loadForId(Integer id) {
        return UserGroupService.findById(id);
    }

}
