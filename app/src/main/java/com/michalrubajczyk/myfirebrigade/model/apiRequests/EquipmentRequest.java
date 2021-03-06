package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import com.michalrubajczyk.myfirebrigade.model.dto.CarEquipment;
import com.michalrubajczyk.myfirebrigade.model.dto.Equipment;

public interface EquipmentRequest {

    void getFireBrigadeEquipment(int fireBrigadeId, DataListener dataListener);

    void getFireBrigadeEquipmentWithCarInfo(int fireBrigadeId, DataListener dataListener);

    void getActiveEquipmentForCar(int carId, DataListener dataListener);

    void getInactiveEquipmentForCar(int carId, DataListener dataListener);

    void createEquipment(Equipment equipment, int fireBrigadeId, DataListener dataListener);

    void updateEquipment(Equipment equipment, DataListener dataListener);

    void deleteEquipment(Equipment equipment, DataListener dataListener);

    void getSingleEquipmentWithAllCars(int equipmentId, int fireBrigadeId, DataListener dataListener);

    void createEquipmentAndSetToCar(CarEquipment carEquipment, int fireBrigadeId, DataListener dataListener);

    void updateEquipmentAndCheckCar(CarEquipment carEquipment, DataListener dataListener);
}
