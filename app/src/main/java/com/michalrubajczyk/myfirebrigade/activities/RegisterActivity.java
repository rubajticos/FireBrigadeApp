package com.michalrubajczyk.myfirebrigade.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.FireBrigade;
import com.michalrubajczyk.myfirebrigade.model.Ksrg;
import com.michalrubajczyk.myfirebrigade.model.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private final int INTERNET_REQUEST_CODE = 2;

    private TextInputEditText firebrigadeName;
    private TextInputEditText firebrigadeCity;
    private TextInputEditText firebrigadeCommunity;
    private TextInputEditText firebrigadeDistrict;
    private TextInputEditText firebrigadeVoivodeship;
    private TextInputEditText username;
    private TextInputEditText password;
    private Spinner firebrigadeKsrgSpinner;
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

        firebrigadeKsrgSpinner = (Spinner) findViewById(R.id.register_ksrg_spinner);
        ksrgSpinnerSetData();

        formRegister = findViewById(R.id.form_register);
        registerBtn = (Button) findViewById(R.id.register_button);
        username = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);

        askPermission(Manifest.permission.INTERNET, INTERNET_REQUEST_CODE);

        registerBtn.setOnClickListener(view -> {
            RegisterServerTask r = new RegisterServerTask(this);
            String message = createRegisterJson(createUserFromForm(), createFirebrigadeFromForm());
            Log.d("registerJSON: ", message);
            Toast.makeText(this, "Rejestracja!", Toast.LENGTH_SHORT).show();
            r.execute(message);
        });


    }

    private void ksrgSpinnerSetData() {
        ArrayList<Ksrg> ksrgValuesList = new ArrayList<>();

        ksrgValuesList.add(new Ksrg(1, "Czlonek KSRG"));
        ksrgValuesList.add(new Ksrg(0, "Nie nalezy do KSRG"));
        ksrgValuesList.add(new Ksrg(-1, "Czy jednostka nalezy do KSRG?"));

        ArrayAdapter<Ksrg> adapter = new ArrayAdapter<Ksrg>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, ksrgValuesList);
        firebrigadeKsrgSpinner.setAdapter(adapter);
        firebrigadeKsrgSpinner.setSelection(adapter.getPosition(ksrgValuesList.get(2)));
    }

    private User createUserFromForm() {
        return new User(username.getText().toString(), password.getText().toString());
    }

    private FireBrigade createFirebrigadeFromForm() {
        FireBrigade fireBrigade = new FireBrigade();
        fireBrigade.setName(firebrigadeName.getText().toString());
        fireBrigade.setCity(firebrigadeCity.getText().toString());
        fireBrigade.setCommunity(firebrigadeCommunity.getText().toString());
        fireBrigade.setDistrict(firebrigadeDistrict.getText().toString());
        fireBrigade.setVoivodeship(firebrigadeVoivodeship.getText().toString());
        if (isGoodItemSelected()) {
            Ksrg ksrg = (Ksrg) firebrigadeKsrgSpinner.getSelectedItem();
            fireBrigade.setKsrg(ksrg.getId());
        }
        return fireBrigade;
    }

    private boolean isGoodItemSelected() {
        Ksrg ksrg = (Ksrg) firebrigadeKsrgSpinner.getSelectedItem();
        if (ksrg.getId() == 0 || ksrg.getId() == 1) {
            return true;
        }

        return false;
    }

    private String createRegisterJson(User user, FireBrigade fireBrigade) {
        Gson gson = new Gson();
        String message = "register\n";
        String userJson = gson.toJson(user) + "\n";
        String firebrigadeJson = gson.toJson(fireBrigade);

        return message + userJson + firebrigadeJson;
    }


    private void askPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            //We don't
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        } else {
            //We have
            Toast.makeText(this, "Internet permission granted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case INTERNET_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Internet permission granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Internet permission denied", Toast.LENGTH_LONG).show();

                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
