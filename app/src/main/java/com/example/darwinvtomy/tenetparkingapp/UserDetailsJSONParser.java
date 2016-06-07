package com.example.darwinvtomy.tenetparkingapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DARWIN V TOMY on 4/22/2016.
 */
public class UserDetailsJSONParser

{
    private String JSONString;

    public UserDetailsJSONParser(String strJson) {
        this.JSONString = strJson;
    }


    public ArrayList<UserDetails> ParseJSON() {
        ArrayList<UserDetails> userDetailses = new ArrayList<>();
        try {
            JSONObject jsonRootObject = new JSONObject(JSONString);
            JSONArray jsonArray = jsonRootObject.optJSONArray("usrdetails");

            for (int i = 0; i < jsonArray.length(); i++) {
                UserDetails userDetails = new UserDetails();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
              //  int id = Integer.parseInt(jsonObject.optString("id"));
                String usrname = jsonObject.optString("usrname");
                String password = jsonObject.optString("pass");
                String qrimageLocation = jsonObject.optString("qrimage");
              //  double mrp = Double.parseDouble(jsonObject.optString("mrp"));
              //  String weight = jsonObject.optString("weight");
              //  String mfg = jsonObject.optString("mfg");
             //   String id = jsonObject.optString("id");
              //  productitem.setId(id);
                userDetails.setUserID(usrname);
              //  productitem.setCost(mrp);
                userDetails.setPassword(password);
                userDetails.setQrLocation(qrimageLocation);
             //   productitem.setMfgdate(mfg);
             //   productitem.setExpdate(exp);
                userDetailses.add(userDetails);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDetailses;
    }


}

