package org.experimenter.web;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
@Ignore
public class AbstractExperimenterPageTest extends WicketTestBase {

    @Test
    public void templatePageRendersSuccessfully() {
        // start and render the test page
        tester.startPage(AbstractExperimenterPage.class);

        // assert rendered page class
        tester.assertRenderedPage(AbstractExperimenterPage.class);
    }
}
