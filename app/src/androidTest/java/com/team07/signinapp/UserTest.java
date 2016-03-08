package com.team07.signinapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 08/03/2016.
 */
public class UserTest extends ApplicationTestCase<Application> {

    public UserTest() {
        super(Application.class);
    }

   /* @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }*/

//    @Test
    /*public void testGetUserType() throws Exception {
        User u = new User();
        u.setUserType(1);

        assertEquals("Getting user type is 1", u.getUserType(), 1);
    }*/

//    @Test
    public void testSetUserType() throws Exception {

    }

//    @Test
    public void testGetPassword() throws Exception {
        User u = new User();
        u.setPassword("Password");

        assertEquals("Getting Password is Password", u.getPassword(), "Password");
    }

//    @Test
    public void testSetPassword() throws Exception {

    }

//    @Test
    public void testGetUsername() throws Exception {
        User u = new User();
        u.setUsername("Test");

        assertEquals("Getting Username is Test", u.getUsername(), "Test");

    }

//    @Test
    public void testSetUsername() throws Exception {

    }

    public void testIsStaff() throws Exception {
        User u = new User();
        u.setUserType(1);

        assertTrue("User is Staff", u.isStaff());
    }

    public void testIsStudent() throws Exception {
        User u = new User();
        u.setUserType(0);

        assertTrue("User is Student", u.isStudent());
    }
}