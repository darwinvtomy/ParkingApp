package com.example.darwinvtomy.tenetparkingapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    // SPP UUID service - this should work for most devices
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String for MAC address
    private static String address;
    final int handlerState = 0;                         //used to identify handler message
    Button chkAllotment, bDestination, cDestination,dButton, smsButton,eButton,fButton;
    TextView txtArduino, txtString, txtStringLength, sensorView0, grandTotal;
    Handler bluetoothIn;
    String readMessage;
    String JSONTextFile;
    ArrayList<ParkingSlot> parkingSlots;
    //ArrayList<ParkingSlot> selectedproducts = new ArrayList<>();
    GridView slotsList;
    SlotAdapter slotAdapter;
    String bluetooth_message;
    TextView total_added;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder recDataString = new StringBuilder();
    private ConnectedThread mConnectedThread;
    private int removeID;

    public static String convertStreamToString(InputStream is)
            throws IOException {
        Writer writer = new StringWriter();

        char[] buffer = new char[2048];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
                Log.e("WRITER","The writer value "+writer.toString());
            }
        } finally {
            is.close();
        }
        String text = writer.toString();
        return text;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        slotsList = (GridView) findViewById(R.id.p_listview);
        //Link the buttons and textViews to respective views
     //  chkAllotment = (Button) findViewById(R.id.chk_allotment);
     /*    bDestination = (Button) findViewById(R.id.b_destination);
        cDestination = (Button) findViewById(R.id.c_destination);
        smsButton = (Button) findViewById(R.id.sms);
        eButton = (Button) findViewById(R.id.send_e);
        fButton = (Button) findViewById(R.id.send_f);
        dButton = (Button) findViewById(R.id.send_d);*/


        // txtString = (TextView) findViewById(R.id.txtString);
        //   txtStringLength = (TextView) findViewById(R.id.testView1);
        sensorView0 = (TextView) findViewById(R.id.sensorView0);
      //

       /* sensorView1 = (TextView) findViewById(R.id.sensorView1);
        sensorView2 = (TextView) findViewById(R.id.sensorView2);
        sensorView3 = (TextView) findViewById(R.id.sensorView3);*/
        total_added = (TextView) findViewById(R.id.amt);
        try {
            InputStream is = getResources().getAssets().open("parkingslotlist.json");
            Log.e("PRODUCT LIST", "___________PRODUCT LIST FOUND_____________");
            JSONTextFile = convertStreamToString(is);
            ParkingSlotJSONParser JSONObject = new ParkingSlotJSONParser(JSONTextFile);
            parkingSlots = JSONObject.ParseJSON();
            for (int i = 0; i < parkingSlots.size(); i++) {
                Log.e("PRODUCT LIST", "Slot ID " + parkingSlots.get(i).getSlotId());
                Log.e("PRODUCT LIST", "ID " + parkingSlots.get(i).getId() + "");
                Log.e("PRODUCT LIST", "IDG "+parkingSlots.get(i).getIdg()+"");
                Log.e("PRODUCT LIST", "COLOR "+parkingSlots.get(i).getColor()+"");
                /*Log.e("PRODUCT LIST", "Cost " + userDetailses.get(i).getCost() + "");
                Log.e("PRODUCT LIST", "Exp Date " + userDetailses.get(i).getExpdate());
                Log.e("PRODUCT LIST", "Mfg Date " + userDetailses.get(i).getMfgdate());*/
              //  Log.e("PRODUCT LIST", "Weight " + userDetailses.get(i).getId());
                Log.e("PRODUCT LIST", "______________________________________");


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    updateDisplay();
        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {                                        //if message is what we want
                    String readMessage = (String) msg.obj;                                                                // msg.arg1 = bytes from connect thread
                    Log.e("BLUELOG", readMessage);
                    //   recDataString.append(readMessage);      								//keep appending to string until ~
                    //  int endOfLineIndex = recDataString.indexOf("~");
                    // determine the end-of-line

                    /*if (endOfLineIndex > 2) {                                           // make sure there data before ~
                        String dataInPrint = recDataString.substring(0, endOfLineIndex);    // extract string
                    //    txtString.setText("Data Received = " + dataInPrint);
                        Log.e("BLUELOG",dataInPrint);
                        sensorView1.setText(dataInPrint.trim());

                      //  Toast.makeText(MainActivity.this,dataInPrint,Toast.LENGTH_SHORT).show();
                        int dataLength = dataInPrint.length();							//get length of data received
                     //   txtStringLength.setText("String Length = " + String.valueOf(dataLength));

                        if (recDataString.charAt(0) == '#')								//if it starts with # we know it is what we are looking for
                        {
                            String sensor0 = recDataString.substring(1, 5);             //get sensor value from string between indices 1-5
                            String sensor1 = recDataString.substring(6, 10);            //same again...
                            String sensor2 = recDataString.substring(11, 15);
                            String sensor3 = recDataString.substring(16, 20);

                            *//*sensorView0.setText(" Sensor 0 Voltage = " + sensor0 + "V");	//update the textviews with sensor values
                            sensorView1.setText(" Sensor 1 Voltage = " + sensor1 + "V");
                            sensorView2.setText(" Sensor 2 Voltage = " + sensor2 + "V");
                            sensorView3.setText(" Sensor 3 Voltage = " + sensor3 + "V");*//*
                        }
                        recDataString.delete(0, recDataString.length()); 					//clear all string data
                        // strIncom =" ";
                        dataInPrint = " ";
                    }*/
                }
            }
        };
