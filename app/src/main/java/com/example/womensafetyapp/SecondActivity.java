package com.example.womensafetyapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView welcomeText = findViewById(R.id.welcomeText);
        TextView userNameText = findViewById(R.id.userNameText);
        TextView safeMsg = findViewById(R.id.safeMsg);

        String name = getIntent().getStringExtra("USER_NAME");

        welcomeText.setText("Welcome to Women Safety App");
        userNameText.setText("Welcome, " + name + " ❤️");
        safeMsg.setText("You are safe here. Your safety is our priority.");
    }
}