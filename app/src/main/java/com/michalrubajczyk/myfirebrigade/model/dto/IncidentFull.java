package com.michalrubajczyk.myfirebrigade.model.dto;

import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class IncidentFull {

    private Incident incident;
    private List<CarIncident> cars;
    private List<FireBrigadeIncident> fireBrigades;

    public IncidentFull(Incident incident, List<CarIncident> carIncidentList, List<FireBrigadeIncident> fireBrigadeIncidents) {
        this.incident = incident;
        this.cars = carIncidentList;
        this.fireBrigades = fireBrigadeIncidents;
    }

    public static List<IncidentFull> makeObjectsFromJson(String data) {
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss");

        IncidentFull[] incidentFullsArr = gsonBuilder.create().fromJson(data, IncidentFull[].class);
        return Arrays.asList(incidentFullsArr);
    }

}
