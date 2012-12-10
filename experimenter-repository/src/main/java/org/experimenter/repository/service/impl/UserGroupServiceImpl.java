package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.UserGroupDao;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserGroupService;

/**
 * Default implementation of the related service interface.
 * 
 * @author jfaryad
 */
public class UserGroupServiceImpl extends AbstractService<UserGroup, UserGroupDao> implements UserGroupService {

    @Override
    protected void deleteDependencies(UserGroup userGroup) {
        connectionFarmService.delete(userGroup.getConnectionFarms());
        projectService.delete(userGroup.getProjects());
    }

    @Override
    public List<UserGroup> findUserGroupsByUser(User user) {
        checkIdNotNull(user);
        return user.getUserGroups();
    }

    @Override
    protected boolean hasDependencies(UserGroup userGroup) {
        return !userGroup.getConnectionFarms().isEmpty()
                || !userGroup.getProjects().isEmpty()
                || !userGroup.getUsers().isEmpty();
    }

}
