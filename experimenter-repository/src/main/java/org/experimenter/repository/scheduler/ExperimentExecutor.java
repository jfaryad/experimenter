package org.experimenter.repository.scheduler;

import java.io.InputStream;

import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.service.ExperimentService;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ExperimentExecutor {

    private Integer experimentId;
    private ExperimentService experimentService;

    public ExperimentExecutor() {

    }

    public ExperimentExecutor(Integer experimentId, ExperimentService experimentService) {
        this.experimentId = experimentId;
        this.experimentService = experimentService;
    }

    public void execute() {
        Experiment experiment = experimentService.findById(experimentId);
        String command = experiment.getApplication().getExecutable();
        Connection connection = experiment.getConnectionFarms().get(0).getConnections().get(0);
        System.out.println("Experiment has " + experiment.getConnectionFarms().size()
                + " connection farms, \nthe first one has "
                + experiment.getConnectionFarms().get(0).getConnections().size()
                + " connections and the first one of them is: " + connection);
        String name = experiment.getName();
        String hostname = connection.getComputer().getAddress();
        String login = connection.getLogin();
        String password = connection.getPassword();
        String pathToFile = experiment.getInputSets().get(0).getInputs().get(0).getData();
        copyExperimentFileToTargetHost(hostname, login, password, pathToFile);
        executeExperiment(hostname, login, password, command);
        System.out.println("Running experiment " + name + " on " + hostname + " with credentials " + login + "/"
                + password + " using input data " + pathToFile);
    }

    private void copyExperimentFileToTargetHost(String hostname, String login, String password, String pathToFile) {
        JSch jsch = null;
        Session session = null;
        Channel channel = null;
        ChannelSftp c = null;
        String khfile = "/home/jakub/.ssh/known_hosts";
        try {
            jsch = new JSch();
            session = jsch.getSession(login, hostname, 22);
            session.setPassword(password);
            jsch.setKnownHosts(khfile);
            session.connect();

            channel = session.openChannel("sftp");
            channel.connect();
            c = (ChannelSftp) channel;

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Starting File Upload:");
            String fsrc = pathToFile, fdest = "/tmp/output.txt";
            c.put(fsrc, fdest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        c.disconnect();
        session.disconnect();
    }

    private void executeExperiment(String hostname, String login, String password, String command) {
        JSch jsch = null;
        Session session = null;
        Channel channel = null;
        ChannelExec c = null;
        String khfile = "/home/jakub/.ssh/known_hosts";
        try {
            jsch = new JSch();
            session = jsch.getSession(login, hostname, 22);
            session.setPassword(password);
            jsch.setKnownHosts(khfile);
            session.connect();

            channel = session.openChannel("exec");
            c = (ChannelExec) channel;
            c.setCommand(command);

            c.setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        c.disconnect();
        session.disconnect();
    }

    public Integer getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Integer experimentId) {
        this.experimentId = experimentId;
    }

    public ExperimentService getExperimentService() {
        return experimentService;
    }

    public void setExperimentService(ExperimentService experimentService) {
        this.experimentService = experimentService;
    }

}
