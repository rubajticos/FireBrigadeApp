package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CarIncident {

    private int idCarIncident;
    private Car car;
    private Incident incident;
    private Firefighter commander;
    private Firefighter driver;
    private Date dateTimeOfDeparture;
    private Date dateTimeOfReturn;
    private List<Firefighter> firefighters;
    private List<Equipment> usedEquipments;

    public CarIncident() {
        this.idCarIncident = -1;
    }
}
