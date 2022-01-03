package com.example.fapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnMasuk, btnDaftar;
    private ProgressBar pbLogin;
    private DBHelper dbHelper;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnMasuk = findViewById(R.id.btnMasuk);
        btnDaftar = findViewById(R.id.btnDaftar);
        pbLogin = findViewById(R.id.pbLogin);

        dbHelper = new DBHelper(this);
        sessionManager = new SessionManager(this);

        if (sessionManager.loggedIn()) {
            startActivity(new Intent(Login.this, Choice.class));
        }

//        btnDaftar.setOnClickListener(v -> {
//            startActivity(new Intent(getApplicationContext(), Daftar.class));
//        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Daftar.class);
                startActivity(i);
                finish();
            }
        });

//        btnMasuk.setOnClickListener(v -> {
//            if (etEmail.getText().length() > 0 && etPassword.getText().length() > 0) {
//                login(etEmail.getText().toString(), etPassword.getText().toString());
//            } else {
//                Toast.makeText(getApplicationContext(), "Silakan isi semua data", Toast.LENGTH_SHORT).show();
//            }
//        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();


                Boolean res = dbHelper.checkUser(email, password);

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Email dan Password harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    if (res == true) {
                        sessionManager.setLoggedIn(true);
                        Toast.makeText(Login.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Choice.class));
                    } else {
                        Toast.makeText(Login.this, "Login Gagal!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

    private void login() {

    }
}