package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Custom stuled logout page. On instantiation, invalidates the session.
 * 
 * @author jfaryad
 * 
 */
public class LogoutPage extends BasePage {

    private static final long serialVersionUID = 1L;

    public LogoutPage(PageParameters parameters) {
        super(parameters);
        getSession().invalidate();
    }

}
