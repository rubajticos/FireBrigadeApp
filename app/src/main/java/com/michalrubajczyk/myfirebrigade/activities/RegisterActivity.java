package com.michalrubajczyk.myfirebrigade.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.michalrubajczyk.myfirebrigade.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private EditText rePassword;
    private Button registerButton;
    private TextView tempTv;


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
        tempTv = findViewById(R.id.temp_textview);
        registerButton = findViewById(R.id.registeR_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://192.168.1.114:8443";

                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET,
                        url,
                        response -> tempTv.setText(response),
                        error -> tempTv.setText(error.toString())
                );

                requestQueue.add(stringRequest);
            }
        });
    }

}
