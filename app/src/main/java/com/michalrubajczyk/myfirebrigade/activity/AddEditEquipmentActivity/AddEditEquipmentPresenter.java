package com.michalrubajczyk.myfirebrigade.activity.AddEditEquipmentActivity;

import android.util.Log;

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
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public void saveEquipment(String name, String type, String carName) {
        if (isNewEquipment()) {
            createEquipment(name, type, carName);
        } else {
            updateEquipment(name, type, carName);
        }
    }

    private void createEquipment(String name, String type, String carName) {
        // TODO: 18/05/2018 nie zapomniec o dacie dodania
    }

    private void updateEquipment(String name, String type, String carName) {
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
