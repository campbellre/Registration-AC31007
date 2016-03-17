package com.team07.signinapp;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ryan on 25/02/2016.
 */
public class User implements Parcelable{

    private String username;
    private String password;

    // Weirdness means 1 is staff, 0 is student
    private int userType;


    public User(){}

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
        userType = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public void fromJson(JSONObject convertFrom){

        // Check JsonObject is not null

        if (convertFrom == null) return;

        try {
            this.username = convertFrom.getString("username");
            this.password = convertFrom.getString("password");
            this.userType = convertFrom.getInt("userType");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJson()
    {
        JSONObject convertTo = new JSONObject();

        try {
            convertTo.put("username", this.username);
            convertTo.put("password", this.password);
            convertTo.put("userType", this.userType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
     return convertTo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeInt(userType);
    }
}
