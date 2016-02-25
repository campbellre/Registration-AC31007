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

        UserInterface userInterface = new UserInterface();
        userInterface.setUserDetails(u);

        try {
            return userInterface.sendUser();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    public UserType getUserType(String username){
        // TODO: Use database
        return userTypes.get(username);
    }
}