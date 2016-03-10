package com.team07.signinapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.TestCase;

import org.json.JSONObject;
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


    public void testSetUserType() throws Exception {

    }

    public void testGetPassword() throws Exception {
        User u = new User();
        u.setPassword("Password");

        assertEquals("Getting Password is Password", u.getPassword(), "Password");
    }

    public void testSetPassword() throws Exception {

    }

    public void testGetUsername() throws Exception {
        User u = new User();
        u.setUsername("Test");

        assertEquals("Getting Username is Test", u.getUsername(), "Test");

    }

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

    public void testToJson() throws Exception {
        User u = new User();

        u.setUsername("user");
        u.setPassword("password");
        u.setUserType(1);

        assertEquals("To Json username is user", (u.toJson()).getString("username"), "user");
        assertEquals("To Json password is password", (u.toJson()).getString("password"), "password");
        assertEquals("To Json user type is 1 or staff", (u.toJson()).getInt("userType"), 1);
    }

    public void testFromJson() throws Exception {
        User u = new User();

        JSONObject jsonForU = new JSONObject();

        jsonForU.put("username", "user");
        jsonForU.put("password", "password");
        jsonForU.put("userType", 1);

        u.fromJson(jsonForU);

        assertEquals("From Json username is user", u.getUsername(), "user");
        assertEquals("From Json password is password", u.getPassword(), "password");
        assertTrue("From Json is Staff", u.isStaff());

    }
}