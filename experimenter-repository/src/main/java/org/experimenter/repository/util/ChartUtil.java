package org.experimenter.repository.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.experimenter.repository.dto.ChartSettings;
import org.experimenter.repository.dto.ChartSettings.ChartSize;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMImplementation;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Helper class for creating charts and providing them in various formats.
 * 
 * @author jfaryad
 * 
 */
public class ChartUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ChartUtil.class);

    /**
     * Creates the chart and returns it as a {@link BufferedImage}, so that it doesn't have to be saved to the
     * filesystem.
     * 
     * @param settings
     *            chart settings
     * @param experimentResults
     *            chart data
     */
    public static BufferedImage getChartAsBufferedImage(ChartSettings settings,
            Map<String, List<BigDecimal>> experimentResults) {
        return generateChart(settings, experimentResults).createBufferedImage(settings.getSize().getWidth(),
                settings.getSize().getHeight());
    }

    /**
     * Creates the chart and writes it as a pdf into the provided file
     * 
     * @param settings
     *            chart settings
     * @param experimentResults
     *            chart data
     * @param outputFile
     *            the pdf file to write to. It has to exist already.
     */
    public static void writeChartToPdf(ChartSettings settings,
            Map<String, List<BigDecimal>> experimentResults, File outputFile) {

        JFreeChart chart = generateChart(settings, experimentResults);

        Document document;
        if (ChartSize.A4.equals(settings.getSize()) || ChartSize.A6.equals(settings.getSize())) {
            document = new Document(PageSize.A4.rotate());
        } else {
            document = new Document(PageSize.A4);
        }

        try {
            File jpg = new File(outputFile.getAbsolutePath() + ".jpg");
            jpg.createNewFile();
            saveChart(jpg, chart, settings.getSize().getWidth(), settings.getSize().getHeight());
            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();
            System.out.println("size: " + document.getPageSize());
            Image image = Image.getInstance(jpg.getAbsolutePath());
            image.scaleToFit(settings.getSize().getPdfPageSize().getWidth(), settings.getSize().getPdfPageSize()
                    .getHeight());

            image.setAbsolutePosition(0, document.getPageSize().getHeight() - image.getScaledHeight());
            document.add(image);

            document.close();
            jpg.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JFreeChart generateChart(ChartSettings settings, Map<String, List<BigDecimal>> experimentResults) {

        XYSeriesCollection dataset = new XYSeriesCollection();
        for (Entry<String, List<BigDecimal>> experiment : experimentResults.entrySet()) {

            XYSeries series = new XYSeries(experiment.getKey());
            Collections.sort(experiment.getValue());
            int counter = 1;
            series.add(0, 0);
            for (BigDecimal value : experiment.getValue()) {
                series.add(counter, value.doubleValue());
                counter++;
            }
            dataset.addSeries(series);
        }
        JFreeChart chart = ChartFactory.createXYLineChart(
                settings.getTitle(), // Title
                settings.getxAxisLabel(), // x-axis Label
                settings.getyAxisLabel(), // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                false, // Use tooltips
                false // Configure chart to generate URLs?
                );

        // display only integers on x-axis
        XYPlot plot = chart.getXYPlot();
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // set legent to the right
        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);

        // display shapes on the lines
        ((XYLineAndShapeRenderer) chart.getXYPlot().getRenderer()).setBaseShapesVisible(true);

        return chart;
    }

    private void saveSvg(File outputFile, JFreeChart chart, int width, int height) {
        DOMImplementation domImpl =
                GenericDOMImplementation.getDOMImplementation();
        org.w3c.dom.Document document = domImpl.createDocument(null, "svg", null);

        // Create an instance of the SVG Generator
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // draw the chart in the SVG generator
        chart.draw(svgGenerator, new Rectangle(width, height));

        // Write svg file
        try {
            OutputStream outputStream = new FileOutputStream(outputFile);
            Writer out = new OutputStreamWriter(outputStream, "UTF-8");
            svgGenerator.stream(out, true /* use css */);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            LOG.error("Error creating the svg image of the chart.", e);
            throw new RuntimeException("Error creating the svg image of the chart.", e);
        }
    }

    private static void saveChart(File outputFile, JFreeChart chart, int width, int height) {
        try {
            ChartUtilities.saveChartAsJPEG(outputFile, chart, width, height);
        } catch (IOException e) {
            System.err.println("Problem occurred creating chart.");
        }
    }

    public static void main(String[] args) {
        System.out.println("balblabla; ; ;    sdfs sdf; s; ;;;sdf   ;;     ;dd ;".replaceAll(";+[;\\s]*;+", ";"));
    }

}
