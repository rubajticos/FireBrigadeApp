package com.michalrubajczyk.myfirebrigade.activity.CarActivity;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.dto.Car;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.util.Arrays;
import java.util.List;

public class CarPresenter implements CarContract.Presenter {

    private final CarRequestImpl mCarRequest;

    private final CarContract.View mCarView;

    private final FireBrigadeUtils mFireBrigadeUtils;

    private final int mFireBrigadeId;

    private boolean mFirstLoad = true;

    public CarPresenter(FireBrigadeUtils fireBrigadeUtils, CarRequestImpl mCarRequest, CarContract.View mCarView) {
        this.mFireBrigadeUtils = fireBrigadeUtils;
        this.mFireBrigadeId = mFireBrigadeUtils.getFireBrigadeIdFromSharedPreferences();
        this.mCarRequest = mCarRequest;
        this.mCarView = mCarView;
        this.mCarView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCars(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadCars(boolean forceUpdade) {
        mCarView.setLoadingIndicator(true);
        mCarRequest.getCarsByFireBrigadeId(mFireBrigadeId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                List<Car> cars = makeCarsListFromResponse(data);
                mCarView.showCars(cars);
                mCarView.hideLoadingIndicator();
            }

            private List<Car> makeCarsListFromResponse(String data) {
                Gson gson = new Gson();
                Car[] carsArray = gson.fromJson(data, Car[].class);
                return Arrays.asList(carsArray);
            }

            @Override
            public void onError(int code) {
                if (code == 404) {
                    mCarView.showNoCars();
                    mCarView.hideLoadingIndicator();
                    mCarView.showLoadingCarsError();
                } else {
                    mCarView.showNoCars();
                    mCarView.hideLoadingIndicator();
                }
            }
        });
    }

    @Override
    public void openCarDetails(Integer requestedCarId) {
        mCarView.showCarDetailUi(requestedCarId);
    }


}
