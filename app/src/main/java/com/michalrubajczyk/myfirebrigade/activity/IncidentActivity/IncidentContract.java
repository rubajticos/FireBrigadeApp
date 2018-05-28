package com.michalrubajczyk.myfirebrigade.activity.IncidentActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.model.dto.IncidentFull;

import java.util.List;

public interface IncidentContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void hideLoadingIndicator();

        void showIncidents(List<IncidentFull> incidentsList);

        void showAddIncident();

        void showDetailsIncident(String incidentId);

        void showLoadingIncidentError();

        void showNoIncidents();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadIncidents(boolean forceUpdade);
    }
}
