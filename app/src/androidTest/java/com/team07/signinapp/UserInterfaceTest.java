package com.team07.signinapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 08/03/2016.
 */
public class UserInterfaceTest extends ApplicationTest {

    @Test
    public void testSetUserDetails() throws Exception {
        UserInterface ui = new UserInterface();

        User u = new User();
        u.setUsername("user");
        u.setPassword("password");

        ui.setUserDetails(u);

        assertEquals("User in Interface is the same", ui.getUserInstance(), u);
    }

}