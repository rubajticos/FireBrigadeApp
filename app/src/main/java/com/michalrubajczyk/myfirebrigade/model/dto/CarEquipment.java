package com.michalrubajczyk.myfirebrigade.model.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class CarEquipment {

    private int carEquipmentId;
    private Car car;
    private Equipment equipment;
    private int qty;
    private Date dateOfPut;
    private Date dateOfWithdrawal;

    public CarEquipment() {
        this(-1, null, null, -1, null, null);
    }

    public CarEquipment(Car car, Equipment equipment, int qty, Date dateOfPut, Date dateOfWithdrawal) {
        this(-1, car, equipment, qty, dateOfPut, dateOfWithdrawal);
    }

    public CarEquipment(int carEquipmentId, Car car, Equipment equipment, int qty, Date dateOfPut, Date dateOfWithdrawal) {
        this.carEquipmentId = carEquipmentId;
        this.car = car;
        this.equipment = equipment;
        this.qty = qty;
        this.dateOfPut = dateOfPut;
        this.dateOfWithdrawal = dateOfWithdrawal;
    }

    public static CarEquipment createFromJson(String json) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.fromJson(json, CarEquipment.class);
    }

    public int getCarEquipmentId() {
        return carEquipmentId;
    }

    public void setCarEquipmentId(int carEquipmentId) {
        this.carEquipmentId = carEquipmentId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getDateOfPut() {
        return dateOfPut;
    }

    public void setDateOfPut(Date dateOfPut) {
        this.dateOfPut = dateOfPut;
    }

    public Date getDateOfWithdrawal() {
        return dateOfWithdrawal;
    }

    public void setDateOfWithdrawal(Date dateOfWithdrawal) {
        this.dateOfWithdrawal = dateOfWithdrawal;
    }

    public boolean isValid() {
        if (this.car == null && this.equipment == null && this.qty <= 0 && this.dateOfPut == null) {
            return false;
        }
        return true;
    }
}
