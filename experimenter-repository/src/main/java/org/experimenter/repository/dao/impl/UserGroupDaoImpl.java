package org.experimenter.repository.dao.impl;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.UserGroupDao;
import org.experimenter.repository.entity.UserGroup;

public class UserGroupDaoImpl extends AbstractBaseDaoImpl<UserGroup> implements UserGroupDao {

    @Override
    public Class<UserGroup> getEntityClass() {
        return UserGroup.class;
    }

    @Override
    public String getTableName() {
        return Constants.USERGROUP;
    }

}
