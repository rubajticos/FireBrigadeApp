package com.michalrubajczyk.myfirebrigade.model;

import java.sql.Date;

/**
 * Created by Michal on 26/02/2018.
 */

public class Firefighter {

    private int idFirefighter;
    private String name;
    private String lastName;
    private Date birthday;
    private Date expiryMedicalTest;
    private FireBrigade fireBrigade;

    public Firefighter() {
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
}
