package com.michalrubajczyk.myfirebrigade.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FireBrigadeRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;
import com.michalrubajczyk.myfirebrigade.model.errors.HttpErrors;
import com.michalrubajczyk.myfirebrigade.utils.AuthUserUtils;
import com.michalrubajczyk.myfirebrigade.view.FireBrigadeActivityView;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Michal on 23/03/2018.
 */

public class FireBrigadePresenterImpl implements FireBrigadePresenter, HttpErrors {
    private final String TAG = "FireBrigadeMainPsnt";

    FireBrigadeActivityView mFireBrigadeActivityView;
    FireBrigadeRequestImpl mFireBrigadeRequest;
    AuthUserUtils mAuthUserUtils;

    public FireBrigadePresenterImpl(FireBrigadeActivityView fireBrigadeActivityView, Context context) {
        this.mFireBrigadeActivityView = fireBrigadeActivityView;
        this.mFireBrigadeRequest = new FireBrigadeRequestImpl(context);
        this.mAuthUserUtils = new AuthUserUtils(context);
    }

    @Override
    public void loadFireBrigadeByUsername() {
        String username = getUsername();
        mFireBrigadeRequest.getFireBrigadeByUsername(username, new DataListener() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "success response: " + data);
                FireBrigadeDTO fireBrigadeDTO = parseDataToObject(data);
                mFireBrigadeActivityView.setFireBrigade(fireBrigadeDTO);
                mFireBrigadeActivityView.setFireBrigadeFragment();
                mFireBrigadeActivityView.prepareAndShowFireBrigadeData();
            }

            @Override
            public void onError(int code) {
                errorResponseCodeSupport(code);
                Log.d(TAG, "request error");
            }
        });
    }

    private String getUsername() {
        return mAuthUserUtils.getUsernameFromSharedPreferences();
    }

    private FireBrigadeDTO parseDataToObject(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, FireBrigadeDTO.class);
    }

    @Override
    public void errorResponseCodeSupport(Integer code) {
        switch (code) {
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                Log.d(TAG, "error - no firebrigade for this username");
                mFireBrigadeActivityView.setEmptyFragment();
        }
    }

    @Override
    public void createFireBrigadeToUser(FireBrigadeDTO firebrigade) {
        mFireBrigadeActivityView.showCreatingLoading();
        mFireBrigadeRequest.addFireBrigadeToUser(firebrigade, mAuthUserUtils.getUsernameFromSharedPreferences(), new DataListener() {
            @Override
            public void onSuccess(String data) {
                mFireBrigadeActivityView.dismissCreatingLoading();
                mFireBrigadeActivityView.callCreatingSuccess();
            }

            @Override
            public void onError(int code) {
                mFireBrigadeActivityView.dismissCreatingLoading();
                mFireBrigadeActivityView.callCreatingFailure();
            }
        });
    }

    @Override
    public boolean validateFireBrigade(FireBrigadeDTO fireBrigadeDTO) {
        if (validate(fireBrigadeDTO)) {
            return true;
        }

        return false;
    }

    private boolean validate(FireBrigadeDTO fireBrigadeDTO) {
        ArrayList<String> values = new ArrayList<>();
        values.add(fireBrigadeDTO.getCity());
        values.add(fireBrigadeDTO.getCommunity());
        values.add(fireBrigadeDTO.getDistrict());
        values.add(fireBrigadeDTO.getName());
        values.add(fireBrigadeDTO.getVoivodeship());

        for (String o : values) {
            if (TextUtils.isEmpty(o)) return false;
        }

        return true;

    }
}
