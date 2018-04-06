package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 23/03/2018.
 */

public class FireBrigadeDTO {

    private Integer idFireBrigade;
    private String name;
    private String voivodeship;
    private String district;
    private String community;
    private String city;
    private boolean ksrg;

    public FireBrigadeDTO() {
        this.name = "";
        this.voivodeship = "";
        this.district = "";
        this.community = "";
        this.city = "";
        this.ksrg = false;
    }

    public Integer getIdFireBrigade() {
        return idFireBrigade;
    }

    public void setIdFireBrigade(Integer idFireBrigade) {
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

    public boolean isKsrg() {
        return ksrg;
    }

    public void setKsrg(boolean ksrg) {
        this.ksrg = ksrg;
    }

    @Override
    public String toString() {
        return "<b>Nazwa jednostki: </b>" + name + "<br/>" +
                "<b>Wojewódzwto: </b>" + voivodeship + "<br/>" +
                "<b>Powiat: </b>" + district + "<br/>" +
                "<b>Gmina: </b>" + community + "<br/>" +
                "<b>Miejscowość: </b>" + city + "<br/>" +
                "<b>KSRG: </b>" + ksrgToString() + "<br/>";
    }

    private String ksrgToString() {
        if (isKsrg()) {
            return "TAK";
        }

        return "NIE";
    }

}
