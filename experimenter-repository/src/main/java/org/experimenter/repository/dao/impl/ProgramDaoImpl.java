package org.experimenter.repository.dao.impl;

import java.util.List;

import org.experimenter.repository.dao.ProgramDao;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.User;

/**
 * Default implementation of {@link ProgramDao}
 * 
 * @author jfaryad
 * 
 */
public class ProgramDaoImpl extends AbstractBaseDaoImpl<Program> implements ProgramDao {

    @Override
    public Class<Program> getEntityClass() {
        return Program.class;
    }

    @Override
    public void removeFromAssociations(Program program) {
        program.getProject().getPrograms().remove(program);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Program> findProgramsByUser(User user) {
        logger.debug(">> findProgramsByUser: " + user);
        List<Program> list = getSession().getNamedQuery(Program.Q_GET_BY_USER)
                .setEntity("user", user)
                .list();
        logger.debug("<< findProgramsByUser: number found:" + list.size());
        return list;
    }

}
