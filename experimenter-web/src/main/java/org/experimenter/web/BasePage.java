package org.experimenter.web;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.model.CurrentUserModel;

/**
 * Base class for all experimenter pages. Contains the base style.
 * 
 * @author jfaryad
 * 
 */
public abstract class BasePage extends WebPage {
    private static final long serialVersionUID = 1L;

    public BasePage(final PageParameters parameters) {
        super(parameters);
        WebMarkupContainer wc = new WebMarkupContainer("forSignedInUsers") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisibilityAllowed(((AuthenticatedWebSession) getSession()).isSignedIn());
            }
        };
        add(wc);

        wc.add(new Label("configLinkText", new PropertyModel<String>(new CurrentUserModel(), "fullName")));
    }
}
