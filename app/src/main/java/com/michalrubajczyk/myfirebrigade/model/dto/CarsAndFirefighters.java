package com.michalrubajczyk.myfirebrigade.model.dto;

import com.google.gson.Gson;

import java.util.List;

import lombok.Data;

@Data
public class CarsAndFirefighters {

    List<Firefighter> commanders;
    List<Firefighter> drivers;
    List<Firefighter> firefighters;
    List<CarWithEquipment> cars;

    public static CarsAndFirefighters prepareFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CarsAndFirefighters.class);
    }
}

