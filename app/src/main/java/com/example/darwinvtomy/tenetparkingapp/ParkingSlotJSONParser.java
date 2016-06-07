package com.example.darwinvtomy.tenetparkingapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DARWIN V TOMY on 4/22/2016.
 */
public class ParkingSlotJSONParser

{
    private String JSONString;

    public ParkingSlotJSONParser(String strJson) {
        this.JSONString = strJson;
    }


    public ArrayList<ParkingSlot> ParseJSON() {
        ArrayList<ParkingSlot> parkingSlots = new ArrayList<>();
        try {
            JSONObject jsonRootObject = new JSONObject(JSONString);
            JSONArray jsonArray = jsonRootObject.optJSONArray("parkingslot");

            for (int i = 0; i < jsonArray.length(); i++) {
                ParkingSlot slotitem = new ParkingSlot();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
              //  int id = Integer.parseInt(jsonObject.optString("id"));
                String slotid = jsonObject.optString("slotid");
              //  double mrp = Double.parseDouble(jsonObject.optString("mrp"));
              //  String weight = jsonObject.optString("weight");
              //  String mfg = jsonObject.optString("mfg");
                String id = jsonObject.optString("id");
                String idg = jsonObject.getString("idg");
                int color = jsonObject.getInt("color");
              //  productitem.setId(id);
                slotitem.setSlotId(slotid);
              //  productitem.setCost(mrp);
                slotitem.setId(id);
                slotitem.setIdg(idg);
                slotitem.setColor(color);
             //   productitem.setMfgdate(mfg);
             //   productitem.setExpdate(exp);
                parkingSlots.add(slotitem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return parkingSlots;
    }


}

