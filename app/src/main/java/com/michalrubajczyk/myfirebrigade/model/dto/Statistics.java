package com.michalrubajczyk.myfirebrigade.model.dto;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class Statistics {

    private int numberOfFirefighters;
    private int numberOfCommanders;
    private int numberOfDrivers;
    private int numberOfCars;
    private int numberOfTools;
    private int numberOfIncidents;
    private int numberOfFire;
    private int numberOfLocalThreat;
    private int numberOfTrainings;
    private int numberOfFalseAlarms;
    private int numberOfSecuringArea;

    public static Statistics prepareFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Statistics.class);
    }
}
