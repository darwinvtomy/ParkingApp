package com.example.darwinvtomy.tenetparkingapp;

/**
 * Created by DARWIN V TOMY on 4/26/2016.
 */
public class UserDetails {


    private String userID;
    private String password;
    private String qrLocation;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQrLocation() {
        return qrLocation;
    }

    public void setQrLocation(String qrLocation) {
        this.qrLocation = qrLocation;
    }
}
