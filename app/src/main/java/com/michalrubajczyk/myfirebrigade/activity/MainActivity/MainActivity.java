package com.michalrubajczyk.myfirebrigade.activity.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.activity.FireBrigadeActivity.FireBrigadeActivity;
import com.michalrubajczyk.myfirebrigade.activity.LoginActivity.LoginActivity;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;
import com.michalrubajczyk.myfirebrigade.utils.AuthUserUtils;
import com.michalrubajczyk.myfirebrigade.utils.SSLAccept;

public class MainActivity extends Activity {
    private SSLAccept ssl = new SSLAccept();


    Handler mHandler = new Handler();
    AuthUserUtils mUserUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ResourcesSingleton.init(getApplicationContext()); //init ResourcesSingleton
        mUserUtils = new AuthUserUtils(getApplicationContext());
        ssl.handleSSLHandshake(); //accept any SSL certifiacte
        mHandler.postDelayed(mLaunchLoginActivity, 2000);

    }

    private Runnable mLaunchLoginActivity = new Runnable() {
        @Override
        public void run() {
            Log.d("shared username", mUserUtils.getUsernameFromSharedPreferences());
            if (mUserUtils.getUsernameFromSharedPreferences().length() == 0) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(getApplicationContext(), FireBrigadeActivity.class);
                startActivity(intent);
                finish();
            }


        }
    };


}
