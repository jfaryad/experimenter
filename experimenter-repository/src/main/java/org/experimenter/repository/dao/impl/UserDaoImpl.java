package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.UserDao;
import org.experimenter.repository.form.SimpleForm;
import org.experimenter.repository.model.User;
import org.sqlproc.engine.SqlSession;

public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

    @Override
    public User findById(Integer id) {
        SqlSession session = getSqlSession();
        User user = getCrudEngine("GET_USER_BY_ID").get(session, User.class, new SimpleForm(id));
        logger.info("<< UserDaoImpl.findById: " + user);
        return user;
    }

    @Override
    public void insert(User user) {
        SqlSession session = getSqlSession();
        logger.info(">> UserDaoImpl.insert: " + user);
        getCrudEngine("INSERT_USER").insert(session, user);
        logger.info("<< UserDaoImpl.insert: " + user);
    }

}
