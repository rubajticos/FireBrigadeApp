package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import com.michalrubajczyk.myfirebrigade.model.dto.Car;

public interface CarRequest {

    void getCarById(int carId, DataListener dataListener);

    void getCarsByFireBrigadeId(int fireBrigadeId, DataListener dataListener);

    void addCarToFireBrigade(Car car, int fireBrigadeId, DataListener dataListener);

    void updateCar(Car car, DataListener dataListener);

    void deleteCar(Car car, DataListener dataListener);

}
