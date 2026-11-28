package com.example.womensafetyapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SafePlaceAtivity extends AppCompatActivity {
    LinearLayout cardPolice, cardHospital, cardHelpCenter, cardPublic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_place);

        // Back Arrow
        TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        TextView btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> finish());

        // Cards
        cardPolice = findViewById(R.id.cardPolice);
        cardHospital = findViewById(R.id.cardHospital);
        cardHelpCenter = findViewById(R.id.cardHelpCenter);
        cardPublic = findViewById(R.id.cardPublic);

        // 1. Police Station Click
        cardPolice.setOnClickListener(v -> {
            showPermissionDialog("Police Station", "7 mins", "15");
        });

        // 2. Hospital Click
        cardHospital.setOnClickListener(v -> {
            showPermissionDialog("Hospital", "10 mins", "1122");
        });

        // 3. Women Help Center Click
        cardHelpCenter.setOnClickListener(v -> {
            showPermissionDialog("Women Help Center", "5 mins", "1099");
        });

        // 4. Public Safe Places Click
        cardPublic.setOnClickListener(v -> {
            showPermissionDialog("Public Safe Place", "3 mins", "15");
        });
    }

    // ===== 1st Dialogue Box : Permission =====
    private void showPermissionDialog(String placeName, String distance, String emergencyNo) {
        new AlertDialog.Builder(this)
                .setTitle("Location Permission")
                .setMessage("You like to share your Location?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    // Yes pe pehla band, dusra khul jayega
                    showDetailsDialog(placeName, distance, emergencyNo);
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                    // No pe wapis Safe Place wali activity pe hi rahega
                })
                .show();
    }

    // ===== 2nd Dialogue Box : Details =====
    private void showDetailsDialog(String placeName, String distance, String emergencyNo) {
        String message =
                "1. " + placeName + "\n\n" +
                        "2. You can see location on Google Map\n\n" +
                        "3. Distance: " + distance + "\n\n" +
                        "4. Emergency Number: " + emergencyNo;

        new AlertDialog.Builder(this)
                .setTitle(placeName)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}