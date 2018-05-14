package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import com.michalrubajczyk.myfirebrigade.model.dto.Equipment;

public interface EquipmentRequest {

    void getFireBrigadeEquipment(int fireBrigadeId, DataListener dataListener);

    void getActiveEquipmentForCar(int carId, DataListener dataListener);

    void getInactiveEquipmentForCar(int carId, DataListener dataListener);

    void createEquipment(Equipment equipment, int fireBrigadeId, DataListener dataListener);

    void updateEquipment(Equipment equipment, DataListener dataListener);

    void deleteEquipment(Equipment equipment, DataListener dataListener);
}
