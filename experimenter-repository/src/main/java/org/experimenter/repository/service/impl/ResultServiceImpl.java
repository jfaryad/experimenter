package org.experimenter.repository.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.experimenter.repository.dao.ResultDao;
import org.experimenter.repository.dto.ChartSettings;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.Result;
import org.experimenter.repository.service.ResultService;
import org.experimenter.repository.util.ChartUtil;
import org.experimenter.repository.util.CsvPrinter;
import org.experimenter.repository.util.ResultParser;
import org.experimenter.repository.util.ZipUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * Implementation of {@link ResultService}
 * 
 * @author jfaryad
 * 
 */
public class ResultServiceImpl extends AbstractService<Result, ResultDao> implements ResultService {

    @Value("${csv.separator}")
    private String csvSeparator;

    @Override
    public void saveResultsFromFile(Integer experimentId, Integer inputId, File tmpResultFile) {
        File storedResultFile = new File(storageService.storeResult(experimentId, inputId, tmpResultFile));
        Map<String, BigDecimal> resultMap = ResultParser.parseResultFile(storedResultFile);
        Experiment experiment = experimentService.findById(experimentId);
        Input input = inputService.findById(inputId);
        if (experiment == null || input == null) {
            storedResultFile.delete();
            throw new IllegalArgumentException("Unable to save result for non existing experiment or input");
        }
        for (Entry<String, BigDecimal> entry : resultMap.entrySet()) {
            Result existingResult = baseDao.findResultByExperimentInputParam(experimentId, inputId, entry.getKey());
            if (existingResult == null) {
                saveUpdate(new Result(experiment, input, entry.getKey(), entry.getValue()));
            } else {
                existingResult.setValue(entry.getValue());
                saveUpdate(existingResult);
            }
        }
    }

    @Override
    public SortedMap<Input, Map<String, BigDecimal>> findResultsForExperiment(Experiment experiment) {
        checkIdNotNull(experiment);
        List<Result> resultList = baseDao.findResultsForExperiment(experiment);
        SortedMap<Input, Map<String, BigDecimal>> resultsByInput =
                new TreeMap<Input, Map<String, BigDecimal>>(Input.COMPARATOR_BY_NAME);
        for (Result result : resultList) {
            Map<String, BigDecimal> inputResultMap = resultsByInput.get(result.getInput());
            if (inputResultMap == null) {
                inputResultMap = new HashMap<String, BigDecimal>();
                resultsByInput.put(result.getInput(), inputResultMap);
            }
            inputResultMap.put(result.getParam(), result.getValue());
        }
        return resultsByInput;
    }

    @Override
    public File downloadResultsAsCsv(Experiment experiment) {
        checkIdNotNull(experiment);
        List<Result> resultList = baseDao.findResultsForExperiment(experiment);
        Set<String> sortedParams = new TreeSet<String>();
        SortedMap<Input, Map<String, BigDecimal>> resultsByInput =
                new TreeMap<Input, Map<String, BigDecimal>>(Input.COMPARATOR_BY_NAME);
        for (Result result : resultList) {
            sortedParams.add(result.getParam());
            Map<String, BigDecimal> inputResultMap = resultsByInput.get(result.getInput());
            if (inputResultMap == null) {
                inputResultMap = new HashMap<String, BigDecimal>();
                resultsByInput.put(result.getInput(), inputResultMap);
            }
            inputResultMap.put(result.getParam(), result.getValue());
        }
        File csvFile;
        try {
            csvFile = storageService.createFileInUniqueTemporaryFolder("results.csv");
            CsvPrinter.printResultDataToCsv(new FileWriter(csvFile), new ArrayList<String>(sortedParams),
                    resultsByInput, csvSeparator);
        } catch (Exception e) {
            throw new RuntimeException("Error writing to the csv file.", e);
        }
        return csvFile;
    }

    @Override
    public File downloadChartAsPdf(ChartSettings settings) {
        Map<String, List<BigDecimal>> valuesByExperimentName = prepareValuesForChart(settings);
        File pdfFile;
        try {
            pdfFile = storageService.createUniqueTemporaryFile();
            ChartUtil.writeChartToPdf(settings, valuesByExperimentName, pdfFile);
        } catch (Exception e) {
            throw new RuntimeException("Error writing to the pdf file.", e);
        }
        return pdfFile;
    }

    @Override
    public File downloadRawResultsAsZip(Experiment experiment) {
        checkIdNotNull(experiment);
        List<File> resultList = storageService.getResultFilesForExperiment(experiment.getId());
        if (resultList.isEmpty()) {
            return null;
        }
        File zipFile;
        try {
            zipFile = storageService.createFileInUniqueTemporaryFolder("results.zip");
            ZipUtil.zipFileListFlat(resultList, zipFile);
        } catch (Exception e) {
            throw new RuntimeException("Error writing to the zip file.", e);
        }
        return zipFile;
    }

    @Override
    public BufferedImage getResultChartAsBufferedImage(ChartSettings settings) {
        Map<String, List<BigDecimal>> valuesByExperimentName = prepareValuesForChart(settings);
        return ChartUtil.getChartAsBufferedImage(settings, valuesByExperimentName);
    }

    private Map<String, List<BigDecimal>> prepareValuesForChart(ChartSettings settings) {
        List<Result> results = baseDao.findResultsForParam(settings.getParam(), settings.getExperimentIds());
        Map<Experiment, List<BigDecimal>> valuesByExperiment = new HashMap<Experiment, List<BigDecimal>>();
        for (Result result : results) {
            List<BigDecimal> values = valuesByExperiment.get(result.getExperiment());
            if (values == null) {
                values = new ArrayList<BigDecimal>();
                valuesByExperiment.put(result.getExperiment(), values);
            }
            values.add(result.getValue());
        }
        Map<String, List<BigDecimal>> valuesByExperimentName = new HashMap<String, List<BigDecimal>>();
        for (Entry<Experiment, List<BigDecimal>> byExperiment : valuesByExperiment.entrySet()) {
            valuesByExperimentName.put(byExperiment.getKey().getName(), byExperiment.getValue());
        }
        return valuesByExperimentName;
    }

    @Override
    public List<String> findAllExperimentParameters(Collection<Integer> experimentIds) {
        if (experimentIds == null || experimentIds.isEmpty()) {
            return Collections.emptyList();
        }
        return baseDao.findAllExperimentParameters(experimentIds);
    }

    @Override
    protected void deleteDependencies(Result entity) {
        // do nothing
    }

    @Override
    protected boolean hasDependencies(Result entity) {
        return false;
    }

}
