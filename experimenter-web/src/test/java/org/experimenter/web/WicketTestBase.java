package org.experimenter.web;

import java.util.Locale;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class WicketTestBase {

    @Autowired
    private ApplicationContext applicationContext;

    protected WicketTester tester;

    @Before
    public void init() {
        populateData();
        createTester();
    }

    private void createTester() {
        WebApplication app = (WebApplication) applicationContext.getBean("experimenterApplication");
        tester = new WicketTester(app, app.getServletContext());
        tester.getApplication().getComponentInstantiationListeners()
                .add(new SpringComponentInjector(tester.getApplication(), applicationContext));
    }

    /**
     * Override to change locale
     * 
     * @return locale, default EN
     */
    protected Locale getLocale() {
        return new Locale("EN");
    }

    /**
     * Override to populate data in database for each test
     */
    protected void populateData() {
        // override in test if necessary
    }
}
