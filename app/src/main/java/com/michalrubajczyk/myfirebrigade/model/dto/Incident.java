package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Incident {

    private int id;
    private String type;
    private String subtype;
    private Date date;
    private String city;
    private String description;

    public Incident() {
        this.id = -1;
    }
}
