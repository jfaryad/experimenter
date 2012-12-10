package org.experimenter.repository.scheduler;

import static org.experimenter.repository.service.impl.FileStorageService.appendFileSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.IOUtils;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.repository.service.ResultService;
import org.experimenter.repository.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * A thread that executes an experiment on a specific machine using specific input data.
 * 
 * @author jfaryad
 * 
 */
public class ExperimentJob extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(ExperimentJob.class);

    @Value("${remoteTmpDir}")
    private String remoteTemporaryDir;

    @Value("${localTmpDir}")
    private String localTemporaryDir;

    @Value("${experiment.connection.retry.number}")
    private int experimentConnectionRetryNumber;

    @Value("${experiment.connection.retry.interval.seconds}")
    private int experimentConnectionRetryInterval;

    @Autowired
    private StorageService storageService;
    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private ResultService resultService;

    private final Integer experimentId;
    private final Integer computerId;
    private final Integer inputId;
    private final Integer connectionId;
    private final String hostname;
    private final Short port;
    private final String login;
    private final String password;
    private final String pathToFile;
    private final String executable;
    private final String command;
    private final String uuid = UUID.randomUUID().toString();
    private CountDownLatch doneSignal;
    private File resultFile;

    /**
     * Constructor. Adds all he required parameters.
     * 
     * @param experimentId
     * @param computerId
     * @param inputId
     * @param connectionId
     * @param hostname
     * @param port
     * @param login
     * @param password
     * @param pathToFile
     * @param command
     * @param executable
     */
    public ExperimentJob(Integer experimentId, Integer computerId, Integer inputId, Integer connectionId,
            String hostname, Short port,
            String login, String password, String pathToFile, String command, String executable) {
        this.experimentId = experimentId;
        this.computerId = computerId;
        this.inputId = inputId;
        this.connectionId = connectionId;
        this.hostname = hostname;
        this.port = port;
        this.login = login;
        this.password = password;
        this.pathToFile = pathToFile;
        this.executable = executable;
        this.command = command;
        setName(getName() + "-" + experimentId + "-" + computerId + "-" + inputId);
    }

    @Override
    public void run() {
        while (experimentConnectionRetryNumber > 0) {

            if (executeJob() && resultFile != null && resultFile.exists()) {
                try {
                    resultService.saveResultsFromFile(experimentId, inputId, resultFile);
                } catch (Exception e) {
                    LOG.error("Failed saving job for experiment: " + experimentId + " and input: " + inputId, e);
                }
                break;
            } else {
                experimentConnectionRetryNumber--;
                try {
                    Thread.sleep(1000 * experimentConnectionRetryInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        connectionService.removeJobFromConnection(connectionService.findById(connectionId));
        doneSignal.countDown();
        LOG.debug("Exiting job for experiment: " + experimentId + " and input: " + inputId + ", remaining: "
                + doneSignal.getCount());
    }

    public boolean executeJob() {
        LOG.info("Running experiment " + experimentId + " on " + hostname + ":" + port + " with username " + login
                + " using input data " + pathToFile);
        JSch jsch = null;
        Session session = null;
        ChannelSftp sftpChannel = null;
        ChannelExec execChannel = null;
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");

        try {
            jsch = new JSch();
            session = jsch.getSession(login, hostname, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();

            // create work directory
            execChannel = (ChannelExec) session.openChannel("exec");
            execChannel.setCommand("rm -Rf " + getDestDir() + "; mkdir -p " + getDestDir());
            executeExecChannel(execChannel);

            // upload files
            LOG.debug("Starting File Upload:");
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            // input data
            String srcInput = pathToFile;
            String destInput = getDestFileName(getFileNameFromAbsolutePath(pathToFile));
            sftpChannel.put(srcInput, destInput);
            // executable program
            String srcExecutable = executable;
            String destExecutable = getDestFileName(getFileNameFromAbsolutePath(executable));
            sftpChannel.put(srcExecutable, destExecutable);
            // launch script
            String destStartScript = getStartScriptName();
            sftpChannel.put(IOUtils.toInputStream(prepareLaunchScript()), getDestFileName(destStartScript));
            sftpChannel.disconnect();

            // run experiment
            execChannel = (ChannelExec) session.openChannel("exec");
            execChannel.setCommand(getActualExecutionCommand());
            executeExecChannel(execChannel);

            // download result file
            LOG.debug("Saving result into " + getResultPath());
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            String srcResult = getDestDir() + "/" + getDestOutputFileName();

            resultFile = new File(getResultPath(), getDestOutputFileName());
            // Check new file, delete if it already existed
            checkFileExists(resultFile);
            resultFile.getParentFile().mkdirs();
            resultFile.createNewFile();
            OutputStream outputStream = new FileOutputStream(resultFile);
            sftpChannel.get(srcResult, outputStream);
            outputStream.flush();
            sftpChannel.disconnect();

            // delete work directory
            execChannel = (ChannelExec) session.openChannel("exec");
            execChannel.setCommand("rm -Rf " + getDestDir());
            executeExecChannel(execChannel);

        } catch (Exception e) {
            LOG.error(
                    "Experiment " + experimentId + " for input " + inputId + "on the machine " + hostname
                            + ", execution failed: ",
                    e);
            return false;
        } finally {
            session.disconnect();
        }
        return true;
    }

    public void setDoneSignal(CountDownLatch doneSignal) {
        this.doneSignal = doneSignal;
    }

    private void executeExecChannel(ChannelExec execChannel) throws JSchException, IOException {
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

    private String getDestDir() {
        return appendFileSeparator(remoteTemporaryDir) + "expwork-" + experimentId + "-" + uuid;
    }

    private String getDestOutputFileName() {
        return getFileNameFromAbsolutePath(pathToFile) + "-result.txt";
    }

    private String getStartScriptName() {
        return "startScript.sh";
    }

    private String prepareLaunchScript() {
        String script = "PROGRAM='" + getFileNameFromAbsolutePath(executable) + "'; "
                + "DATA='" + getFileNameFromAbsolutePath(pathToFile) + "'; "
                + command.replaceAll("\\r\\n", "; ") // newlines by semicolon
                        .replaceAll(";+[;\\s]*;+", ";"); // reduce multiple semicolons

        LOG.debug("Launch script: " + script);
        return script;
    }

    private String getActualExecutionCommand() {
        String execCommand = "cd " + getDestDir() + "; sh " + getStartScriptName() + " &> "
                + getDestOutputFileName() + "; cd ..";
        LOG.debug("Execution command: " + execCommand);
        return execCommand;
    }

    private String getDestFileName(String fileName) {
        return getDestDir() + "/" + fileName;
    }

    private String getResultPath() {
        return appendFileSeparator(localTemporaryDir) + experimentId + "-" + uuid;
    }

    private String getFileNameFromAbsolutePath(String path) {
        String[] pathParts = path.split("/");
        return pathParts[pathParts.length - 1];
    }

    private void checkFileExists(File newFile) {
        if (newFile.exists()) {
            if (!newFile.delete()) {
                throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
            }
        }
    }

}
