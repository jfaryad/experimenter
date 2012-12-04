package org.experimenter.web.component;

import java.awt.image.BufferedImage;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.http.WebResponse.CacheScope;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.time.Duration;
import org.experimenter.web.ChartPage;

/**
 * An image generated from a {@link BufferedImage} on the fly to display the char previews without having to save them
 * as files.
 * 
 * @author jfaryad
 * 
 */
public class DynamicChartImage extends Image {

    private static final long serialVersionUID = 1L;

    private transient BufferedImage image;

    public DynamicChartImage(String id) {
        super(id);
        setOutputMarkupId(true);
        setImageResourceReference(new ResourceReference(ChartPage.class, "chart-preview") {

            private static final long serialVersionUID = 1L;

            @Override
            public IResource getResource() {
                return getBufferedImageResource();
            }
        });
    }

    private IResource getBufferedImageResource() {
        DynamicImageResource resource = new DynamicImageResource() {

            private static final long serialVersionUID = 1L;

            @Override
            protected byte[] getImageData(final Attributes attributes) {
                if (image == null) {
                    return toImageData(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));
                } else {
                    byte[] imageData = toImageData(image);
                    image = null;
                    return imageData;
                }
            }

            @Override
            protected void configureResponse(final ResourceResponse response, final Attributes attributes) {
                super.configureResponse(response, attributes);

                if (isCacheable() == false) {
                    response.setCacheDuration(Duration.NONE);
                    response.setCacheScope(CacheScope.PRIVATE);
                }
            }

        };

        return resource;
    }

    protected boolean isCacheable() {
        return false;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
