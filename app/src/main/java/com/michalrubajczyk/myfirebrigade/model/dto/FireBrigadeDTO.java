package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.List;

/**
 * Created by Michal on 23/03/2018.
 */

public class FireBrigadeDTO {

    private int idFireBrigade;
    private String name;
    private String voivodeship;
    private String district;
    private String community;
    private String city;
    private boolean ksrg;
    private Integer user;
    private List<Integer> firefightersIds;
    private List<Integer> carsIds;
    private List<Integer> equipmentIds;
    private List<FireBrigadeIncidentDTO> incidents;

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

    public boolean isKsrg() {
        return ksrg;
    }

    public void setKsrg(boolean ksrg) {
        this.ksrg = ksrg;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public List<Integer> getFirefightersIds() {
        return firefightersIds;
    }

    public void setFirefightersIds(List<Integer> firefightersIds) {
        this.firefightersIds = firefightersIds;
    }

    public List<Integer> getCarsIds() {
        return carsIds;
    }

    public void setCarsIds(List<Integer> carsIds) {
        this.carsIds = carsIds;
    }

    public List<Integer> getEquipmentIds() {
        return equipmentIds;
    }

    public void setEquipmentIds(List<Integer> equipmentIds) {
        this.equipmentIds = equipmentIds;
    }

    public List<FireBrigadeIncidentDTO> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<FireBrigadeIncidentDTO> incidents) {
        this.incidents = incidents;
    }

    @Override
    public String toString() {
        return "<b>Nazwa jednostki: </b>" + name + "<br/>" +
                "<b>Wojewódzwto: </b>" + voivodeship + "<br/>" +
                "<b>Powiat: </b>" + district + "<br/>" +
                "<b>Gmina: </b>" + community + "<br/>" +
                "<b>Miejscowość: </b>" + city + "<br/>" +
                "<b>KSRG: </b>" + ksrgToString() + "<br/>" +
                "<b>Strażaków: </b>" + firefightersIds.size() + "<br/>" +
                "<b>Samochodów: </b>" + carsIds.size();
    }

    private String ksrgToString() {
        if (isKsrg()) {
            return "TAK";
        }

        return "NIE";
    }

}
