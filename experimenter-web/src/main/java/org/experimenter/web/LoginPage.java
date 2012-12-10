package org.experimenter.web;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Custom styled login page. Contains only the default login panel.
 * 
 * @author jfaryad
 * 
 */
public class LoginPage extends BasePage {

    private static final long serialVersionUID = 1L;

    public LoginPage(PageParameters parameters) {
        super(parameters);
        add(new SignInPanel("signInPanel"));
    }

}
