package org.experimenter.web;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * The empty welcome page. Planned for adding shortcuts to frequent actions or some news feed.
 * 
 * @author jfaryad
 * 
 */
@AuthorizeInstantiation("USER")
public class HomePage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        super(parameters, "Experimenter Home Page");
    }
}
