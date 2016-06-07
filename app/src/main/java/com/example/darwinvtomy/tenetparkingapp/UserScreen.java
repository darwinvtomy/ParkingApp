package com.example.darwinvtomy.tenetparkingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UserScreen extends AppCompatActivity {
Button slotChecking;
    Button linkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        linkView = (Button) findViewById(R.id.userScreenLink);

        slotChecking = (Button) findViewById(R.id.slot_checking);
        slotChecking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserScreen.this,DeviceListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showQRCode(View view) {
        Intent intent = new Intent(UserScreen.this,QRCodeDisplay.class);
        startActivity(intent);
    }

    public void showTheLinkPlease(View view) {
        linkView.setVisibility(View.VISIBLE);
    linkView.setText("GO");
    }

    public void gotothelink(View view) {
        Uri adress= Uri.parse(GlobalClass.UrlLink);
        Intent browser= new Intent(Intent.ACTION_VIEW, adress);
        startActivity(browser);
    }
}
