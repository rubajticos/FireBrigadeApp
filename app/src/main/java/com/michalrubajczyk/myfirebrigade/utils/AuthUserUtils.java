package com.michalrubajczyk.myfirebrigade.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.auth.AuthUserInfo;

import org.json.JSONObject;

/**
 * Created by Michal on 20/03/2018.
 */

public class AuthUserUtils {

    Context mContext;

    public AuthUserUtils(Context context) {
        this.mContext = context;
    }

    public AuthUserInfo createAuthUserInfoFromResponse(JSONObject response) {
        return mapJSONToAuthObject(response);
    }

    private AuthUserInfo mapJSONToAuthObject(JSONObject response) {
        Gson gson = new Gson();
        String jsonString = response.toString();
        AuthUserInfo authUserInfo = gson.fromJson(jsonString, AuthUserInfo.class);
        return authUserInfo;
    }

    public void addAuthUserInfoToSharedPreferences(AuthUserInfo authUserInfo, String login) {
        AuthUserInfo userInfo = authUserInfo;
        userInfo.setUsername(login);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", userInfo.getUsername());
        editor.putString("access_token", userInfo.getAccess_token());
        editor.putString("refresh_token", userInfo.getRefresh_token());
        editor.commit();
    }

    public String getUsernameFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getString("username", "no username");
    }

    public String getAccessTokenFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getString("access_token", "no access_token");
    }

    public String getRefreshTokenFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getString("refresh_token", "no refresh_token");
    }


}