/*        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {                                        //if message is what we want
                    String readMessage = (String) msg.obj;                                                                // msg.arg1 = bytes from connect thread
                    Log.e("BLUELOG", readMessage);
                    //   recDataString.append(readMessage);      								//keep appending to string until ~
                    //  int endOfLineIndex = recDataString.indexOf("~");
                    // determine the end-of-line

                    *//*if (endOfLineIndex > 2) {                                           // make sure there data before ~
                        String dataInPrint = recDataString.substring(0, endOfLineIndex);    // extract string
                    //    txtString.setText("Data Received = " + dataInPrint);
                        Log.e("BLUELOG",dataInPrint);
                        sensorView1.setText(dataInPrint.trim());

                      //  Toast.makeText(MainActivity.this,dataInPrint,Toast.LENGTH_SHORT).show();
                        int dataLength = dataInPrint.length();							//get length of data received
                     //   txtStringLength.setText("String Length = " + String.valueOf(dataLength));

                        if (recDataString.charAt(0) == '#')								//if it starts with # we know it is what we are looking for
                        {
                            String sensor0 = recDataString.substring(1, 5);             //get sensor value from string between indices 1-5
                            String sensor1 = recDataString.substring(6, 10);            //same again...
                            String sensor2 = recDataString.substring(11, 15);
                            String sensor3 = recDataString.substring(16, 20);

                            *//**//*sensorView0.setText(" Sensor 0 Voltage = " + sensor0 + "V");	//update the textviews with sensor values
                            sensorView1.setText(" Sensor 1 Voltage = " + sensor1 + "V");
                            sensorView2.setText(" Sensor 2 Voltage = " + sensor2 + "V");
                            sensorView3.setText(" Sensor 3 Voltage = " + sensor3 + "V");*//**//*
                        }
                        recDataString.delete(0, recDataString.length()); 					//clear all string data
                        // strIncom =" ";
                        dataInPrint = " ";
                    }*//*
                }
            }
        };*/

        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
        checkBTState();


        // Set up onClick listeners for buttons to send 1 or 0 to turn on/off LED


