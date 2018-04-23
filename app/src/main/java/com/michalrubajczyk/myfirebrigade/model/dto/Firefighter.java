package com.michalrubajczyk.myfirebrigade.model.dto;

import com.google.gson.Gson;

import java.util.Date;

public class Firefighter {

    private int idFirefighter;
    private String name;
    private String lastName;
    private Date birthday;
    private Date expiryMedicalTest;

    public Firefighter() {
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
}
