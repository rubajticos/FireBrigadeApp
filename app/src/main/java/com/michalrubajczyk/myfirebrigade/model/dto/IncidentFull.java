package com.michalrubajczyk.myfirebrigade.model.dto;

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
}
