package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.ApplicationDao;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.User;

/**
 * Default implementation of {@link ApplicationDao}
 * 
 * @author jfaryad
 * 
 */
public class ApplicationDaoImpl extends AbstractBaseDaoImpl<Application> implements ApplicationDao {

    @Override
    public Class<Application> getEntityClass() {
        return Application.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Application> findApplicationsByProgram(Program program) {
        return getSession().getNamedQuery(Application.Q_GET_BY_PROGRAM).setEntity("program", program).list();
    }

    @Override
    public void removeFromAssociations(Application application) {
        application.getProgram().getApplications().remove(application);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Application> findApplicationsByUser(User user) {
        logger.debug(">> findApplicationsByUser: " + user);
        List<Application> list = getSession().getNamedQuery(Application.Q_GET_BY_USER)
                .setEntity("user", user)
                .list();
        logger.debug("<< findApplicationsByUser: number found:" + list.size());
        return list;
    }

}
