package org.experimenter.web;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class WicketTestBase {

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    private ExperimenterApplication experimenterApplication;

    protected WicketTester tester;

    @Before
    public void init() {
        tester = new WicketTester(experimenterApplication);
    }
}
