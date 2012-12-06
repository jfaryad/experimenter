package org.experimenter.repository.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.SortedMap;
import java.util.UUID;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.Result;
import org.junit.Test;

public class ResultServiceTest extends AbstractServiceTest {

    private static final String resultContent = "" +
            "experimenter-results-head;runtime;sat;vars;clauses\n" +
            "args:[lefv, rnd100-3]\n" +
            "experimenter-results-data;0.306;0;32;11\n";

    @Test
    public void testResultSaving() throws IOException {

        Experiment experiment = experimentService.findById(1);
        Input input = inputService.findById(1);
        ;
        Result result = new Result(experiment, input, "runtime", BigDecimal.ZERO);
        resultService.saveUpdate(result);

        System.out.println("resutl id: " + result.getId());

        // resultService.saveUpdate(new Result(experiment, input, "runtime", BigDecimal.ZERO));

        File tmpFile = File.createTempFile(UUID.randomUUID().toString(), ".txt");
        FileUtils.writeStringToFile(tmpFile, resultContent);

        resultService.saveResultsFromFile(1, 1, tmpFile);

        SortedMap<Input, Map<String, BigDecimal>> resultMap = resultService.findResultsForExperiment(new Experiment(1));

        experiment = experimentService.findById(1);
        input = inputService.findById(1);
        Map<String, BigDecimal> results = resultMap.get(input);

        Assert.assertEquals(new BigDecimal(0.306).setScale(3, RoundingMode.HALF_UP),
                results.get("runtime").setScale(3, RoundingMode.HALF_UP));

    }
}
