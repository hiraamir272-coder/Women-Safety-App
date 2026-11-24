package com.example.womensafetyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    EditText etEmailPhone, etPassword;
    Button btnLogin;
    CheckBox cbRememberMe;
    TextView tvForgetPassword;

    // Tumhara Fixed Data
    String myEmail = "hiraamir272@gmail.com";
    String myPhone = "03234101385";
    String myPassword = "001199";

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etEmailPhone = findViewById(R.id.etEmailPhone);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnlogin);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);

        prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);

        // Agar pehle Remember Me tick tha to auto fill
        if (prefs.getBoolean("remember", false)) {
            etEmailPhone.setText(prefs.getString("email", ""));
            etPassword.setText(prefs.getString("pass", ""));
            cbRememberMe.setChecked(true);
        }

        // LOGIN BUTTON
        btnLogin.setOnClickListener(v -> {
            String input = etEmailPhone.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (input.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if ((input.equals(myEmail) || input.equals(myPhone)) && pass.equals(myPassword)) {

                // Remember Me Save
                if (cbRememberMe.isChecked()) {
                    prefs.edit().putBoolean("remember", true)
                            .putString("email", input)
                            .putString("pass", pass).apply();
                } else {
                    prefs.edit().clear().apply();
                }

                // BOLD me Login Successful
                Toast.makeText(this, Html.fromHtml("<b>Login Successful</b>"), Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // FORGET PASSWORD
        tvForgetPassword.setOnClickListener(v -> showForgetDialog());
    }

    private void showForgetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forget Password");

        // Custom Layout Dialog ke liye
        final EditText etRegisterEmail = new EditText(this);
        etRegisterEmail.setHint("Register Email");
        etRegisterEmail.setPadding(40, 30, 40, 30);
        builder.setView(etRegisterEmail);

        builder.setPositiveButton("Send Reset Link", (dialog, which) -> {
            String regEmail = etRegisterEmail.getText().toString().trim();
            if (regEmail.equals(myEmail)) {
                Toast.makeText(this, Html.fromHtml("<b>Login Successful</b>"), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Email Not Registered", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}