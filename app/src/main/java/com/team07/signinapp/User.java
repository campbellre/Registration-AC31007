package com.team07.signinapp;

/**
 * Created by Ryan on 25/02/2016.
 */
public class User {

    private String username;
    private String password;
    private UserType userType;


    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
