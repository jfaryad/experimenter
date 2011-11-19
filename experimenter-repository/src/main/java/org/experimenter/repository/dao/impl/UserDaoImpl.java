package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.Constants;
import org.experimenter.repository.dao.UserDao;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.form.SimpleForm;
import org.sqlproc.engine.SqlSession;

public class UserDaoImpl extends AbstractBaseDaoImpl<User> implements UserDao {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public String getTableName() {
        return Constants.USER;
    }

    @Override
    public List<User> findUsersByUserGroup(UserGroup userGroup) {
        logger.debug(">> findUsersByUserGroup: " + userGroup);
        SqlSession session = getSqlSession();
        String engineName = "FIND_USER_BY_USERGROUP";
        List<User> users = getQueryEngine(engineName).query(session, getEntityClass(),
                new SimpleForm(userGroup.getId()));
        logger.debug("<< findUsersByUserGroup: number of users found:" + users.size());
        return users;
    }

}
