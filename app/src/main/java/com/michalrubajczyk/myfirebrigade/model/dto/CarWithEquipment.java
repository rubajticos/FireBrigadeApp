package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class CarWithEquipment {

    private Car car;

    private List<Equipment> equipments;
}
