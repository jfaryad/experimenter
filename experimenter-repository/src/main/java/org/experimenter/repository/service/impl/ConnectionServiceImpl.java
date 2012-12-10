package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.ConnectionDao;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Default implementation of the related service interface.
 * 
 * @author jfaryad
 */
public class ConnectionServiceImpl extends AbstractService<Connection, ConnectionDao> implements ConnectionService {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionServiceImpl.class);

    @Override
    protected void deleteDependencies(Connection connection) {
    }

    @Override
    public List<Connection> findConnectionsByConnectionFarm(ConnectionFarm connectionFarm) {
        checkIdNotNull(connectionFarm);
        return connectionFarm.getConnections();
    }

    @Override
    public List<Connection> findConnectionsByComputer(Computer computer) {
        checkIdNotNull(computer);
        return computer.getConnections();
    }

    @Override
    public List<Connection> findConnectionsByUser(User user) {
        checkIdNotNull(user);
        return baseDao.findConnectionsByUser(user);
    }

    @Override
    protected boolean hasDependencies(Connection connection) {
        return false;
    }

    @Override
    public Connection addJobToLeastLoadedConnection(List<ConnectionFarm> connectionFarms, Integer maxRunningJobs) {
        synchronized (ConnectionServiceImpl.class) {
            checkNotEmpty(connectionFarms);
            if (maxRunningJobs == null || maxRunningJobs <= 0) {
                maxRunningJobs = Integer.MAX_VALUE;
            }
            LOG.trace(Thread.currentThread().getName() + " searching computer");
            Computer computer = computerService.findLeastLoadedComputer(connectionFarms, maxRunningJobs);
            if (computer == null) {
                return null;
            }
            LOG.trace(Thread.currentThread().getName() + " found computer " + computer);
            List<Connection> connections = baseDao.findByComputerAndConnectionFarms(connectionFarms, computer);
            if (connections != null && !connections.isEmpty()) {
                computer.setNumberOfRunningJobs(computer.getNumberOfRunningJobs() + 1);
                LOG.debug("increasing jobs on computer " + computer.getId() + " to "
                        + computer.getNumberOfRunningJobs());
                computerService.saveUpdate(computer);
                getSession().flush();

                // very important, we don't want the computer to be cached, or else the
                // experiment executor won't see the actual number of running jobs
                getSession().evict(computer);

                return connections.get(0);
            } else {
                return null;
            }
        }
    }

    @Override
    public void removeJobFromConnection(Connection connection) {
        synchronized (ConnectionServiceImpl.class) {
            checkIdNotNull(connection);
            // getSession().clear();
            Computer computer = computerService.findById(connection.getComputer().getId());
            computer.setNumberOfRunningJobs(Math.max(computer.getNumberOfRunningJobs() - 1, 0));
            LOG.debug("decreasing jobs on computer " + computer.getId() + " to " + computer.getNumberOfRunningJobs());
            computerService.saveUpdate(computer);
            getSession().flush();
        }
    }

    @Override
    public boolean testConnection(String hostname, String login, String password, Short port) {
        JSch jsch = null;
        Session session = null;
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");

        try {
            jsch = new JSch();
            session = jsch.getSession(login, hostname, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
        } catch (JSchException e) {
            LOG.error("Connection test failed", e);
            return false;
        } finally {
            session.disconnect();
        }
        return true;
    }

}
