package org.experimenter.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class AbstractExperimenterPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public AbstractExperimenterPage(final PageParameters parameters, final String pageTitle) {
        super(parameters);
        add(new Label("page-title", pageTitle));
        add(new Label("content-title", pageTitle));
        add(new Menu("menu"));
    }
}
