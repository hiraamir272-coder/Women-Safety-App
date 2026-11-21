package com.example.womensafetyapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.womensafetyapp.R;

public class MainActivity extends AppCompatActivity {

    EditText nameBox, passwordBox;
    CheckBox rememberBox;
    Button loginButton;
    TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameBox = findViewById(R.id.nameBox);
        passwordBox = findViewById(R.id.passwordBox);
        rememberBox = findViewById(R.id.rememberBox);
        loginButton = findViewById(R.id.loginButton);
        messageText = findViewById(R.id.messageText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameBox.getText().toString();
                String password = passwordBox.getText().toString();

                // 1. Agar khali chora to
                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
                // 2. Agar sahi name aur password ho
                else if (name.equals("HiraAmir") && password.equals("272272")) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    // Neeche wala decent message
                    messageText.setText("Welcome to Women Safety App\nYou are safe, we are with you.");

                    // Agar Remember me pe tick hai to
                    if (rememberBox.isChecked()) {
                        Toast.makeText(MainActivity.this, "Remembered", Toast.LENGTH_SHORT).show();
                    }
                }
                // 3. Agar ghalat likha to
                else {
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}