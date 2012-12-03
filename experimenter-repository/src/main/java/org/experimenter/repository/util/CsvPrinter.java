package org.experimenter.repository.util;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import org.experimenter.repository.entity.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility class that takes care of transforming experiment results into csv data.
 * 
 * @author jfaryad
 * 
 */
public class CsvPrinter {

    protected final static Logger LOG = LoggerFactory.getLogger(CsvPrinter.class);
    private static final String NEWLINE = "\n";

    public static void printResultDataToCsv(Writer writer, List<String> sortedParams,
            SortedMap<Input, Map<String, BigDecimal>> resultsByInput,
            String separator) {
        try {

            writer.append("Input data");
            for (String param : sortedParams) {
                writer.append(separator);
                writer.append(param);
            }
            writer.append(NEWLINE);

            for (Entry<Input, Map<String, BigDecimal>> entry : resultsByInput.entrySet()) {
                writer.append(entry.getKey().getName());
                for (String param : sortedParams) {
                    writer.append(separator);
                    writer.append(formatValue(entry.getValue().get(param)));
                }
                writer.append(NEWLINE);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            LOG.error("Error writing csv result data into a file", e);
            throw new RuntimeException("Error writing csv result data into a file", e);
        }
    }

    private static String formatValue(BigDecimal value) {
        if (value == null) {
            return "";
        }
        value = value.setScale(3, RoundingMode.HALF_UP);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        try {
            return df.format(value);
        } catch (NumberFormatException ex) {
            LOG.error("error formatting the value", ex);
            return "";
        }
    }
}
