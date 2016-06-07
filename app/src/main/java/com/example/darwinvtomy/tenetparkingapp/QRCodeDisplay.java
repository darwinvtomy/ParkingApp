package com.example.darwinvtomy.tenetparkingapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class QRCodeDisplay extends AppCompatActivity {
ImageView imageview;
    Button linkVie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_display);
        String QrCodeLocation = GlobalClass.getTheUserinFo().getQrLocation();
        linkVie = (Button) findViewById(R.id.linkText);

        int imageResource = getResources().getIdentifier(QrCodeLocation, null, getPackageName());

        imageview= (ImageView)findViewById(R.id.display_qr);
        Drawable res = getResources().getDrawable(imageResource);
        imageview.setImageDrawable(res);

    }

    public void showTheLink(View view) {
        linkVie.setVisibility(View.VISIBLE);
        linkVie.setText("GO");
    }

    public void gotothelink(View view) {
        Uri adress= Uri.parse(GlobalClass.UrlLink);
        Intent browser= new Intent(Intent.ACTION_VIEW, adress);
        startActivity(browser);
    }
}
