package com.team07.signinapp;

/**
 * Created by Ryan on 25/02/2016.
 */
public class User {

    private String username;
    private String password;

    // Weirdness means 1 is staff, 0 is student
    private int userType;


    private int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
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


    public boolean isStaff()
    {
        return (userType == 1);
    }

    public boolean isStudent()
    {
        return (userType == 0);
    }

}
