package com.michalrubajczyk.myfirebrigade.activity.AddEditCarActivity;

import android.util.Log;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.dto.Car;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class AddEditCarPresenter implements AddEditCarContract.Presenter {
    public static final String TAG = "AdEd Car PRES";

    private final CarRequestImpl mCarRequest;

    private final AddEditCarContract.View mAddEditCarView;

    private final String mCarId;

    private boolean mIsDataMissing;

    private FireBrigadeUtils mFirebrigadeUtils;

    public AddEditCarPresenter(CarRequestImpl mCarRequest,
                               AddEditCarContract.View mAddEditCarView,
                               String mCarId,
                               boolean mIsDataMissing,
                               FireBrigadeUtils mFirebrigadeUtils) {
        this.mCarRequest = mCarRequest;
        this.mAddEditCarView = mAddEditCarView;
        this.mCarId = mCarId;
        this.mIsDataMissing = mIsDataMissing;
        this.mFirebrigadeUtils = mFirebrigadeUtils;

        mAddEditCarView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!isNewCar() && mIsDataMissing) {
            populateCar();
        }

    }

    private boolean isNewCar() {
        return mCarId == null;
    }

    @Override
    public void saveCar(String model, String operationalNumbers, String plates, String type, int water, int foam, int motorPumpPerformance, int numberOfSeats) {
        if (isNewCar()) {
            createCar(model, operationalNumbers, plates, type, water, foam, motorPumpPerformance, numberOfSeats);
        } else {
            int carId = Integer.parseInt(mCarId);
            updateCar(carId, model, operationalNumbers, plates, type, water, foam, motorPumpPerformance, numberOfSeats);
        }
    }

    private void createCar(String model, String operationalNumbers, String plates, String type, int water, int foam, int motorPumpPerformance, int numberOfSeats) {
        Log.d(TAG, "Tworzenie nowego samochodu");
        mAddEditCarView.showProgressBar();
        Car car = new Car(model, operationalNumbers, plates, type, water, foam, motorPumpPerformance, numberOfSeats);
        if (car.isValid()) {
            mCarRequest.addCarToFireBrigade(car, mFirebrigadeUtils.getFireBrigadeIdFromSharedPreferences(), new DataListener() {
                @Override
                public void onSuccess(String data) {
                    Log.d(TAG, "Dodawanie samochodu powiodło się!");
                    mAddEditCarView.showAddCarSuccess();
                    mAddEditCarView.showCars();
                    mAddEditCarView.hideProgressBar();
                }

                @Override
                public void onError(int code) {
                    Log.d(TAG, "Dodawanie samochodu nie powiodło się :(");
                    if (code == -999) {
                        mAddEditCarView.showServerError();
                        mAddEditCarView.hideProgressBar();
                    } else {
                        mAddEditCarView.showAddCarError();
                        mAddEditCarView.hideProgressBar();
                    }
                }
            });
        } else {
            mAddEditCarView.showInwalidCarError();
        }

    }

    private void updateCar(int carId, String model, String operationalNumbers, String plates, String type, int water, int foam, int motorPumpPerformance, int numberOfSeats) {
        Log.d(TAG, "Aktualizacja samochodu o id = " + carId);
        mAddEditCarView.showProgressBar();
        Car car = new Car(carId, model, operationalNumbers, plates, type, water, foam, motorPumpPerformance, numberOfSeats);
        if (car.isValid()) {
            mCarRequest.updateCar(car, new DataListener() {
                @Override
                public void onSuccess(String data) {
                    Log.d(TAG, "Aktualizacja samochodu powiodła się!");
                    mAddEditCarView.showUpdateCarSuccess();
                    mAddEditCarView.showCars();
                    mAddEditCarView.hideProgressBar();
                }

                @Override
                public void onError(int code) {
                    Log.d(TAG, "Aktualizacja samochodu nie powiodła się :(");
                    if (code == -999) {
                        mAddEditCarView.showServerError();
                        mAddEditCarView.hideProgressBar();
                    } else {
                        mAddEditCarView.showUpdateCarError();
                        mAddEditCarView.hideProgressBar();
                    }
                }
            });
        } else {
            mAddEditCarView.showInwalidCarError();
        }

    }

    @Override
    public void populateCar() {
        if (isNewCar()) {
            throw new RuntimeException("populateCar() was called but firefighter is new");
        }
        mAddEditCarView.showProgressBar();
        int carId = Integer.parseInt(mCarId);
        getAndSetMyCarDetails(carId);
    }


    private void getAndSetMyCarDetails(int carId) {
        mCarRequest.getCarById(carId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                Car car = prepareCarFromResponse(data);
                mAddEditCarView.setModel(car.getModel());
                mAddEditCarView.setOperationalNumbers(car.getOperationalNumbers());
                mAddEditCarView.setPlates(car.getPlates());
                mAddEditCarView.setType(car.getType());
                mAddEditCarView.setWater(car.getWater());
                mAddEditCarView.setFoam(car.getFoam());
                mAddEditCarView.setMotorPumpPerformance(car.getMotorPumpPerformance());
                mAddEditCarView.setNumberOfSeats(car.getNumberOfSeats());

                mAddEditCarView.hideProgressBar();
            }

            private Car prepareCarFromResponse(String data) {
                Gson gson = new Gson();
                return gson.fromJson(data, Car.class);
            }

            @Override
            public void onError(int code) {
                Log.d(TAG, "Błąd podczas pobierania samochodu do aktualizacji :(");
                if (code == -999) {
                    mAddEditCarView.showServerError();
                    mAddEditCarView.hideProgressBar();
                } else {
                    mAddEditCarView.showPopulateCarError();
                    mAddEditCarView.hideProgressBar();
                }
            }
        });
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }


}
