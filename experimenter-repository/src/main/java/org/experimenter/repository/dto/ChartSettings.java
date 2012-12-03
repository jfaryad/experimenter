package org.experimenter.repository.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

/**
 * Dto for passing the user selected settings for generating charst from the UI to the service.
 * 
 * @author jfaryad
 * 
 */
public class ChartSettings implements Serializable {

    public static enum ChartSize {
        A4("A4", PageSize.A4.rotate(), 1190, 1684),
        A5("A5", PageSize.A5.rotate(), 990, 1400),
        A6("A6", PageSize.A6.rotate(), 850, 1200),
        PREVIEW("PREVIEW", null, 425, 600);

        private final int height;
        private final int width;
        private final String name;
        private final Rectangle pdfPageSize;

        private ChartSize(String name, Rectangle pdfPageSize, int height, int width) {
            this.height = height;
            this.width = width;
            this.name = name;
            this.pdfPageSize = pdfPageSize;
        }

        public static ChartSize[] getSelectableValues() {
            return new ChartSize[] { A4, A5, A6 };
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public Rectangle getPdfPageSize() {
            return pdfPageSize;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static final long serialVersionUID = 1L;
    private String param;
    private ChartSize size = ChartSize.A4;
    private String title;
    private String xAxisLabel;
    private String yAxisLabel;
    private Set<Integer> experimentIds = new HashSet<Integer>();

    @Override
    public ChartSettings clone() {
        ChartSettings clone = new ChartSettings();
        clone.setParam(param);
        clone.setSize(size);
        clone.setTitle(title);
        clone.setxAxisLabel(xAxisLabel);
        clone.setyAxisLabel(yAxisLabel);
        clone.getExperimentIds().addAll(experimentIds);
        return clone;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public ChartSize getSize() {
        return size;
    }

    public void setSize(ChartSize size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getxAxisLabel() {
        return xAxisLabel;
    }

    public void setxAxisLabel(String xAxisLabel) {
        this.xAxisLabel = xAxisLabel;
    }

    public String getyAxisLabel() {
        return yAxisLabel;
    }

    public void setyAxisLabel(String yAxisLabel) {
        this.yAxisLabel = yAxisLabel;
    }

    public Set<Integer> getExperimentIds() {
        return experimentIds;
    }

    public void setExperimentIds(Set<Integer> experimentIds) {
        this.experimentIds = experimentIds;
    }

}
