package org.experimenter.repository.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.experimenter.repository.service.StorageService;
import org.experimenter.repository.util.ZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileSystemUtils;

/**
 * Implementation of {@link StorageService} storing files in the file system
 * 
 * @author jfaryad
 * 
 */
public class FileStorageService implements StorageService {

    private static final Logger LOG = LoggerFactory.getLogger(FileStorageService.class);

    @Value("${storage.applications.folder}")
    private String applicationFolder;

    @Value("${storage.inputFiles.folder}")
    private String inputDataFolder;

    @Value("${storage.results.folder}")
    private String resultFolder;

    @Value("${localTmpDir}")
    private String localTmpDir;

    @Override
    public String saveInput(Integer inputId, File tmpFile) {
        return moveTmpFileToStorage(getInputDataDir(inputId), tmpFile, true, true);
    }

    @Override
    public String getInputDataDir(Integer inputId) {
        if (inputId == null) {
            throw new IllegalArgumentException("inputId must not be null");
        }
        return appendFileSeparator(inputDataFolder) + inputId;
    }

    @Override
    public String saveApplication(Integer applicationId, File tmpFile) {
        return moveTmpFileToStorage(getApplicationDataDir(applicationId), tmpFile, true, true);
    }

    @Override
    public String getApplicationDataDir(Integer applicationId) {
        if (applicationId == null) {
            throw new IllegalArgumentException("applicationId must not be null");
        }
        return appendFileSeparator(applicationFolder) + applicationId;
    }

    @Override
    public String getUniqueTemporaryFolderPath() {
        return appendFileSeparator(localTmpDir) + UUID.randomUUID();
    }

    private String moveTmpFileToStorage(String targetDataDir, File tmpFile, boolean deleteTargetDir,
            boolean deleteTmpParentDir) {
        File newFile = new File(appendFileSeparator(targetDataDir) + tmpFile.getName());
        try {
            if (deleteTargetDir) {
                deleteParentDir(newFile);
            } else if (newFile.exists()) {
                newFile.delete();
            }
            newFile.getParentFile().mkdirs();
            File tmpParentDir = tmpFile.getParentFile();

            FileUtils.moveFile(tmpFile, newFile);

            if (deleteTmpParentDir && tmpParentDir.exists()) {
                FileSystemUtils.deleteRecursively(tmpParentDir);
            }
        } catch (IOException e) {
            LOG.error("Error copying data from " + tmpFile.getAbsolutePath(), e);
            throw new IllegalStateException("Error moving input data from temporary upload location");
        }
        LOG.debug(tmpFile.getName() + " saved to storage: " + newFile.getAbsolutePath());
        return newFile.getAbsolutePath();
    }

    /**
     * Check whether the parent file allready exists, and if so, try to delete it
     * 
     * @param newFile
     *            the file to check
     */
    private void deleteParentDir(File newFile) {
        if (newFile.getParentFile().exists()) {
            if (!FileSystemUtils.deleteRecursively(newFile.getParentFile())) {
                throw new IllegalStateException("Unable to delete " + newFile.getParentFile().getAbsolutePath());
            }
        }
    }

    /**
     * Appends the file separator to the file path, if it doesn't already end with it.
     * 
     * @param path
     * @return
     */
    public static String appendFileSeparator(String path) {
        if (path != null && !path.endsWith(File.separator)) {
            return path + File.separator;
        }
        return path;
    }

    @Override
    public List<File> unzipArchiveIntoTempDir(File zipArchive) {
        return ZipUtil.unzip(zipArchive, new File(getUniqueTemporaryFolderPath()));
    }

    @Override
    public String storeResult(Integer experimentId, Integer inputId, File tmpFile) {
        return moveTmpFileToStorage(getResultDir(experimentId, inputId), tmpFile, false, true);
    }

    @Override
    public File createFileInUniqueTemporaryFolder(String fileName) {
        File newFile = new File(getUniqueTemporaryFolderPath(), fileName);
        if (newFile.exists()) {
            newFile.delete();
        }
        newFile.getParentFile().mkdirs();
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            LOG.error("Unable to create file: " + newFile.getAbsolutePath());
            throw new RuntimeException("Unable to create file: " + newFile.getAbsolutePath(), e);
        }
        return newFile;
    }

    private String getResultDir(Integer experimentId, Integer inputId) {
        if (inputId == null || experimentId == null) {
            throw new IllegalArgumentException("experimentId and inputId must not be null");
        }
        return appendFileSeparator(resultFolder) + experimentId + File.separator + inputId;
    }

    @Override
    public List<File> getResultFilesForExperiment(Integer experimentId) {
        List<File> files = new ArrayList<File>();
        generateFileList(new File(appendFileSeparator(resultFolder) + experimentId), files);
        return files;
    }

    @Override
    public void deleteExperimentDirectory(Integer experimentId) {
        // TODO Auto-generated method stub

    }

    /**
     * Recursively finds all files in a directory
     * 
     * @param node
     *            the root directory to start searching
     */
    private void generateFileList(File node, List<File> files) {
        if (node.isFile()) {
            files.add(node);
        } else if (node.isDirectory()) {
            String[] subNodes = node.list();
            for (String filename : subNodes) {
                generateFileList(new File(node, filename), files);
            }
        }
    }

    @Override
    public File createUniqueTemporaryFile() throws IOException {
        File file = new File(getUniqueTemporaryFolderPath());
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }

}
