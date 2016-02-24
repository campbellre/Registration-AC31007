package com.team07.signinapp;

import java.util.HashMap;
import java.util.Map;

// Handles user logins
public class Login {
    public enum UserType {
        Student,
        Staff,
        None
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
        return userLogins.containsKey(username) && userLogins.get(username).equals(password);
    }

    public UserType getUserType(String username){
        // TODO: Use database
        return userTypes.get(username);
    }
}