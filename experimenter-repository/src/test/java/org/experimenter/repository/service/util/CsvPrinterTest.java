package org.experimenter.repository.service.util;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import junit.framework.Assert;

import org.experimenter.repository.entity.Input;
import org.experimenter.repository.util.CsvPrinter;
import org.junit.Test;

/**
 * Tests structure and formatting of the {@link CsvPrinter} utility
 * 
 * @author jfaryad
 * 
 */
public class CsvPrinterTest {

    private static final List<String> sortedParams = Arrays.asList(new String[] { "param1", "param2", "param3" });
    private static final SortedMap<Input, Map<String, BigDecimal>> resultsByInput = new TreeMap<Input, Map<String, BigDecimal>>(
            Input.COMPARATOR_BY_NAME);
    private static final String SEPARATOR = ";";
    private static final Input input1 = new Input();
    private static final Map<String, BigDecimal> input1Map = new HashMap<String, BigDecimal>();
    private static final Input input2 = new Input();
    private static final Map<String, BigDecimal> input2Map = new HashMap<String, BigDecimal>();
    private static final Input input3 = new Input();
    private static final Map<String, BigDecimal> input3Map = new HashMap<String, BigDecimal>();

    private static final String RESULT = "" +
            "Input data;param1;param2;param3\n" +
            "input1;0.46;4.468;0.3\n" +
            "input2;2.12;;0.781\n" +
            "input3;0;14.347;1\n";

    static {
        input1.setName("input1");
        input2.setName("input2");
        input3.setName("input3");
        resultsByInput.put(input1, input1Map);
        resultsByInput.put(input2, input2Map);
        resultsByInput.put(input3, input3Map);
        input1Map.put("param1", new BigDecimal("0.460"));
        input1Map.put("param2", new BigDecimal("4.4678"));
        input1Map.put("param3", new BigDecimal("0.3"));
        input2Map.put("param1", new BigDecimal("2.12"));
        input2Map.put("param3", new BigDecimal("0.7809"));
        input3Map.put("param1", new BigDecimal("0"));
        input3Map.put("param2", new BigDecimal("14.34678"));
        input3Map.put("param3", new BigDecimal("1"));
    }

    @Test
    public void printResultDataToCsvTest() {
        StringWriter sw = new StringWriter();
        CsvPrinter.printResultDataToCsv(sw, sortedParams, resultsByInput, SEPARATOR);
        Assert.assertEquals(RESULT, sw.toString());
    }
}
