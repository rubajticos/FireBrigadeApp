package com.michalrubajczyk.myfirebrigade.model;

import java.util.Set;

/**
 * Created by Michal on 26/02/2018.
 */

public class FireBrigade {

    private int idFireBrigade;
    private String name;
    private String voivodeship;
    private String district;
    private String community;
    private String city;
    private int ksrg;
    private User user;
    private Set<Firefighter> firefighters;

    public FireBrigade() {
    }

    public int getIdFireBrigade() {
        return idFireBrigade;
    }

    public void setIdFireBrigade(int idFireBrigade) {
        this.idFireBrigade = idFireBrigade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getKsrg() {
        return ksrg;
    }

    public void setKsrg(int ksrg) {
        this.ksrg = ksrg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Firefighter> getFirefighters() {
        return firefighters;
    }

    public void setFirefighters(Set<Firefighter> firefighters) {
        this.firefighters = firefighters;
    }
}
