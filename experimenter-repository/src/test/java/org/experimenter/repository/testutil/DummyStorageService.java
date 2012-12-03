package org.experimenter.repository.testutil;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.experimenter.repository.service.StorageService;

public class DummyStorageService implements StorageService {

    @Override
    public String saveInput(Integer inputId, File tmpFile) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getInputDataDir(Integer inputId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String saveApplication(Integer applicationId, File tmpFile) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getApplicationDataDir(Integer applicationId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUniqueTemporaryFolderPath() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<File> unzipArchiveIntoTempDir(File zipArchive) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String storeResult(Integer experimentId, Integer inputId, File tmpFile) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public File createFileInUniqueTemporaryFolder(String fileName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<File> getResultFilesForExperiment(Integer experimentId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteExperimentDirectory(Integer experimentId) {
        // TODO Auto-generated method stub

    }

    @Override
    public File createUniqueTemporaryFile() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

}
