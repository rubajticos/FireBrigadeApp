package com.michalrubajczyk.myfirebrigade.model.dto;

import com.google.common.base.Strings;
import com.google.gson.Gson;

import java.util.Date;

public class Firefighter {

    private int idFirefighter;
    private String name;
    private String lastName;
    private Date birthday;
    private Date expiryMedicalTest;
    private FireBrigade fireBrigade;

    public Firefighter() {
        this.idFirefighter = -1;
    }

    public Firefighter(int idFirefighter, String name, String lastName, Date birthday, Date expiryMedicalTest) {
        this.idFirefighter = idFirefighter;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.expiryMedicalTest = expiryMedicalTest;
    }

    public Firefighter(String json) {
        Firefighter prepareFirefighter = prepareFromJson(json);
        this.idFirefighter = prepareFirefighter.getIdFirefighter();
        this.name = prepareFirefighter.getName();
        this.lastName = prepareFirefighter.getLastName();
        this.birthday = prepareFirefighter.getBirthday();
        this.expiryMedicalTest = prepareFirefighter.getExpiryMedicalTest();
    }

    private Firefighter prepareFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Firefighter.class);
    }

    public int getIdFirefighter() {
        return idFirefighter;
    }

    public void setIdFirefighter(int idFirefighter) {
        this.idFirefighter = idFirefighter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getExpiryMedicalTest() {
        return expiryMedicalTest;
    }

    public void setExpiryMedicalTest(Date expiryMedicalTest) {
        this.expiryMedicalTest = expiryMedicalTest;
    }

    public FireBrigade getFireBrigade() {
        return fireBrigade;
    }

    public void setFireBrigade(FireBrigade fireBrigade) {
        this.fireBrigade = fireBrigade;
    }

    @Override
    public String toString() {
        return "Firefighter{" +
                "idFirefighter=" + idFirefighter +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", expiryMedicalTest=" + expiryMedicalTest +
                '}';
    }

    public boolean isValid() {
        if (Strings.isNullOrEmpty(name) && Strings.isNullOrEmpty(lastName) && birthday == null && expiryMedicalTest == null) {
            return false;
        }
        return true;
    }

    public String getNameAndLastName() {
        StringBuilder s = new StringBuilder();
        s.append(getName());
        s.append(" ");
        s.append(getLastName());
        return s.toString();
    }
}
