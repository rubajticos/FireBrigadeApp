package com.michalrubajczyk.myfirebrigade.model.dto;

import android.icu.text.StringSearch;

import com.google.common.base.Strings;
import com.google.gson.Gson;

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

    public FireBrigadeDTO(String json) {
        FireBrigadeDTO prepareDto = prepareFromJson(json);
        this.idFireBrigade = prepareDto.getIdFireBrigade();
        this.name = prepareDto.getName();
        this.voivodeship = prepareDto.getVoivodeship();
        this.district = prepareDto.getDistrict();
        this.community = prepareDto.getCommunity();
        this.city = prepareDto.getCity();
        this.ksrg = prepareDto.isKsrg();
    }

    public FireBrigadeDTO(String name, String voivodeship, String district, String community, String city, boolean ksrg) {
        this.name = name;
        this.voivodeship = voivodeship;
        this.district = district;
        this.community = community;
        this.city = city;
        this.ksrg = ksrg;
    }

    public FireBrigadeDTO(Integer id, String name, String voivodeship, String district, String community, String city, boolean ksrg) {
        this.idFireBrigade = id;
        this.name = name;
        this.voivodeship = voivodeship;
        this.district = district;
        this.community = community;
        this.city = city;
        this.ksrg = ksrg;
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

    private FireBrigadeDTO prepareFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, FireBrigadeDTO.class);
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(name) &&
                Strings.isNullOrEmpty(voivodeship) &&
                Strings.isNullOrEmpty(district) &&
                Strings.isNullOrEmpty(community) &&
                Strings.isNullOrEmpty(city);
    }
}
