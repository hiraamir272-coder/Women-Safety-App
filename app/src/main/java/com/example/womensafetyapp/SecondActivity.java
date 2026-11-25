package com.example.womensafetyapp;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    EditText etEmailPhone, etPassword;
    CheckBox cbRememberMe;
    Button btnLogin;
    TextView tvForgetPassword;

    String myEmail = "hiraamir272@gmail.com";
    String myPhone = "03234101385";
    String myPass = "001199";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etEmailPhone = findViewById(R.id.etEmailPhone);
        etPassword = findViewById(R.id.etPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        btnLogin= findViewById(R.id.btnlogin);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);

        SharedPreferences prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);

        // Agar Remember Me pehle tick tha to data wapis bhar do
        if (prefs.getBoolean("remember", false)) {
            etEmailPhone.setText(prefs.getString("email", ""));
            etPassword.setText(prefs.getString("password", ""));
            cbRememberMe.setChecked(true);
        }

        // LOGIN BUTTON
        btnLogin.setOnClickListener(v -> {
            String input = etEmailPhone.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if ((input.equals(myEmail) || input.equals(myPhone)) && pass.equals(myPass)) {

                // Remember Me save karo
                SharedPreferences.Editor editor = prefs.edit();
                if (cbRememberMe.isChecked()) {
                    editor.putString("email", input);
                    editor.putString("password", pass);
                    editor.putBoolean("remember", true);
                } else {
                    editor.clear();
                }
                editor.apply();

                // BOLD me Login Successful
                Toast.makeText(this, Html.fromHtml("<b>Login Successful</b>"), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // FORGET PASSWORD
        tvForgetPassword.setOnClickListener(v -> showForgetDialog());
    }

    private void showForgetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forget Password");

        // 1st Line - Register Email
        EditText etRegisterEmail = new EditText(this);
        etRegisterEmail.setHint("Register Email");
        etRegisterEmail.setPadding(40, 30, 40, 30);
        builder.setView(etRegisterEmail);

        // 2nd Line - Send Reset link Button
        builder.setPositiveButton("Send Reset link", (dialog, which) -> {
            String regEmail = etRegisterEmail.getText().toString().trim();
            if (regEmail.equals(myEmail)) {
                Toast.makeText(this, Html.fromHtml("<b>Login Successful</b>"), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // 3rd Line - Cancel Button
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}