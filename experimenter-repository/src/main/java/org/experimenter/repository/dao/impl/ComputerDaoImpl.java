package org.experimenter.repository.dao.impl;

import org.experimenter.repository.dao.ComputerDao;
import org.experimenter.repository.entity.Computer;

public class ComputerDaoImpl extends AbstractBaseDaoImpl<Computer> implements ComputerDao {

    @Override
    public Class<Computer> getEntityClass() {
        return Computer.class;
    }

    @Override
    protected void removeFromAssociations(Computer item) {
        // do nothing
    }

}
