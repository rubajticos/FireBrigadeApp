package com.michalrubajczyk.myfirebrigade.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FireBrigadeRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;
import com.michalrubajczyk.myfirebrigade.model.errors.HttpErrors;
import com.michalrubajczyk.myfirebrigade.utils.AuthUserUtils;
import com.michalrubajczyk.myfirebrigade.view.FireBrigadeMainView;

/**
 * Created by Michal on 23/03/2018.
 */

public class FireBrigadeMainPresenterImpl implements FireBrigadeMainPresenter, FireBrigadeWithDataPresenter, HttpErrors {
    private final String TAG = "FireBrigadeMainPsnt";

    FireBrigadeMainView mFireBrigadeMainView;
    FireBrigadeRequestImpl mFireBrigadeRequest;
    AuthUserUtils mAuthUserUtils;

    public FireBrigadeMainPresenterImpl(FireBrigadeMainView fireBrigadeMainView, Context context) {
        this.mFireBrigadeMainView = fireBrigadeMainView;
        this.mFireBrigadeRequest = new FireBrigadeRequestImpl(context);
        this.mAuthUserUtils = new AuthUserUtils(context);
    }

    @Override
    public void getFireBrigadeByUsername() {
        String username = mAuthUserUtils.getUsernameFromSharedPreferences();
        mFireBrigadeRequest.getFireBrigadeByUsername(username, new DataListener() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "success response: " + data);
                FireBrigadeDTO fireBrigadeDTO = parseDataToObject(data);
                mFireBrigadeMainView.setFragmentWithFirebrigade(fireBrigadeDTO);
            }

            @Override
            public void onError(int code) {
                errorResponseCodeSupport(code);
                Log.d(TAG, "request error");
            }
        });
    }

    private FireBrigadeDTO parseDataToObject(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, FireBrigadeDTO.class);
    }

    @Override
    public void errorResponseCodeSupport(Integer code) {
        switch (code) {
            case 500:
                Log.d(TAG, "error - no firebrigade for this username");
                mFireBrigadeMainView.setFragmentNoFirebrigade();
        }
    }

    @Override
    public void performFireBrigadeToShow(FireBrigadeDTO fireBrigadeDTO) {

    }
}
