package com.example.fapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Choice extends AppCompatActivity {

    private Button btnLogout;
    // private TextView valueName;
    // DBHelper dbHelper;

    private CardView cvRuangTopik;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        // btnLogin = findViewById(R.id.btnLogin);
        btnLogout = findViewById(R.id.btnLogout);
        // valueName = findViewById(R.id.valueName);
        cvRuangTopik = findViewById(R.id.cvRuangTopik);

        // dbHelper = new DBHelper(this);
        sessionManager = new SessionManager(this);

        if (!sessionManager.loggedIn()) {
            logout();
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        cvRuangTopik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Choice.this, EditProfil.class);
                startActivity(i);
                finish();
            }
        });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Choice.this, Login.class);
//                startActivity(i);
//                finish();
//            }
//        });

        // valueName.setText(dbHelper.checkUser(DBHelper.row_email));

//        btnLogout.setOnClickListener(v -> {
////            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(getApplicationContext(), Login.class));
//        });

    }

    private void logout() {
        sessionManager.setLoggedIn(false);
        finish();
        startActivity(new Intent(Choice.this, Login.class));
    }
}