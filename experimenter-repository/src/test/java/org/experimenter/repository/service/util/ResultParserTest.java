package org.experimenter.repository.service.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.util.CsvPrinter;
import org.experimenter.repository.util.ResultParser;
import org.junit.Test;

public class ResultParserTest {

    private static final String resultContent = "" +
            "experimenter-results-head;runtime;sat;vars;clauses\n" +
            "args:[lefv, rnd100-3]\n" +
            "experimenter-results-data;0.306;0;32;11\n";

    @Test
    public void testResultParsing() throws IOException {
        File tmpFile = File.createTempFile(UUID.randomUUID().toString(), ".txt");
        FileUtils.writeStringToFile(tmpFile, resultContent);

        Map<String, BigDecimal> results = ResultParser.parseResultFile(tmpFile);
        for (Entry<String, BigDecimal> entry : results.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        Input input = new Input(1);
        input.setName("rnd100-3");
        SortedMap<Input, Map<String, BigDecimal>> treeMap = new TreeMap<Input, Map<String, BigDecimal>>();
        treeMap.put(input, results);
        CsvPrinter.printResultDataToCsv(new OutputStreamWriter(System.out),
                Arrays.asList(new String[] { "clauses", "runtime", "sat", "vars" }),
                treeMap, ";");
    }

}