/*       chkAllotment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mConnectedThread.write("x");    // Send "x" via Bluetooth
                Toast.makeText(getBaseContext(), "x", Toast.LENGTH_SHORT).show();
            }
        });*/

   /*      bDestination.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mConnectedThread.write("B");    // Send "B" via Bluetooth
                Toast.makeText(getBaseContext(), "B", Toast.LENGTH_SHORT).show();
            }
        });
        cDestination.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mConnectedThread.write("C");    // Send "C" via Bluetooth
                Toast.makeText(getBaseContext(), "C", Toast.LENGTH_SHORT).show();
            }
        });
        smsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mConnectedThread.write("S");    // Send "S" via Bluetooth
                Toast.makeText(getBaseContext(), "S", Toast.LENGTH_SHORT).show();
            }
        });

        eButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mConnectedThread.write("E");
                Toast.makeText(getBaseContext(), "E", Toast.LENGTH_SHORT).show();
            }
        });
        fButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mConnectedThread.write("F");    // Send "F" via Bluetooth
                Toast.makeText(getBaseContext(), "F", Toast.LENGTH_SHORT).show();
            }
        });
        dButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mConnectedThread.write("D");    // Send "D" via Bluetooth
                Toast.makeText(getBaseContext(), "D", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

/*    private void addtheitemTothecart(String itemAdded) {
        Log.e("BLUELOG", "Prodict Size = " + userDetailses.size());
        int itemID = Integer.parseInt(itemAdded);
        for (int i = 0; i < userDetailses.size(); i++) {
            if (userDetailses.get(i).getId() == itemID) {
                Log.e("BLUELOG", "ParkingSlot Selected " + userDetailses.get(i).getSlotId());
                if(productisNotAvaliable(userDetailses.get(i).getId())){
                    addProducttotheList(userDetailses.get(i));
                }else {
                    removeTheProductDialog(userDetailses.get(i).getId());
                }

            }
        }

    }*/

/*    private void removeTheItem(int id) {

        for (int i = 0;i<selectedproducts.size();i++){
            if(selectedproducts.get(i).getId()==id){
                selectedproducts.remove(i);
            }
        }

        slotAdapter = new SlotAdapter(this, R.layout.slot_item, selectedproducts);
        slotsList.setAdapter(slotAdapter);
        slotsList.setOnItemClickListener(pRoductClickListener);
        slotAdapter.notifyDataSetChanged();
        printTheTotal();
    }*/

/*    private void removeTheProductDialog(int id) {
        removeID  = id;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("This ParkingSlot is already Added Do you want to remove it");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                removeTheItem(removeID);
                Toast.makeText(MainActivity.this,"Item Removed",Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }*/

/*    private boolean productisNotAvaliable(int id) {
    for (int i =0;i<selectedproducts.size();i++){
        if(selectedproducts.get(i).getId()==id){
            return false;
        }
    }
    return true;
    }*/

