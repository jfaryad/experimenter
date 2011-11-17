package org.experimenter.repository.service.impl;

import org.experimenter.repository.dao.ComputerDao;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.service.ComputerService;

public class ComputerServiceImpl extends AbstractService<Computer, ComputerDao> implements ComputerService {

    @Override
    protected void deleteDependencies(Computer computer) {
        connectionService.delete(connectionService.findConnectionsByComputer(computer));
    }

}
