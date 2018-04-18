package com.michalrubajczyk.myfirebrigade.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.michalrubajczyk.myfirebrigade.model.auth.AuthUserInfo;

public class FireBrigadeUtils {

    private Context mContext;

    public FireBrigadeUtils(Context context) {
        this.mContext = context;
    }

    public void addFireBrigadeIdToSharedPreferences(int firebrigadeId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("FIREBRIGADE_ID", firebrigadeId);
        editor.commit();
    }

    public int getFireBrigadeIdFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getInt("FIREBRIGADE_ID", -1);
    }

    public void clearFireBrigadeIdSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("FIREBRIGADE_ID");
        editor.commit();
    }
}
