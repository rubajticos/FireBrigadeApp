package com.michalrubajczyk.myfirebrigade.model.dto;

import com.google.common.base.Strings;
import com.google.gson.Gson;

public class Equipment {

    private int id;
    private String name;
    private String type;
    private String subtype;
    private FireBrigade fireBrigade;

    public Equipment() {
        this.id = -1;
    }

    public Equipment(String name, String type, String subtype) {
        this.id = -1;
        this.name = name;
        this.type = type;
        this.subtype = subtype;
    }

    public Equipment(int id, String name, String type, String subtype) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.subtype = subtype;
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

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public FireBrigade getFireBrigade() {
        return fireBrigade;
    }

    public void setFireBrigade(FireBrigade fireBrigade) {
        this.fireBrigade = fireBrigade;
    }

    public static Equipment createEquipmentFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Equipment.class);
    }

    public boolean isValid() {
        if (Strings.isNullOrEmpty(this.name) && Strings.isNullOrEmpty(this.type)) {
            return false;
        }
        return true;
    }
}
