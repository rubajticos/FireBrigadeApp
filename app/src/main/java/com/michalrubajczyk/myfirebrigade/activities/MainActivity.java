package com.michalrubajczyk.myfirebrigade.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.michalrubajczyk.myfirebrigade.R;

public class MainActivity extends AppCompatActivity {
    private final int INTERNET_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        askPermission(Manifest.permission.INTERNET, INTERNET_REQUEST_CODE);

        /* TEST CONNECTION */
        ConnectionWithServerTask m = new ConnectionWithServerTask();
        m.execute("message");
        Toast.makeText(getApplicationContext(), "Wyslano", Toast.LENGTH_LONG).show();

    }

    private void askPermission(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            //We don't
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        } else {
            //We have
            Toast.makeText(this, "Internet permission granted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case INTERNET_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Internet permission granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Internet permission denied", Toast.LENGTH_LONG).show();

                }
        }
    }
}