/*    private void addProducttotheList(ParkingSlot productadded) {
        slotAdapter = null;
        Log.e("BLUELOG", productadded.getId() + "");
        Log.e("BLUELOG", productadded.getSlotId() + "");
        Log.e("BLUELOG", productadded.getId() + "");
        Log.e("BLUELOG", productadded.getCost() + "");
        Log.e("BLUELOG", productadded.getMfgdate() + "");
        //  slotAdapter = null;

        // productadded = new ParkingSlot();
   *//*     addProduct.setSlotId("Wheat");
        addProduct.setCost(450);
        addProduct.setExpdate("25/7/2016");
        addProduct.setId(9);
        addProduct.setId("4");
        addProduct.setMfgdate("27/3/2015");*//*
      //  selectedproducts.add(productadded);
        slotAdapter = new SlotAdapter(this, R.layout.slot_item, selectedproducts);
        slotsList.setAdapter(slotAdapter);
        slotsList.setOnItemClickListener(pRoductClickListener);
        slotAdapter.notifyDataSetChanged();
        printTheTotal();


*//*        selectedproducts.add(product);
        slotAdapter = new SlotAdapter(this, R.layout.slot_item, selectedproducts);
        slotsList.setAdapter(slotAdapter);
        slotAdapter.notifyDataSetChanged();*//*
    }*/

   private void updateDisplay() {
       slotAdapter = new SlotAdapter(this, R.layout.slot_item, parkingSlots,100,100);
       slotsList.setAdapter(slotAdapter);

   }
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }

    @Override
    public void onResume() {
        super.onResume();

        //Get MAC address from DeviceListActivity via intent
        Intent intent = getIntent();

        //Get the MAC address from the DeviceListActivty via EXTRA
        address = intent.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

        //create device and set the MAC address

        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
        }
        // Establish the Bluetooth socket connection.
        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                //insert code to deal with this
            }
        }
        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();

        //I send a character when resuming.beginning transmission to check device is connected
        //If it is not an exception will be thrown in the write method and finish() will be called
        mConnectedThread.write("x");
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            //Don't leave Bluetooth sockets open when leaving activity
            btSocket.close();
        } catch (IOException e2) {
            //insert code to deal with this
        }
    }

    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    private void checkBTState() {

        if (btAdapter == null) {
            Toast.makeText(getBaseContext(), "Device does not support bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

/*    public void addItem(View view) {
        slotAdapter = null;

        ParkingSlot addProduct = new ParkingSlot();
        addProduct.setSlotId("Wheat");
        addProduct.setCost(450);
        addProduct.setExpdate("25/7/2016");
        addProduct.setId(9);
        addProduct.setId("4");
        addProduct.setMfgdate("27/3/2015");
        userDetailses.add(addProduct);
        slotAdapter = new SlotAdapter(this, R.layout.slot_item, userDetailses);
        slotsList.setAdapter(slotAdapter);
        slotAdapter.notifyDataSetChanged();
    }*/

    //create new class for connect thread
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void run() {
            byte[] buffer = new byte[9600];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);            //read bytes from input buffer
                    readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetooth_message = readMessage.trim();
                    if (isNumeric(bluetooth_message)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                displaytheAmount(Integer.parseInt(bluetooth_message));

                            }
                        });

                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sensorView0.setText(bluetooth_message);
//stuff that updates ui
                                if (!bluetooth_message.isEmpty()) {
                                    pickUptheLocation(bluetooth_message);
                                    Log.e("BLUELOG", "Received Message :" + bluetooth_message);
                                }
                            }
                        });
                    }

                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "Connection Failure", Toast.LENGTH_SHORT).show();
                finish();

            }
        }
    }

    private void displaytheAmount(int i) {
        switch (i){
            case 1:
                total_added.setText(450+" Rs");
                break;
            case 2:
                total_added.setText(400+" Rs");
                break;
            case 3:
                total_added.setText(350+" Rs");
                break;
            case 4:
                total_added.setText(300+" Rs");
                break;
            case 5:
                total_added.setText(250+" Rs");
                break;
            case 6:
                total_added.setText(200+" Rs");
                break;
            case 7:
                total_added.setText(150+" Rs");
                break;
            case 8:
                total_added.setText(100+" Rs");
                break;
            case 9:
                total_added.setText(50+" Rs");
            case 0:
                total_added.setText(0+" Rs");
            }
    }

    private void pickUptheLocation(String bluetooth_message) {


        for (int i = 0;i<parkingSlots.size();i++){
            if(parkingSlots.get(i).getId().equals(bluetooth_message)){
                parkingSlots.get(i).setColor(2);
                slotAdapter = new SlotAdapter(this, R.layout.slot_item, parkingSlots,i,1);
                slotsList.setAdapter(slotAdapter);
                slotAdapter.notifyDataSetChanged();
            }else if(parkingSlots.get(i).getIdg().equals(bluetooth_message)){
                parkingSlots.get(i).setColor(3);
                slotAdapter = new SlotAdapter(this, R.layout.slot_item, parkingSlots,i,2);
                slotsList.setAdapter(slotAdapter);
                slotAdapter.notifyDataSetChanged();
            }
        }
    }

/*    private AdapterView.OnItemClickListener pRoductClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

            Log.e("PRODUCT","Arg2 is "+arg2 );
            Log.e("PRODUCT","Arg3 is"+arg3);
            Log.e("PRODUCT","ID is "+selectedproducts.get(arg2).getSlotId());
            GlobalClass.setSelectedParkingSlot(selectedproducts.get(arg2));
            Intent intent = new Intent(MainActivity.this,ProductDetails.class);
            startActivity(intent);
          //  textView1.setText("Connecting...");
            // Get the device MAC address, which is the last 17 chars in the View
         //   String info = ((TextView) v).getText().toString();
          //  String address = info.substring(info.length() - 17);

            // Make an intent to start next activity while taking an extra which is the MAC address.
          //  Intent i = new Intent(DeviceListActivity.this, MainActivity.class);
          //  i.putExtra(EXTRA_DEVICE_ADDRESS, address);
          //  startActivity(i);
        }
    };*/


/*    private void printTheTotal(){
        double total = 0;
        for (int i = 0;i<selectedproducts.size();i++){
            total = total+selectedproducts.get(i).getCost();
        }
        total_added.setText(total+" "+"Rs");

    }*/
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}


