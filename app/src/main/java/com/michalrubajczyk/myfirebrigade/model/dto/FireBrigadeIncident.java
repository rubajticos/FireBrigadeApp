package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.Date;

import lombok.Data;

/**
 * Created by Michal on 23/03/2018.
 */

@Data
public class FireBrigadeIncident {

    private int idFireBrigadeIncident;
    private Incident incident;
    private FireBrigade fireBrigade;
    private Date dateTimeOfAlarm;

    public FireBrigadeIncident() {
        this.idFireBrigadeIncident = -1;
    }
}
