package com.michalrubajczyk.myfirebrigade.activity.FireBrigadeActivity;


import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FireBrigadeRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.auth.AuthUserInfo;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;
import com.michalrubajczyk.myfirebrigade.utils.AuthUserUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

/**
 * Created by Michal on 23/03/2018.
 */

public class FireBrigadePresenter implements FireBrigadeContract.Presenter {

    private final FireBrigadeRequestImpl mFireBrigadeRequests;

    private final FireBrigadeContract.View mFireBrigadeView;

    private final AuthUserUtils mUser;

    private final FireBrigadeUtils mFireBrigadeUtils;

    private boolean mFirstLoad = true;

    public FireBrigadePresenter(FireBrigadeRequestImpl fireBrigadeRequest, AuthUserUtils authUserUtils, FireBrigadeUtils fireBrigadeUtils, FireBrigadeContract.View fireBrigadeView) {
        mFireBrigadeRequests = fireBrigadeRequest;
        mFireBrigadeView = fireBrigadeView;
        mFireBrigadeUtils = fireBrigadeUtils;
        mUser = authUserUtils;
        mFireBrigadeView.setPresenter(this);
    }


    @Override
    public void start() {
        loadFireBrigade(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadFireBrigade(boolean forceUpdate) {
        mFireBrigadeView.showLoadingSpinner(true);
        mFireBrigadeRequests.getFireBrigadeByUsername(mUser.getUsernameFromSharedPreferences(), new DataListener() {
            @Override
            public void onSuccess(String data) {
                FireBrigadeDTO firebrigade = makeFirebrigadeFromResponse(data);
                mFireBrigadeUtils.addFireBrigadeIdToSharedPreferences(firebrigade.getIdFireBrigade());
                mFireBrigadeView.showFireBrigade(firebrigade);
                mFireBrigadeView.hideLoadingSpinner(true);
            }

            @Override
            public void onError(int code) {
                if (code == 500) {
                    mFireBrigadeView.showNoFireBrigade();
                    mFireBrigadeView.hideLoadingSpinner(true);
                } else {
                    mFireBrigadeView.showNoFireBrigade();
                    mFireBrigadeView.hideLoadingSpinner(true);
                }
            }
        });

    }

    private FireBrigadeDTO makeFirebrigadeFromResponse(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, FireBrigadeDTO.class);
    }

    @Override
    public void addFireBrigade() {

    }

    @Override
    public void logOut() {
        mUser.clearUserDataSharedPreferences();
        mFireBrigadeUtils.clearFireBrigadeIdSharedPreferences();
        mFireBrigadeView.showLogin();
    }

}


