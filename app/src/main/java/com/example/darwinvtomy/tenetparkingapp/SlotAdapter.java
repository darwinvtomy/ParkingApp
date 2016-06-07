package com.example.darwinvtomy.tenetparkingapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DARWIN V TOMY on 4/22/2016.
 */
public class SlotAdapter extends ArrayAdapter<ParkingSlot> {
    private Context context;
    private ArrayList<ParkingSlot> parkingSlotList;
    private int SELECTEDSLOT_NUMBER;
    private int SELECTEDSLOT_COLOR;


    public SlotAdapter(Context context, int resource, ArrayList<ParkingSlot> objects, int selectedSlot, int color) {
        super(context, resource, objects);
        this.context = context;
        this.parkingSlotList = objects;
        this.SELECTEDSLOT_NUMBER = selectedSlot;
        this.SELECTEDSLOT_COLOR = color;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slot_item,parent,false);

        ParkingSlot parkingSlot = parkingSlotList.get(position);
        TextView name = (TextView) view.findViewById(R.id.p_name);
        RelativeLayout itemLayout = (RelativeLayout) view.findViewById(R.id.item_background);
       // TextView cost = (TextView) view.findViewById(R.id.p_cost);
/*        if(SELECTEDSLOT_NUMBER==position){
            if(SELECTEDSLOT_COLOR ==1){
                itemLayout.setBackgroundColor(Color.RED);
            }else if (SELECTEDSLOT_COLOR == 2){
                itemLayout.setBackgroundColor(Color.GREEN);
            }

        }*/
        if(parkingSlot.getColor()==2){
            itemLayout.setBackgroundColor(Color.RED);
        }else if(parkingSlot.getColor() == 3 ){
            itemLayout.setBackgroundColor(Color.GREEN);
        }
        name.setText(parkingSlot.getSlotId());
       // cost.setText(parkingSlot.getCost()+"");
        return view;
    }
}
