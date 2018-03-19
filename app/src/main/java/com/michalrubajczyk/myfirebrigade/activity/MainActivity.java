package com.michalrubajczyk.myfirebrigade.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.michalrubajczyk.myfirebrigade.R;

public class MainActivity extends Activity {

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler.postDelayed(mLaunchLoginActivity, 5000);
    }

    private Runnable mLaunchLoginActivity = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    };


}
