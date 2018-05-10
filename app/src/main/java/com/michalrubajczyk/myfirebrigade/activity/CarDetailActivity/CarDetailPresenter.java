package com.michalrubajczyk.myfirebrigade.activity.CarDetailActivity;

import android.support.annotation.Nullable;

import com.google.common.base.Strings;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.dto.Car;

public class CarDetailPresenter implements CarDetailsContract.PresenterDetails {

    private final CarRequestImpl mCarRequest;

    private final CarDetailsContract.ViewDetails mCarDetailsView;

    @Nullable
    private Integer mCarId;

    public CarDetailPresenter(CarRequestImpl mCarRequest, CarDetailsContract.ViewDetails mCarDetailsView, Integer mFirefighterId) {
        this.mCarRequest = mCarRequest;
        this.mCarDetailsView = mCarDetailsView;
        this.mCarId = mFirefighterId;

        mCarDetailsView.setPresenter(this);
    }

    @Override
    public void start() {
        openCar();
    }

    private void openCar() {
        if (mCarId == null) {
            mCarDetailsView.showMissingCar();
            return;
        }

        mCarDetailsView.setLoadingIndicator(true);
        mCarRequest.getCarById(mCarId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                Car car = Car.createCarFromJson(data);
                showCar(car);
                mCarDetailsView.hideLoadingIndicator();
            }

            @Override
            public void onError(int code) {
                mCarDetailsView.showMissingCar();
                mCarDetailsView.hideLoadingIndicator();
            }
        });
    }

    private void showCar(Car car) {
        String model = car.getModel();
        String operationalNumbers = car.getOperationalNumbers();
        String plates = car.getPlates();
        String type = car.getType();
        int water = car.getWater();
        int foam = car.getFoam();
        int motorPumpPerformance = car.getMotorPumpPerformance();
        int numOfSeats = car.getNumberOfSeats();

        if (!Strings.isNullOrEmpty(model)) {
            mCarDetailsView.setActivityName(model);
        }
        mCarDetailsView.setCarId(Integer.toString(car.getId()));
        mCarDetailsView.showModel(model);
        mCarDetailsView.showOperationalNumbers(operationalNumbers);
        mCarDetailsView.showPlates(plates);
        mCarDetailsView.showType(type);
        mCarDetailsView.showWater(water);
        mCarDetailsView.showFoam(foam);
        mCarDetailsView.showMotoPumpPerformance(motorPumpPerformance);
        mCarDetailsView.showNumberOfSeats(numOfSeats);
    }


    @Override
    public void editCar() {

    }

    @Override
    public void deleteCar() {

    }

}
