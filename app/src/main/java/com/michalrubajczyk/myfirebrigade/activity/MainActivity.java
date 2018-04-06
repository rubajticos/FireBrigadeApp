package com.michalrubajczyk.myfirebrigade.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;

public class MainActivity extends AppCompatActivity {

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResourcesSingleton.init(getApplicationContext()); //init ResourcesSingleton
        mHandler.postDelayed(mLaunchLoginActivity, 3000);
    }

    private Runnable mLaunchLoginActivity = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    };


}
