package com.michalrubajczyk.myfirebrigade.activity.StatisticsActivity;

import android.support.annotation.Nullable;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.StatisticsRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.Statistics;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class StatisticsPresenter implements StatisticsContract.PresenterDetails {

    private final StatisticsRequestImpl mStatisticsRequest;

    private final StatisticsContract.ViewDetails mCarDetailsView;

    private final FireBrigadeUtils mFirebrigadeUtils;

    @Nullable
    private Integer mCarId;

    public StatisticsPresenter(StatisticsRequestImpl statisticsRequest, StatisticsContract.ViewDetails statisticsView, FireBrigadeUtils fireBrigadeUtils) {
        this.mStatisticsRequest = statisticsRequest;
        this.mCarDetailsView = statisticsView;
        this.mFirebrigadeUtils = fireBrigadeUtils;

        statisticsView.setPresenter(this);
    }

    @Override
    public void start() {
        getStatistics();
    }


    @Override
    public void getStatistics() {
        mCarDetailsView.setLoadingIndicator();
        mStatisticsRequest.getStatisticsForFireBrigade(mFirebrigadeUtils.getFireBrigadeIdFromSharedPreferences(), new DataListener() {
            @Override
            public void onSuccess(String data) {
                Statistics statistics = Statistics.prepareFromJson(data);
                mCarDetailsView.showNumOfFirefighter(Integer.toString(statistics.getNumberOfFirefighters()));
                mCarDetailsView.showNumOfCommanders(Integer.toString(statistics.getNumberOfCommanders()));
                mCarDetailsView.showNumOfDrivers(Integer.toString(statistics.getNumberOfDrivers()));
                mCarDetailsView.showNumOfCars(Integer.toString(statistics.getNumberOfCars()));
                mCarDetailsView.showNumOfTools(Integer.toString(statistics.getNumberOfTools()));
                mCarDetailsView.showNumOfIncidents(Integer.toString(statistics.getNumberOfIncidents()));
                mCarDetailsView.showNumOfFire(Integer.toString(statistics.getNumberOfFire()));
                mCarDetailsView.showNumOfLocalThreat(Integer.toString(statistics.getNumberOfLocalThreat()));
                mCarDetailsView.showNumOfTrainings(Integer.toString(statistics.getNumberOfTrainings()));
                mCarDetailsView.showNumOfFalseAlarms(Integer.toString(statistics.getNumberOfFalseAlarms()));
                mCarDetailsView.showNumOfSecureAres(Integer.toString(statistics.getNumberOfSecuringArea()));
                mCarDetailsView.hideLoadingIndicator();
            }

            @Override
            public void onError(int code) {
                mCarDetailsView.showError();
            }
        });

    }
}
