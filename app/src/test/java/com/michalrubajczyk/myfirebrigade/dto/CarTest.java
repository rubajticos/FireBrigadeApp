package com.michalrubajczyk.myfirebrigade.dto;

import com.michalrubajczyk.myfirebrigade.model.dto.Car;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {

    @Test
    public void createCarFromJson_isNotEmpty() {
        String json = "{\"id\":1,\"model\":\"Steyr 791\",\"operationalNumbers\":\"569[R]70\",\"plates\":\"RSR32XE\",\"type\":\"GBA\",\"water\":2500,\"foam\":250,\"motorPumpPerformance\":16}";

        Car car = Car.createCarFromJson(json);
        Assert.assertNotNull(car.getId());
        Assert.assertNotNull(car.getModel());
        Assert.assertNotNull(car.getOperationalNumbers());
        Assert.assertNotNull(car.getPlates());
        Assert.assertNotNull(car.getType());
        Assert.assertNotNull(car.getWater());
        Assert.assertNotNull(car.getFoam());
        Assert.assertNotNull(car.getMotorPumpPerformance());


    }
}
