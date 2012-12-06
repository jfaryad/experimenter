package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ComputerDao;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.service.ComputerService;

public class ComputerServiceImpl extends AbstractService<Computer, ComputerDao> implements ComputerService {

    @Override
    protected void deleteDependencies(Computer computer) {
        connectionService.delete(computer.getConnections());
    }

    @Override
    protected boolean hasDependencies(Computer computer) {
        return !computer.getConnections().isEmpty();
    }

    @Override
    public Computer findLeastLoadedComputer(List<ConnectionFarm> connectionFarms, int maxRunningJobs) {
        checkNotEmpty(connectionFarms);
        return baseDao.findLeastLoadedComputer(connectionFarms, maxRunningJobs);
    }

}
