package com.team07.signinapp;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Handles user logins
public class Login {
    public enum UserType {
        Student,
        Staff,
    }


    private UserInterface userInterface;
    private Map<String, String> userLogins;
    private Map<String, UserType> userTypes;

    public Login(){
        userLogins = new HashMap<>();
        userTypes = new HashMap<>();

        userLogins.put("student", "student");
        userTypes.put("student", UserType.Student);

        userLogins.put("staff", "staff");
        userTypes.put("staff", UserType.Staff);
    }

    public boolean loginValid(String username, String password){
        // TODO: Use database
        //return userLogins.containsKey(username) && userLogins.get(username).equals(password);

        User u = new User();
        u.setUsername(username);
        u.setPassword(password);

        userInterface = new UserInterface();
        userInterface.setUserDetails(u);

        //Testing logins to avoid server
        //TODO: Delete this at some point
        if(username.equals("a") && password.equals("a"))
        {
            u.setUserType(1);
            return true;
        }

        if(username.equals("b") && password.equals("b"))
        {
            u.setUserType(0);
            return true;
        }

        try {
            return userInterface.sendUser();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    public UserInterface getUserInterface(){
        return userInterface;
    }

    public UserType getUserType(String username){
        // TODO: Use database
        return userTypes.get(username);
    }
}