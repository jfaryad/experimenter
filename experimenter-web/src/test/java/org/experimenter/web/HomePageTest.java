package org.experimenter.web;

import org.junit.Test;

/**
 * Simple test using the WicketTester
 */

public class HomePageTest extends WicketTestBase {

    @Test
    public void homepageRendersSuccessfully() {
        // start and render the test page
        tester.startPage(HomePage.class);

        // assert rendered page class
        tester.assertRenderedPage(HomePage.class);
    }
}