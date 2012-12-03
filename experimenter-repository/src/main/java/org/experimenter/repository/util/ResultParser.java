package org.experimenter.repository.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility class that takes care of the parsing of a result file of an experiment.
 * <p>
 * The file must be a plain text file and must contain two special lines (anywhere in the file and in any order): <br>
 * line starting with "experimenter-results-head" of semicolon separated names of the parameters that have been
 * measured.<br>
 * and<br>
 * a line starting with "experimenter-results-data" of semicolon separated decimal values that have been measured, in
 * the same order as were parameter names in the header line.<br>
 * <p>
 * For example:
 * 
 * <pre>
 * ...
 * decisions 842
 * experimenter-results-head;runtime;sat;vars;clauses
 * args:[jw, uf250-02.cnf]
 * experimenter-results-data;0.156;1;32;11
 * ...
 * </pre>
 * 
 * That means, that the parameter runtime has a value of 0.156, the paramter sat has the value 1 etc..
 * 
 * @author jfaryad
 * 
 */
public class ResultParser {

    private static final Logger LOG = LoggerFactory.getLogger(ResultParser.class);

    public static Map<String, BigDecimal> parseResultFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            String[] header = null;
            String[] values = null;
            while ((line = reader.readLine()) != null) {
                if (isHeaderLine(line)) {
                    header = line.split(";");
                } else if (isResultDataLine(line)) {
                    values = line.split(";");
                }
                if (header != null && values != null) { // both special lines found, stop parsing lines
                    break;
                }
            }
            if (header == null || values == null) {
                LOG.error("Result file " + file.getAbsolutePath()
                        + " doesn't contain the results in the expected format.");
                throw new RuntimeException("Result file " + file.getAbsolutePath()
                        + " doesn't contain the results in the expected format.");
            }
            return parseResultValues(header, values);

        } catch (IOException e) {
            LOG.error("IO error when reading the result file " + file.getAbsolutePath(), e);
            return Collections.emptyMap();
        } finally {
            closeQuietly(reader);
        }

    }

    private static void closeQuietly(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ignored) {
            }
        }
    }

    private static Map<String, BigDecimal> parseResultValues(String[] header, String[] values) {
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        for (int i = 1; i < Math.min(header.length, values.length); i++) {
            String paramName = header[i];
            String value = values[i];
            if (paramName.isEmpty() || value.isEmpty()) {
                continue;
            }
            try {
                BigDecimal numericValue = new BigDecimal(value);
                map.put(paramName, numericValue);
            } catch (NumberFormatException ex) {
                LOG.error("Unable to parse the value for parameter: " + paramName + ", skipping...");
            }
        }
        return map;
    }

    private static boolean isHeaderLine(String line) {
        return line.startsWith("experimenter-results-head");
    }

    private static boolean isResultDataLine(String line) {
        return line.startsWith("experimenter-results-data");
    }

}
