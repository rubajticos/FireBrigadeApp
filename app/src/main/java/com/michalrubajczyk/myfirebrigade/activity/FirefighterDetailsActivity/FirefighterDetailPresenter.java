package com.michalrubajczyk.myfirebrigade.activity.FirefighterDetailsActivity;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;

import java.text.SimpleDateFormat;

public class FirefighterDetailPresenter implements FirefighterDetailsContract.PresenterDetails {

    private final FirefighterRequestImpl mFirefighterRequest;

    private final FirefighterDetailsContract.ViewDetails mFirefighterDetailsView;

    @Nullable
    private Integer mFirefighterId;

    public FirefighterDetailPresenter(FirefighterRequestImpl mFirefighterRequest, FirefighterDetailsContract.ViewDetails mFirefighterDetailsView, Integer mFirefighterId) {
        this.mFirefighterRequest = mFirefighterRequest;
        this.mFirefighterDetailsView = mFirefighterDetailsView;
        this.mFirefighterId = mFirefighterId;

        mFirefighterDetailsView.setPresenter(this);
    }

    @Override
    public void start() {
        openFirefighter();
    }

    private void openFirefighter() {
        if (mFirefighterId == null) {
            mFirefighterDetailsView.showMissingFirefighter();
            return;
        }

        mFirefighterDetailsView.setLoadingIndicator(true);
        mFirefighterRequest.getFirefighterById(mFirefighterId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                Firefighter firefighter = makeFirefighterFromResponse(data);
                showFirefighter(firefighter);
                mFirefighterDetailsView.hideLoadingIndicator();
            }

            private Firefighter makeFirefighterFromResponse(String data) {
                Gson gson = new Gson();
                Firefighter firefighter = gson.fromJson(data, Firefighter.class);
                return firefighter;
            }

            @Override
            public void onError(int code) {
                mFirefighterDetailsView.showMissingFirefighter();
                mFirefighterDetailsView.hideLoadingIndicator();
            }
        });
    }

    private void showFirefighter(Firefighter firefighter) {
        String name = firefighter.getName();
        String lastName = firefighter.getLastName();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String birthdayDate = dateFormat.format(firefighter.getBirthday());
        String expiryMedicalTest = dateFormat.format(firefighter.getExpiryMedicalTest());

        mFirefighterDetailsView.setFirefighterId(Integer.toString(firefighter.getIdFirefighter()));
        mFirefighterDetailsView.showName(name);
        mFirefighterDetailsView.showLastName(lastName);
        mFirefighterDetailsView.showBirthday(birthdayDate);
        mFirefighterDetailsView.showExpiryMedicalTest(expiryMedicalTest);
    }


    @Override
    public void editFirefighter() {

    }

    @Override
    public void deleteFirefighter() {

    }

}
