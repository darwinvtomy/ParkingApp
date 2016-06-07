package com.example.darwinvtomy.tenetparkingapp;

/**
 * Created by DARWIN V TOMY on 4/25/2016.
 */
public class GlobalClass {
    private static ParkingSlot selectedParkingSlot;
    private static String UserID;
    private static UserDetails USER_DETAILS;
    public static  String UrlLink = "http://www.parkinglot.tenettech.net/";



    public static void setSelectedParkingSlot(ParkingSlot selectedParkingSlot) {
        GlobalClass.selectedParkingSlot = selectedParkingSlot;
    }

    public static ParkingSlot getheSelectedProductDetails() {
        return selectedParkingSlot;
    }

    public static void setUserID (String UserIDReceived){
        GlobalClass.UserID = UserIDReceived;
    }
    public static String getTheUserID(){
        return UserID;
    }

    public static void saveTheUserDetails(UserDetails userDetailses) {
        GlobalClass.USER_DETAILS = userDetailses;
    }
    public static UserDetails getTheUserinFo(){
        return USER_DETAILS;
    }

}
