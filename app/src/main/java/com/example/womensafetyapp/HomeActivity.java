package com.example.womensafetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    // Saare cards ko yahan declare karenge
    LinearLayout cardLiveLocation, cardSafeZones,  cardFakeCall, cardTrustedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. XML wali ID ko Java se jorna - YE SAB SE ZAROORI HAI
        cardLiveLocation = findViewById(R.id.cardLiveLocation);
        cardSafeZones = findViewById(R.id.cardSafePlace);
        cardFakeCall = findViewById(R.id.cardFakeCalls);
        cardTrustedContact=findViewById(R.id.cardTrustedContact);

        // 2. LIVE LOCATION ka click - Ye Map wali screen kholega
