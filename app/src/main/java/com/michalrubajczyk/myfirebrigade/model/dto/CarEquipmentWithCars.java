package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class CarEquipmentWithCars {

    private CarEquipment carEquipment;
    private List<Car> allCars;

}
