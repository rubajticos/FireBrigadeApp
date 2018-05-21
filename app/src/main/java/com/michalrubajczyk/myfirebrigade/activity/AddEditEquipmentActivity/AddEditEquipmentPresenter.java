package com.michalrubajczyk.myfirebrigade.activity.AddEditEquipmentActivity;

import android.util.Log;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.EquipmentRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.Car;
import com.michalrubajczyk.myfirebrigade.model.dto.CarEquipment;
import com.michalrubajczyk.myfirebrigade.model.dto.CarEquipmentWithCars;
import com.michalrubajczyk.myfirebrigade.model.dto.Equipment;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddEditEquipmentPresenter implements AddEditEquipmentContract.Presenter {
    public static final String TAG = "AdEd Firefither PRES";

    private final FirefighterRequestImpl mFirefighterRequest;

    private final EquipmentRequestImpl mEquipmentRequest;

    private final CarRequestImpl mCarRequest;

    private final AddEditEquipmentContract.View mAddEditFirefighterView;

    private final String mEquipmentId;

    private boolean mIsDataMissing;

    private FireBrigadeUtils mFirebrigadeUtils;

    private SimpleDateFormat simpleDateFormat;

    private List<Car> myCars;

    public AddEditEquipmentPresenter(FirefighterRequestImpl mFirefighterRequest,
                                     EquipmentRequestImpl mEquipmentRequest,
                                     CarRequestImpl mCarRequestImpl,
                                     AddEditEquipmentContract.View mAddEditFirefighterView,
                                     String mEquipmentId,
                                     boolean mIsDataMissing,
                                     FireBrigadeUtils mFirebrigadeUtils) {
        this.mFirefighterRequest = mFirefighterRequest;
        this.mEquipmentRequest = mEquipmentRequest;
        this.mCarRequest = mCarRequestImpl;
        this.mAddEditFirefighterView = mAddEditFirefighterView;
        this.mEquipmentId = mEquipmentId;
        this.mIsDataMissing = mIsDataMissing;
        this.mFirebrigadeUtils = mFirebrigadeUtils;

        mAddEditFirefighterView.setPresenter(this);

        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    }

    @Override
    public void start() {
        if (!isNewEquipment() && mIsDataMissing) {
            Log.d(TAG, "Przed edytowaniem sprzętu");
            prepareCarsAndPopulateEquipment();
        } else {
            Log.d(TAG, "Przed dodawaniem sprzętu");
            prepareCars();
        }
    }

    private void prepareCars() {
        mAddEditFirefighterView.showProgress();
        mCarRequest.getCarsByFireBrigadeId(mFirebrigadeUtils.getFireBrigadeIdFromSharedPreferences(), new DataListener() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "cars: " + data);
                List<Car> cars = Car.createCarListFromJson(data);
                if (cars.size() == 0) {
                    mAddEditFirefighterView.showNoCars();
                } else {
                    mAddEditFirefighterView.showCars();
                    myCars = cars;
                    mAddEditFirefighterView.setCarNames(prepareCarNames(myCars));
                }
                mAddEditFirefighterView.hideProgress();
            }

            @Override
            public void onError(int code) {
                mAddEditFirefighterView.hideProgress();
                if (code == 404) {
                    mAddEditFirefighterView.showNoCars();
                } else {
                    mAddEditFirefighterView.showServerError();
                }
            }
        });
    }

    private void prepareCarsAndPopulateEquipment() {
        mAddEditFirefighterView.showProgress();
        mEquipmentRequest.getSingleEquipmentWithAllCars(Integer.parseInt(mEquipmentId), mFirebrigadeUtils.getFireBrigadeIdFromSharedPreferences(), new DataListener() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "response" + data);
                CarEquipmentWithCars myEqWithcars = makeObjFromResponse(data);
                CarEquipment carEquipment = myEqWithcars.getCarEquipment();
                List<Car> cars = myEqWithcars.getAllCars();
                if (cars == null) {
                    mAddEditFirefighterView.showNoCars();
                } else {
                    mAddEditFirefighterView.showCars();
                    myCars = cars;
                    mAddEditFirefighterView.setCarNames(prepareCarNames(myCars));
                    populateEquipment(carEquipment);
                }
                mAddEditFirefighterView.hideProgress();
            }

            private CarEquipmentWithCars makeObjFromResponse(String response) {
                Gson gson = new Gson();
                return gson.fromJson(response, CarEquipmentWithCars.class);
            }

            @Override
            public void onError(int code) {
                mAddEditFirefighterView.hideProgress();
                if (code == 404) {
                    mAddEditFirefighterView.showNoCars();
                } else {
                    mAddEditFirefighterView.showServerError();
                }
            }
        });
    }

    private List<String> prepareCarNames(List<Car> myCars) {
        List<String> carNamesList = new ArrayList<>();
        String carNoChoosen = ResourcesSingleton.getInstance().getString(R.string.add_edit_equipment_car_not_choosen);
        carNamesList.add(carNoChoosen);
        for (Car c : myCars) {
            carNamesList.add(c.getModel());
        }
        return carNamesList;
    }


    private boolean isNewEquipment() {
        return mEquipmentId == null;
    }

    @Override
    public void saveEquipment(String name, String type, String subtype, String carName) {
        mAddEditFirefighterView.showProgress();
        if (isNewEquipment()) {
            createEquipment(name, type, subtype, carName);
        } else {
            updateEquipment(name, type, subtype, carName);
        }
    }

    private void createEquipment(String name, String type, String subtype, String carName) {
        if (isEquipmentNameEmpty(name)) {
            if (isCarNotSelected(carName)) {
                createOnlyEquipment(name, type, subtype);
            } else {
                createEquipmentWithCar(name, type, subtype, carName);
            }
        } else {
            mAddEditFirefighterView.hideProgress();
            mAddEditFirefighterView.showInwalidEquipmentError();
        }
    }

    private boolean isCarNotSelected(String carName) {
        return Strings.isNullOrEmpty(carName) || carName.equals(ResourcesSingleton.getInstance().getString(R.string.add_edit_equipment_car_not_choosen));
    }

    private boolean isEquipmentNameEmpty(String name) {
        return Strings.isNullOrEmpty(name);
    }

    private void createEquipmentWithCar(String name, String type, String subtype, String carName) {
        Equipment equipment = new Equipment(name, type, subtype);
        Car car = searchCarByName(carName, myCars);
        CarEquipment carEquipment = new CarEquipment();
        carEquipment.setEquipment(equipment);
        carEquipment.setCar(car);
        carEquipment.setDateOfPut(new Date());
        mEquipmentRequest.createEquipmentAndSetToCar(carEquipment, mFirebrigadeUtils.getFireBrigadeIdFromSharedPreferences(), new DataListener() {
            @Override
            public void onSuccess(String data) {
                mAddEditFirefighterView.hideProgress();
                mAddEditFirefighterView.showEquipments();
            }

            @Override
            public void onError(int code) {
                mAddEditFirefighterView.hideProgress();
                mAddEditFirefighterView.showAddEquipmentError();
            }
        });
    }

    private Car searchCarByName(String carName, List<Car> cars) {
        for (Car car : cars) {
            if (car.getModel().equals(carName)) return car;
        }
        return null;
    }

    private void createOnlyEquipment(String name, String type, String subtype) {
        Equipment equipment = new Equipment(name, type, subtype);
        mEquipmentRequest.createEquipment(equipment, mFirebrigadeUtils.getFireBrigadeIdFromSharedPreferences(), new DataListener() {
            @Override
            public void onSuccess(String data) {
                mAddEditFirefighterView.showEquipments();
                mAddEditFirefighterView.hideProgress();
            }

            @Override
            public void onError(int code) {
                mAddEditFirefighterView.hideProgress();
                mAddEditFirefighterView.showAddEquipmentError();
            }
        });
    }

    private void updateEquipment(String name, String type, String subtype, String carName) {
        if (isNewEquipment()) {
            throw new RuntimeException("updateEquipment() was called but equipment is new");
        }
        int equipmentId = Integer.parseInt(mEquipmentId);

        Equipment equipment = new Equipment(equipmentId, name, type, subtype);
        Car car = searchCarByName(carName, myCars);

        CarEquipment carEquipment = new CarEquipment();
        carEquipment.setEquipment(equipment);
        carEquipment.setCar(car);

        mEquipmentRequest.updateEquipmentAndCheckCar(carEquipment, new DataListener() {
            @Override
            public void onSuccess(String data) {
                mAddEditFirefighterView.hideProgress();
                mAddEditFirefighterView.showEquipments();
            }

            @Override
            public void onError(int code) {
                mAddEditFirefighterView.hideProgress();
                mAddEditFirefighterView.showUpdateEquipmentError();
            }
        });

    }

    @Override
    public void populateEquipment(CarEquipment carEquipment) {
        mAddEditFirefighterView.setName(carEquipment.getEquipment().getName());
        mAddEditFirefighterView.setTypes(carEquipment.getEquipment().getType(), carEquipment.getEquipment().getSubtype());
        if (carEquipment.getCar() != null) {
            mAddEditFirefighterView.setCar(carEquipment.getCar().getModel());
        } else {
            mAddEditFirefighterView.setCar(ResourcesSingleton.getInstance().getString(R.string.add_edit_equipment_car_not_choosen));
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }


}
