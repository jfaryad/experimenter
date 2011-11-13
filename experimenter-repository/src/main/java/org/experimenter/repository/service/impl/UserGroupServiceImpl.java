package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserGroupService;

public class UserGroupServiceImpl extends AbstractService<UserGroup> implements UserGroupService {

    @Override
    protected void deleteDependencies(UserGroup userGroup) {
        junctionDao.removeUserFromUserGroup(null, userGroup);
        connectionFarmService.delete(connectionFarmService.findConnectionFarmsByUserGroup(userGroup));
        projectService.delete(projectService.findProjectsByUserGroup(userGroup));
    }

    @Override
    public List<UserGroup> findUserGroupsByUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

}
