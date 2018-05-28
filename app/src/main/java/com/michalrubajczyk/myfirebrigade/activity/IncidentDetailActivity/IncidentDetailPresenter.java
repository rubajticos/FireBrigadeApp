package com.michalrubajczyk.myfirebrigade.activity.IncidentDetailActivity;

import android.support.annotation.Nullable;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.IncidentRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.CarIncident;
import com.michalrubajczyk.myfirebrigade.model.dto.Equipment;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.model.dto.IncidentFull;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class IncidentDetailPresenter implements IncidentDetailsContract.PresenterDetails {

    private final IncidentRequestImpl mIncidentRequest;

    private final IncidentDetailsContract.ViewDetails mIncidentDetailsView;

    private final SimpleDateFormat dateFormatter;
    private final SimpleDateFormat dateTimeFormatter;

    @Nullable
    private Integer mIncidentId;

    public IncidentDetailPresenter(IncidentRequestImpl mIncidentRequest, IncidentDetailsContract.ViewDetails mIncidentDetailsView, Integer mIncidentId) {
        this.mIncidentRequest = mIncidentRequest;
        this.mIncidentDetailsView = mIncidentDetailsView;
        this.mIncidentId = mIncidentId;

        this.dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        this.dateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault());

        mIncidentDetailsView.setPresenter(this);

    }

    @Override
    public void start() {
        openIncident();
    }

    private void openIncident() {
        if (mIncidentId == null) {
            mIncidentDetailsView.showMissingIncident();
            return;
        }

        mIncidentDetailsView.setLoadingIndicator(true);
        mIncidentRequest.getIncidentById(mIncidentId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                IncidentFull incidentFull = IncidentFull.makeObjectFromJson(data);
                showIncident(incidentFull);
                mIncidentDetailsView.hideLoadingIndicator();
            }

            @Override
            public void onError(int code) {
                mIncidentDetailsView.showMissingIncident();
                mIncidentDetailsView.hideLoadingIndicator();
            }
        });
    }

    private void showIncident(IncidentFull incidentFull) {
        mIncidentDetailsView.showDescription(incidentFull.getIncident().getDescription());
        mIncidentDetailsView.showCity(incidentFull.getIncident().getCity());
        mIncidentDetailsView.showDate(dateFormatter.format(incidentFull.getIncident().getDate()));
        mIncidentDetailsView.showDateOfAlarm(dateTimeFormatter.format(incidentFull.getFireBrigades().get(0).getDateTimeOfAlarm()));
        mIncidentDetailsView.showType(incidentFull.getIncident().getType());
        mIncidentDetailsView.showSubType(incidentFull.getIncident().getSubtype());

        for (CarIncident c : incidentFull.getCars()) {
            String firefighters = prepareFirefighters(c.getFirefighters());
            String equipments = prepareEquipments(c.getUsedEquipments());

            mIncidentDetailsView.showCarInfo(
                    c.getCar().getModelAndNumbers(),
                    dateTimeFormatter.format(c.getDateTimeOfDeparture()),
                    dateTimeFormatter.format(c.getDateTimeOfReturn()),
                    c.getCommander().getNameAndLastName(),
                    c.getDriver().getNameAndLastName(),
                    firefighters,
                    equipments);
        }

    }

    private String prepareEquipments(List<Equipment> usedEquipments) {
        StringBuilder s = new StringBuilder();
        for (Equipment e : usedEquipments) {
            s.append(e.getName());
            s.append("\n");
        }

        return s.toString();
    }

    private String prepareFirefighters(List<Firefighter> firefighters) {
        StringBuilder s = new StringBuilder();
        for (Firefighter f : firefighters) {
            s.append(f.getNameAndLastName());
            s.append("\n");
        }
        return s.toString();
    }


    @Override
    public void editIncident() {

    }

    @Override
    public void deleteIncident() {

    }

}
