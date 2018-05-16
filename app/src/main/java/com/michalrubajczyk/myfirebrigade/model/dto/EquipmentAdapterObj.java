package com.michalrubajczyk.myfirebrigade.model.dto;

public class EquipmentAdapterObj {

    private int id;
    private String name;
    private String type;
    private String carName;

    public EquipmentAdapterObj() {
    }

    public EquipmentAdapterObj(int id, String name, String type, String carName) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.carName = carName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
}
