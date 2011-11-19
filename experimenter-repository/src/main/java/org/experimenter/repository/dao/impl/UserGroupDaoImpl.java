package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.UserGroupDao;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.SimpleForm;
import org.sqlproc.engine.SqlSession;

public class UserGroupDaoImpl extends AbstractBaseDaoImpl<UserGroup> implements UserGroupDao {

    @Override
    public Class<UserGroup> getEntityClass() {
        return UserGroup.class;
    }

    @Override
    public String getTableName() {
        return Constants.USERGROUP;
    }

    @Override
    public List<UserGroup> findUserGroupsByUser(User user) {
        logger.debug(">> findUserGroupsByUser: " + user);
        SqlSession session = getSqlSession();
        String engineName = "FIND_USERGROUP_BY_USER";
        List<UserGroup> userGroups = getQueryEngine(engineName).query(session, getEntityClass(),
                new SimpleForm(user.getId()));
        logger.debug("<< findUserGroupsByUser: number of userGroups found:" + userGroups.size());
        return userGroups;
    }
}
