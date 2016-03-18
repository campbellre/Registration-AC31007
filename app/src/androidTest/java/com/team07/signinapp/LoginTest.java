package com.team07.signinapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 08/03/2016.
 */
public class LoginTest extends ApplicationTestCase<Application> {

    public LoginTest() {
        super(Application.class);
    }

    public void testLoginValid() throws Exception {
        Login login = new Login();
        assertTrue(login.loginValid("lsmith", "pass"));
    }

    public void testGetUserType() throws Exception {
        Login login = new Login();
        assertTrue(login.getUserType("lsmith").equals("lecturer"));
        assertTrue(login.getUserType("lpalm").equals("student"));
    }
}