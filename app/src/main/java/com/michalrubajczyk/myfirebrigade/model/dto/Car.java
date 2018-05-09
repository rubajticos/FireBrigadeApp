package com.michalrubajczyk.myfirebrigade.model.dto;

import com.google.gson.Gson;

public class Car {

    private int id;
    private String model;
    private String operationalNumbers;
    private String plates;
    private String type;
    private int water;
    private int foam;
    private int motorPumpPerformance;

    public Car() {
        this.id = -1;
    }

    public Car(int id, String model, String operationalNumbers, String plates, String type, int water, int foam, int motorPumpPerformance) {
        this.id = id;
        this.model = model;
        this.operationalNumbers = operationalNumbers;
        this.plates = plates;
        this.type = type;
        this.water = water;
        this.foam = foam;
        this.motorPumpPerformance = motorPumpPerformance;
    }

    public static Car createCarFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Car.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOperationalNumbers() {
        return operationalNumbers;
    }

    public void setOperationalNumbers(String operationalNumbers) {
        this.operationalNumbers = operationalNumbers;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getFoam() {
        return foam;
    }

    public void setFoam(int foam) {
        this.foam = foam;
    }

    public int getMotorPumpPerformance() {
        return motorPumpPerformance;
    }

    public void setMotorPumpPerformance(int motorPumpPerformance) {
        this.motorPumpPerformance = motorPumpPerformance;
    }

}
