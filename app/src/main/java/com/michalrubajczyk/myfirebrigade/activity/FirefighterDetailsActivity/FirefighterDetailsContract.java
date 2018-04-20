package com.michalrubajczyk.myfirebrigade.activity.FirefighterDetailsActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;

import java.util.Date;
import java.util.List;

public interface FirefighterDetailsContract {

    interface ViewDetails extends BaseView<PresenterDetails> {
        void setLoadingIndicator(boolean active);

        void hideLoadingIndicator();

        void showMissingFirefighter();

        void hideName();

        void showName(String name);

        void hideLastName();

        void showLastName(String lastName);

        void hideBirthday();

        void showBirthday(String birthdayDate);

        void hideExpiryMedicalTest();

        void showExpiryMedicalTest(String medicalTestsDate);

        void showEditFirefighter(int firefighterId);

        boolean isActive();

    }

    interface PresenterDetails extends BasePresenter {

        void editFirefighter();

        void deleteFirefighter();

    }

    interface ViewDetailsTraining extends BaseView<PresenterDetailsTraining> {

        void setLoadingIndicator(boolean active);

        void hideLoadingIndicator();

        void showTrainings(List<FirefighterTraining> trainings);

        void showNoTrainings();

        void showLoadingFirefightersError();

    }

    interface PresenterDetailsTraining extends BasePresenter {

        void loadFirefighterTrainings(boolean forceUpdate);

    }

}
