package com.michalrubajczyk.myfirebrigade.activity.StatisticsActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;

public interface StatisticsContract {

    interface ViewDetails extends BaseView<PresenterDetails> {
        void setLoadingIndicator();

        void hideLoadingIndicator();

        void showNumOfFirefighter(String num);

        void showNumOfCommanders(String num);

        void showNumOfDrivers(String num);

        void showNumOfCars(String num);

        void showNumOfTools(String num);

        void showNumOfIncidents(String num);

        void showNumOfFire(String num);

        void showNumOfLocalThreat(String num);

        void showNumOfTrainings(String num);

        void showNumOfFalseAlarms(String num);

        void showNumOfSecureAres(String num);

        void showError();

        boolean isActive();

    }

    interface PresenterDetails extends BasePresenter {

        void getStatistics();

    }

}
