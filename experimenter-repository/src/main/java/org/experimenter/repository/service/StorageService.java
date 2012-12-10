package org.experimenter.repository.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Input;

/**
 * The service managing interaction with the storage, saving and reading input files, executables and result files.
 * 
 * @author jfaryad
 */
public interface StorageService {

    /**
     * Saves the uploaded input file to the storage.
     * 
     * @param inputId
     *            the id of the{@link Input} that is being saved
     * @tmpFile the temporary location of the input data
     * @return the absolute path to the saved file
     */
    public String saveInput(Integer inputId, File tmpFile);

    /**
     * Returns the path of the input data directory in the the storage. If the input has no path to data set yet, a new
     * path will be created from it's id.
     * 
     * @param inputId
     *            the id of the input to get the data path from.
     */
    public String getInputDataDir(Integer inputId);

    /**
     * Saves the uploaded application executable file to the storage.
     * 
     * @param applicationId
     *            the id of the {@link Application} that is being saved
     * @tmpFile the temporary location of the executable
     * @return the absolute path to the saved file
     */
    public String saveApplication(Integer applicationId, File tmpFile);

    /**
     * Returns the path to the storage where to save/read from the application executable.
     * 
     * @param applicationId
     *            the id of the application to get the data path from.
     */
    public String getApplicationDataDir(Integer applicationId);

    /**
     * Returns the path to a local folder where temporary files (such as uploaded files) can be stored, before they are
     * processed further. The folder should have a unique random name to ensure, that nobody else will manipulate it.
     */
    public String getUniqueTemporaryFolderPath();

    /**
     * Creates a file in a unique folder.
     * 
     * @param fileName
     *            the name of the file to be created
     * @return a new file. If it existed, it will be deleted and recreated.
     */
    public File createFileInUniqueTemporaryFolder(String fileName);

    /**
     * Creates a file with a unique name that can be deleted after using it. No paret directories will be created.
     * 
     * @return a new file.
     * @throws IOException
     */
    public File createUniqueTemporaryFile() throws IOException;

    /**
     * Unzips the given archive into the local temporary directory and returns a list of unzipped files.
     * 
     * @return a list of unzipped files
     */
    public List<File> unzipArchiveIntoTempDir(File zipArchive);

    /**
     * Saves the result file of an experiment to the storage.
     * 
     * @param experimentId
     *            id of the experiment
     * @param inputId
     *            id of the input it was run on
     * @param resultFile
     *            the result file
     * @return the absolute path to the saved result file
     */
    public String storeResult(Integer experimentId, Integer inputId, File tmpFile);

    /**
     * Returns a list of all result files belonging to an experiment;
     * 
     * @param experimentId
     *            the id of the experiment to get results for
     * @return a list of files
     */
    public List<File> getResultFilesForExperiment(Integer experimentId);

    /**
     * Deletes recursively the whole resulsts directory for the given experiment.
     * 
     * @param experimentId
     *            the experiment to delete from storage
     */
    public void deleteExperimentDirectory(Integer experimentId);

}
