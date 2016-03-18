package com.team07.signinapp;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    // Technicaly works
    public void testLoginFailsForInvalidCredentials() throws Exception {
        Login login = new Login();
        boolean result = login.loginValid("student", "");
        assertFalse(result);
    }
}