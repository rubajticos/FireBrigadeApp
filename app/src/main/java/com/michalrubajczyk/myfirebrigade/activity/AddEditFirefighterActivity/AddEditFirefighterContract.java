package com.michalrubajczyk.myfirebrigade.activity.AddEditFirefighterActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.model.dto.Training;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface AddEditFirefighterContract {

    interface View extends BaseView<Presenter> {

        void setTrainingNames(List<String> mTrainingNames);

        void showInwalidFirefighterError();

        void showUpdateFirefighterError();

        void showFirefighter();

        void setName(String name);

        void setLastName(String lastName);

        void setBirthday(String birthday);

        void setExpiryMedicalTests(String expiryMedicalTests);

        void setTrainings(HashMap<String, String> trainings);

        boolean isActive();


    }

    interface Presenter extends BasePresenter {

        void saveFirefighter(String name, String lastName, Date birthday, Date expiryMedicalTest, List<Training> trainings);

        void populateFirefighter();

        boolean isDataMissing();

    }

}
