package com.michalrubajczyk.myfirebrigade.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.michalrubajczyk.myfirebrigade.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private EditText rePassword;
    private Button registerButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.repassword);
        registerButton = findViewById(R.id.registeR_button);
    }

}
