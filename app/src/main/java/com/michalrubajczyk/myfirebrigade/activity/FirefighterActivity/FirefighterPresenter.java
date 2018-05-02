package com.michalrubajczyk.myfirebrigade.activity.FirefighterActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.util.Arrays;
import java.util.List;

public class FirefighterPresenter implements FirefighterContract.Presenter {

    private final FirefighterRequestImpl mFirefighterRequest;

    private final FirefighterContract.View mFirefighterView;

    private final FireBrigadeUtils mFireBrigadeUtils;

    private final int mFireBrigadeId;

    private boolean mFirstLoad = true;

    public FirefighterPresenter(FireBrigadeUtils fireBrigadeUtils, FirefighterRequestImpl mFirefighterRequest, FirefighterContract.View mFirefighterView) {
        this.mFireBrigadeUtils = fireBrigadeUtils;
        this.mFireBrigadeId = mFireBrigadeUtils.getFireBrigadeIdFromSharedPreferences();
        this.mFirefighterRequest = mFirefighterRequest;
        this.mFirefighterView = mFirefighterView;
        this.mFirefighterView.setPresenter(this);
    }

    @Override
    public void start() {
        loadFirefighters(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadFirefighters(boolean forceUpdade) {
        mFirefighterView.setLoadingIndicator(true);
        mFirefighterRequest.getFirefightersByFirebrigadeId(mFireBrigadeId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                List<Firefighter> firefighters = makeFirefighterListFromResponse(data);
                mFirefighterView.showFirefighters(firefighters);
                mFirefighterView.hideLoadingIndicator();
            }

            private List<Firefighter> makeFirefighterListFromResponse(String data) {
                Gson gson = new Gson();
                Firefighter[] firefightersArray = gson.fromJson(data, Firefighter[].class);
                return Arrays.asList(firefightersArray);
            }

            @Override
            public void onError(int code) {
                if (code == 500) {
                    mFirefighterView.showNoFirefighters();
                    mFirefighterView.hideLoadingIndicator();
                    mFirefighterView.showLoadingFirefightersError();
                } else {
                    mFirefighterView.showNoFirefighters();
                    mFirefighterView.hideLoadingIndicator();
                }
            }
        });
    }

    @Override
    public void addNewFirefighter() {

    }

    @Override
    public void deleteFirefighter() {

    }

    @Override
    public void openFirefighterDetails(Integer requestedFirefighterId) {
        mFirefighterView.showFirefighterDetailUi(requestedFirefighterId);
    }


}
