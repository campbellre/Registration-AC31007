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
/*


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
*/


    //
    // TODO:
    // This does nothing until someone works out how not to show our passwords on the internet.
    //
    public void testLoginValid() throws Exception {
        Login login = new Login();
    }


    public void testGetUserType() throws Exception {

    }
}