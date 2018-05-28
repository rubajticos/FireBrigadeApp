package com.michalrubajczyk.myfirebrigade.activity.IncidentDetailActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;

public interface IncidentDetailsContract {

    interface ViewDetails extends BaseView<PresenterDetails> {
        void setLoadingIndicator(boolean active);

        void hideLoadingIndicator();

        void showMissingIncident();

        void setIncidentId(String incidentId);

        void showDescription(String description);

        void showCity(String city);

        void showDate(String date);

        void showDateOfAlarm(String dateOfAlarm);

        void showType(String type);

        void showSubType(String subtype);

        void showCarInfo(String carName, String departureTime, String returnTime, String commander, String driver, String firefighters, String usedEquipment);

        void setActivityName(String name);

        void showEditIncident(int incidentId);

        boolean isActive();

    }

    interface PresenterDetails extends BasePresenter {

        void editIncident();

        void deleteIncident();

    }

}
