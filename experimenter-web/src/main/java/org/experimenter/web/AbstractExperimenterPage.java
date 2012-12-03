package org.experimenter.web;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Base for all pages containing the left hand menu
 * 
 * @author jfaryad
 * 
 */
public abstract class AbstractExperimenterPage extends BasePage {
    private static final long serialVersionUID = 1L;

    public AbstractExperimenterPage(final PageParameters parameters, final String pageTitle) {
        super(parameters);
        add(new Label("content-title", pageTitle));
        add(new Menu("menu"));
        Injector.get().inject(this);
    }
}
