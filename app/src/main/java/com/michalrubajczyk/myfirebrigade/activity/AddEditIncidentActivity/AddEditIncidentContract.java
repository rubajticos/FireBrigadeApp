package com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.dto.additional.PreparedCarInIncident;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AddEditIncidentContract {

    interface View extends BaseView<Presenter> {

        void setCarNames(Map<String, Integer> carNames);

        void setCommandersNames(Map<String, Integer> commandersNames);

        void setDriversNames(Map<String, Integer> driversNames);

        void showInwalidIncidentError();

        void showNumOfPeopleExceeded(String carName);

        void showNoCars();

        void showNoFirefighters();

        void showNoCommanders();

        void showNoDrivers();

        void showNoEquipmentInCar();

        void showProgress();

        void hideProgress();

        void setType(String type);

        void setSubtype(String subtype);

        void setDate(String date);

        void setCity(String city);

        void setDescription(String description);

        boolean isActive();


    }

    interface Presenter extends BasePresenter {

        void saveIncident(String type, String subtype, Date date, String city, String description, List<PreparedCarInIncident> cars);

        void populateIncident();

        boolean isDataMissing();

    }

}
