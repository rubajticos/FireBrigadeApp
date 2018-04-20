package com.michalrubajczyk.myfirebrigade.activity.FirefighterDetailsActivity;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;

import java.util.Arrays;
import java.util.List;

public class FirefighterDetailsTrainingPresenter implements FirefighterDetailsContract.PresenterDetailsTraining {
    private final FirefighterRequestImpl mFirefighterRequest;

    private final FirefighterDetailsContract.ViewDetailsTraining mFirefighterTrainingView;

    private final Integer mFirefighterId;

    public FirefighterDetailsTrainingPresenter(FirefighterRequestImpl mFirefighterRequest, FirefighterDetailsContract.ViewDetailsTraining mFirefighterTrainingView, Integer mFirefighterId) {
        this.mFirefighterRequest = mFirefighterRequest;
        this.mFirefighterTrainingView = mFirefighterTrainingView;
        this.mFirefighterId = mFirefighterId;

        mFirefighterTrainingView.setPresenter(this);
    }

    @Override
    public void start() {
        loadFirefighterTrainings(false);
    }

    @Override
    public void loadFirefighterTrainings(boolean forceUpdate) {
        mFirefighterTrainingView.setLoadingIndicator(true);
        mFirefighterRequest.getFirefighterTrainings(mFirefighterId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                List<FirefighterTraining> trainings = makeFirefighterTrainingsFromResponse(data);
                mFirefighterTrainingView.showTrainings(trainings);
                mFirefighterTrainingView.hideLoadingIndicator();
            }

            private List<FirefighterTraining> makeFirefighterTrainingsFromResponse(String data) {
                Gson gson = new Gson();
                FirefighterTraining[] firefighterTrainingsArray = gson.fromJson(data, FirefighterTraining[].class);
                return Arrays.asList(firefighterTrainingsArray);
            }

            @Override
            public void onError(int code) {
                if (code == 500) {
                    mFirefighterTrainingView.showNoTrainings();
                    mFirefighterTrainingView.hideLoadingIndicator();
                    mFirefighterTrainingView.showLoadingFirefightersError();
                } else {
                    mFirefighterTrainingView.showNoTrainings();
                    mFirefighterTrainingView.hideLoadingIndicator();
                }
            }
        });
    }


}
