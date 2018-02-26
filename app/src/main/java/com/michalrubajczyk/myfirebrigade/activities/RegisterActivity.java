package com.michalrubajczyk.myfirebrigade.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.michalrubajczyk.myfirebrigade.R;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText firebrigadeName;
    private TextInputEditText firebrigadeCity;
    private TextInputEditText firebrigadeCommunity;
    private TextInputEditText firebrigadeDistrict;
    private TextInputEditText firebrigadeVoivodeship;
    private TextInputEditText username;
    private TextInputEditText password;
    private Spinner firebrigadeKsrg;
    private Button registerBtn;
    private View formRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebrigadeName = (TextInputEditText) findViewById(R.id.register_name);
        firebrigadeCity = (TextInputEditText) findViewById(R.id.register_city);
        firebrigadeCommunity = (TextInputEditText) findViewById(R.id.register_community);
        firebrigadeDistrict = (TextInputEditText) findViewById(R.id.register_district);
        firebrigadeVoivodeship = (TextInputEditText) findViewById(R.id.register_voivodeship);

        firebrigadeKsrg = (Spinner) findViewById(R.id.register_ksrg_spinner);
        String[] ksrgArray = new String[]{"Nalezy", "Nie nalezy"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ksrgArray);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        firebrigadeKsrg.setAdapter(adapter);

        formRegister = findViewById(R.id.form_register);
        registerBtn = (Button) findViewById(R.id.register_button);
        username = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);

        registerBtn.setOnClickListener((view) -> {
            Toast.makeText(this, "Rejestracja!", Toast.LENGTH_SHORT).show();
        });


    }

}
