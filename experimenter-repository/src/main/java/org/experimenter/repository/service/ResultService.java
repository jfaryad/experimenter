package org.experimenter.repository.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.experimenter.repository.dto.ChartSettings;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.Result;

/**
 * Service for managing (saving, aggregating, parsing, comparing) results of experiments
 * 
 * @author jfaryad
 * 
 */
public interface ResultService extends EntityService<Result> {

    /**
     * Parses the result file, saves individual result values and takes care of calling the storage service to store the
     * result file itself
     * 
     * @param experimentId
     *            id of the experiment
     * @param inputId
     *            id of the input it was run on
     * @param resultFile
     *            the temporary result file
     */
    public void saveResultsFromFile(Integer experimentId, Integer inputId, File tmpResultFile);

    /**
     * Finds all the results for all the parameters for all the inputs of one experiment.
     * 
     * @param experiment
     *            the experiment
     * @return a map of maps, every input in the experiment is represented by one map and every measured parameter of
     *         that experiment is represented by a key in the map. The value of that parameter is then the value in the
     *         map as well.
     */
    public SortedMap<Input, Map<String, BigDecimal>> findResultsForExperiment(Experiment experiment);

    /**
     * Creates a csv file with the results for all inputs of an experiment. The file will be in a temporary location and
     * can be safely deleted after it has been downloaded.
     * 
     * @param experiment
     *            the experiment
     * @return a csv file
     */
    public File downloadResultsAsCsv(Experiment experiment);

    /**
     * Finds all the output files from all inputs from the given experiment in the storage and returns them in a zip
     * file.
     * 
     * @param experiment
     *            the experiment
     * @return a zip archive
     */
    public File downloadRawResultsAsZip(Experiment experiment);

    /**
     * Fetches the data for the given experiments, generates the chart and returns it as a buffered image.
     * 
     * @param settings
     *            chart configuration
     * @return a {@link BufferedImage}
     */
    public BufferedImage getResultChartAsBufferedImage(ChartSettings settings);

    /**
     * Returns the list of all available distinct parameters from all results of the given experiments.
     * 
     * @param experimentIds
     *            the experiments to look at results for
     * 
     * @return a non-null list of distinct string values
     */
    public List<String> findAllExperimentParameters(Collection<Integer> experimentIds);

    /**
     * Fetches the data for the given experiments, generates the chart and returns it as a pdf document.
     * 
     * @param settings
     *            chart configuration
     * @return a new pdf containing the chart.
     */
    public File downloadChartAsPdf(ChartSettings settings);

    /**
     * Fetches the data for the given experiments, generates the chart and returns it as a svg image
     * 
     * @param settings
     *            chart configuration
     * @return a new svg file containing the chart.
     */
    public File downloadChartAsSvg(ChartSettings settings);

}
