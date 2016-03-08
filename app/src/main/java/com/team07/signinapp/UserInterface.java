package com.team07.signinapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Ryan on 25/02/2016.
 */
public class UserInterface {

    private User setUser;

    public UserInterface()
    {

    }

    // Set the user details to send to the webapp.
    public void setUserDetails(User user)
    {
        setUser = user;
    }

    public User getUserInstance() {return  setUser; }

    public Boolean sendUser() throws JSONException, IOException {
        // Create the user object
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("username", this.setUser.getUsername());
        jsonObject.put("password", this.setUser.getPassword());

        // Send to the internet.

        ServerInteraction serverInteraction = new ServerInteraction();

        JSONObject jsonToParse = serverInteraction.postAndGetJson(jsonObject, "login");

        if(jsonToParse != null)
        {
            if(jsonToParse.getString("username").equals("no")) {
                return false;
            }

            this.setUser.setUserType(jsonToParse.getInt("userType"));
            return true;
        }

        return false;

    }


}
