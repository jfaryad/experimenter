package org.experimenter.web.component;

import java.awt.image.BufferedImage;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse.CacheScope;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.dto.ChartSettings;
import org.experimenter.repository.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An image generated from a {@link BufferedImage} on the fly to display the chart previews without having to save them
 * as files.
 * 
 * @author jfaryad
 * 
 */
public class DynamicChartImage extends Image {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(DynamicChartImage.class);

    @SpringBean
    private ResultService resultService;

    private final IModel<ChartSettings> imageSettingsModel = Model.of((ChartSettings) null);

    public DynamicChartImage(String id) {
        super(id);
        setOutputMarkupId(true);
        Injector.get().inject(this);
    }

    @Override
    protected IResource getImageResource() {
        DynamicImageResource resource = new DynamicImageResource() {

            private static final long serialVersionUID = 1L;

            @Override
            protected byte[] getImageData(final Attributes attributes) {
                ChartSettings settings = imageSettingsModel.getObject();
                if (settings != null) {
                    try {
                        BufferedImage image = resultService.getResultChartAsBufferedImage(settings);
                        return toImageData(image);
                    } catch (Exception e) {
                        LOG.error("Unable to retrieve the buffered chart image", e);
                        error("Error retrieveing the chart preview");

                    }
                }
                return toImageData(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));
            }

            @Override
            protected void configureResponse(final ResourceResponse response, final Attributes attributes) {
                super.configureResponse(response, attributes);
                response.disableCaching();
                response.setCacheScope(CacheScope.PRIVATE);
            }

        };

        return resource;
    }

    public void setImageSettings(ChartSettings settings) {
        this.imageSettingsModel.setObject(settings);
    }
}
