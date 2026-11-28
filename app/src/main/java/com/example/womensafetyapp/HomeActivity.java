package com.example.womensafetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import android.app.AlertDialog;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    // Saare cards ko yahan declare karenge
    LinearLayout cardLiveLocation, cardSafePlace,cardFakeCall, cardTrustedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. XML wali ID ko Java se jorna - YE SAB SE ZAROORI HAI
        cardLiveLocation = findViewById(R.id.cardLiveLocation);
        cardSafePlace = findViewById(R.id.cardSafePlace);
        cardSafePlace.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SafePlaceAtivity.class);
            startActivity(intent);
        });
        cardFakeCall = findViewById(R.id.cardFakeCalls);
        cardTrustedContact=findViewById(R.id.cardTrustedContact);
        cardTrustedContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTrustedPeopleDialog();
            }
        });

        // 2. LIVE LOCATION ka click - Ye Map wali screen kholega
        // 2. LIVE LOCATION ka click - Ye Dialog Box kholega
        cardLiveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLiveLocation();
            }
        });

        // 3. Baqi 3 features ka code yahan likhenge - ABHI KHALI CHOR DO
        // cardSafeZones ka click yahan aayega
        // cardFakeCall ka click yahan aayega
        // cardTrustedContact ka click yahan aayega

    } // onCreate yahan khatam hota hai

    // ===== LIVE LOCATION KA POORA KAAM YAHAN SE SHURU =====

    private void getLiveLocation() {
        // Pehle permission check karenge
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                showLocationDialog(latitude, longitude);
            } else {
                Toast.makeText(this, "Location nahi mili, GPS On karo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLocationDialog(double latitude, double longitude) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your Live Location");
        builder.setMessage("Latitude: " + latitude + "\nLongitude: " + longitude);

        // 1. SHARE LOCATION wala button
        builder.setPositiveButton("Share Location", (dialog, which) -> {
            Toast.makeText(this, "Location Shared", Toast.LENGTH_SHORT).show();
            // Toast 2 second ka hi hota hai, ye khud band ho jayega
        });

        // 2. CANCEL wala button
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss(); // Dialog band ho jayega, user wapis Home pe aa jayega
        });

        builder.show();
    }

    // Permission milne ke baad dobara location lene ke liye
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLiveLocation();
        } else {
            Toast.makeText(this, "Permission chahiye location ke liye", Toast.LENGTH_SHORT).show();
        }
    }
        private void showTrustedPeopleDialog() {
            String[] names = {"Mother", "Father", "Brother", "Friend"};
            int[] selected = {-1};

            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("First Trusted Contact");

            builder.setSingleChoiceItems(names, -1, (dialog, which) -> {
                selected[0] = which;
            });

            builder.setPositiveButton("Emergency Call", (dialog, which) -> {
                if (selected[0] == -1) {
                    Toast.makeText(this, "Please select a contact first", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Call Forwarding to " + names[selected[0]], Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
            });

            builder.show();
        }
    }
