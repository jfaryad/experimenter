package org.experimenter.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.experimenter.repository.dao.ComputerDao;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.ConnectionFarm;
import org.hibernate.CacheMode;

/**
 * Default implementation of {@link ComputerDao}
 * 
 * @author jfaryad
 * 
 */
public class ComputerDaoImpl extends AbstractBaseDaoImpl<Computer> implements ComputerDao {

    @Override
    public Class<Computer> getEntityClass() {
        return Computer.class;
    }

    @Override
    public Computer findLeastLoadedComputer(List<ConnectionFarm> connectionFarms, int maxRunningJobs) {
        logger.debug(">> findLeastLoadedComputer");
        List<Integer> farmIds = new ArrayList<Integer>();
        for (ConnectionFarm farm : connectionFarms) {
            farmIds.add(farm.getId());
        }
        Computer computer = (Computer) getSession().getNamedQuery(Computer.Q_GET_LEAST_LOADED)
                .setParameterList("farmIds", farmIds)
                .setInteger("maxJobs", maxRunningJobs)
                .setMaxResults(1)
                .setCacheMode(CacheMode.REFRESH)
                .uniqueResult();
        logger.debug("<< findLeastLoadedComputer: found: " + computer);
        return computer;
    }

    @Override
    protected void removeFromAssociations(Computer item) {
        // do nothing
    }

}
