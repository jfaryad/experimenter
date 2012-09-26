package org.experimenter.repository.scheduler;

import java.io.IOException;
import java.io.InputStream;

import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.service.ExperimentService;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
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
        String executable = experiment.getApplication().getExecutable();
        // TODO select from experiment's connection farms, not user group's farms
        Connection connection = experiment.getApplication().getProgram().getProject().getUserGroup()
                .getConnectionFarms().get(0).getConnections().get(1);
        // System.out.println("Experiment has " + experiment.getConnectionFarms().size()
        // + " connection farms, \nthe first one has "
        // + experiment.getConnectionFarms().get(0).getConnections().size()
        // + " connections and the first one of them is: " + connection);
        String name = experiment.getName();
        String hostname = connection.getComputer().getAddress();
        String login = connection.getLogin();
        String password = connection.getPassword();
        String pathToFile = experiment.getInputSets().get(0).getInputs().get(0).getData();
        runExperiment(hostname, login, password, pathToFile, executable);
        System.out.println("Running experiment " + name + " on " + hostname + " with username " + login
                + " using input data " + pathToFile);
    }

    private void runExperiment(String hostname, String login, String password, String pathToFile,
            String executable) {
        JSch jsch = null;
        Session session = null;
        ChannelSftp sftpChannel = null;
        ChannelExec execChannel = null;
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");

        try {
            jsch = new JSch();
            session = jsch.getSession(login, hostname, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();

            // create work directory
            execChannel = (ChannelExec) session.openChannel("exec");
            execChannel.setCommand("mkdir /tmp/expwork");
            executeExecChannel(execChannel);

            // upload files
            System.out.println("Starting File Upload:");
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            String srcInput = pathToFile;
            String destInput = "/tmp/expwork/input.zip";
            sftpChannel.put(srcInput, destInput);
            String srcExecutable = executable;
            String destExecutable = "/tmp/expwork/executable";
            sftpChannel.put(srcExecutable, destExecutable);
            sftpChannel.disconnect();

            // run experiment
            execChannel = (ChannelExec) session.openChannel("exec");
            execChannel.setCommand("cd /tmp/expwork; sh executable; cd /tmp");
            executeExecChannel(execChannel);

            // download result file
            System.out.println("Result:");
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            String result = "/tmp/expwork/output.txt";
            sftpChannel.get(result, System.out);
            System.out.flush();
            sftpChannel.disconnect();

            // delete work directory
            execChannel = (ChannelExec) session.openChannel("exec");
            execChannel.setCommand("rm -Rf /tmp/expwork");
            executeExecChannel(execChannel);

        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private void executeExecChannel(ChannelExec execChannel) throws JSchException, IOException {
        execChannel.setErrStream(System.err);

        InputStream in = execChannel.getInputStream();

        execChannel.connect();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0)
                    break;
                System.out.print(new String(tmp, 0, i));
            }
            if (execChannel.isClosed()) {
                System.out.println("exit-status: " + execChannel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
            }
        }

        execChannel.disconnect();
    }

}
