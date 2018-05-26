package com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.model.dto.PreparedCarInIncident;

import java.util.HashMap;
import java.util.List;

public interface AddEditIncidentContract {

    interface View extends BaseView<Presenter> {

        void setCommandorsNames(String[] commandersNames);

        void setDriversNames(String[] driversNames);

        void setCarsNames(HashMap<String, String[]> carNames);

        void setFirefighterNames(String[] firefighters);

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

        void saveIncident(String type, String subtype, String date, String city, String description, List<PreparedCarInIncident> cars);

        void populateIncident();

        boolean isDataMissing();

    }

}
