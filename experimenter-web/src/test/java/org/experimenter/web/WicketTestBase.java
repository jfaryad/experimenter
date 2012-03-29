package org.experimenter.web;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
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
