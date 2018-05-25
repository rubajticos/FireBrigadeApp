package com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity;

import com.michalrubajczyk.myfirebrigade.dto.additional.PreparedCarInIncident;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.IncidentRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.CarWithEquipment;
import com.michalrubajczyk.myfirebrigade.model.dto.CarsAndFirefighters;
import com.michalrubajczyk.myfirebrigade.model.dto.Equipment;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddEditIncidentPresenter implements AddEditIncidentContract.Presenter {
    public static final String TAG = "AdEd Firefither PRES";

    private final IncidentRequestImpl mIncidentRequest;

    private final FirefighterRequestImpl mFirefighterRequest;

    private final CarRequestImpl mCarRequest;

    private final AddEditIncidentContract.View mAddEditIncidentView;

    private final String mIncidentId;

    private boolean mIsDataMissing;

    private FireBrigadeUtils mFirebrigadeUtils;

    private SimpleDateFormat simpleDateFormat;

    private List<CarWithEquipment> myFireBrigadeCars;
    private List<Firefighter> myFireBrigadeFirefighters;
    private List<Firefighter> myFireBrigadeCommanders;
    private List<Firefighter> myFireBrigadeDrivers;

    public AddEditIncidentPresenter(IncidentRequestImpl mIncidentRequest,
                                    FirefighterRequestImpl mFirefighterRequest,
                                    CarRequestImpl mCarRequest,
                                    AddEditIncidentContract.View mAddEditIncidentView,
                                    String mIncidentId,
                                    boolean mIsDataMissing,
                                    FireBrigadeUtils mFirebrigadeUtils) {
        this.mIncidentRequest = mIncidentRequest;
        this.mFirefighterRequest = mFirefighterRequest;
        this.mCarRequest = mCarRequest;
        this.mAddEditIncidentView = mAddEditIncidentView;
        this.mIncidentId = mIncidentId;
        this.mIsDataMissing = mIsDataMissing;
        this.mFirebrigadeUtils = mFirebrigadeUtils;

        mAddEditIncidentView.setPresenter(this);

        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    }

    @Override
    public void start() {
        if (!isNewIncicent() && mIsDataMissing) {
            prepareDataAndPopulateIncident();
        } else {
            prepareData();
        }
    }

    private void prepareDataAndPopulateIncident() {

    }

    private void prepareData() {
        mAddEditIncidentView.showProgress();
        mIncidentRequest.getFirefightersAndCars(mFirebrigadeUtils.getFireBrigadeIdFromSharedPreferences(), new DataListener() {
            @Override
            public void onSuccess(String data) {
                CarsAndFirefighters carsAndFirefighters = CarsAndFirefighters.prepareFromJson(data);

                myFireBrigadeCars = carsAndFirefighters.getCars();
                myFireBrigadeCommanders = carsAndFirefighters.getCommanders();
                myFireBrigadeDrivers = carsAndFirefighters.getDrivers();
                myFireBrigadeFirefighters = carsAndFirefighters.getFirefighters();

                setItemsInView(myFireBrigadeCars, myFireBrigadeCommanders, myFireBrigadeDrivers, myFireBrigadeFirefighters);

            }


            @Override
            public void onError(int code) {
                // TODO: 24/05/2018 obsluga bledow
            }
        });
    }

    private void setItemsInView(List<CarWithEquipment> myFireBrigadeCars, List<Firefighter> myFireBrigadeCommanders, List<Firefighter> myFireBrigadeDrivers, List<Firefighter> myFireBrigadeFirefighters) {
        if (myFireBrigadeCars.size() != 0) {
            HashMap<String, String[]> carNamesWithEquipmentMap = prepareCarMap(myFireBrigadeCars);
            mAddEditIncidentView.setCarsNames(carNamesWithEquipmentMap);
        } else {
            mAddEditIncidentView.showNoCars();
        }

        if (myFireBrigadeCommanders.size() != 0) {
            String[] commandersArray = prepareFirefighterStringArrayFromList(myFireBrigadeCommanders);
            mAddEditIncidentView.setCommandorsNames(commandersArray);
        } else {
            mAddEditIncidentView.showNoCommanders();
        }

        if (myFireBrigadeDrivers.size() != 0) {
            String[] driversArray = prepareFirefighterStringArrayFromList(myFireBrigadeDrivers);
            mAddEditIncidentView.setDriversNames(driversArray);
        } else {
            mAddEditIncidentView.showNoDrivers();
        }

        if (myFireBrigadeFirefighters.size() != 0) {
            String[] firefightersArray = prepareFirefighterStringArrayFromList(myFireBrigadeFirefighters);
            mAddEditIncidentView.setFirefighterNames(firefightersArray);
        } else {
            mAddEditIncidentView.showNoFirefighters();
        }

    }

    private String[] prepareFirefighterStringArrayFromList(List<Firefighter> myFirefighterList) {
        List<String> firefighterNamesList = new ArrayList<>();

        for (Firefighter f : myFirefighterList) {
            firefighterNamesList.add(f.getName() + " " + f.getLastName());
        }

        return firefighterNamesList.toArray(new String[firefighterNamesList.size()]);
    }

    private HashMap<String, String[]> prepareCarMap(List<CarWithEquipment> myFireBrigadeCars) {
        HashMap<String, String[]> map = new HashMap<>();
        for (CarWithEquipment c : myFireBrigadeCars) {
            List<String> eqNamesList = new ArrayList<>();
            for (Equipment e : c.getEquipments()) {
                eqNamesList.add(e.getName());
            }

            String[] eqNamesArray = eqNamesList.toArray(new String[eqNamesList.size()]);

            map.put(c.getCar().getModel(), eqNamesArray);
        }
        return map;
    }

    private boolean isNewIncicent() {
        return mIncidentId == null;
    }

    @Override
    public void populateIncident() {

    }

    @Override
    public void saveIncident(String type, String subtype, Date date, String city, String description, List<PreparedCarInIncident> cars) {
        if (isNewIncicent()) {
            createIncident(type, subtype, date, city, description, cars);
        } else {
            updateIncident(type, subtype, date, city, description, cars);
        }
    }

    private void createIncident(String type, String subtype, Date date, String city, String description, List<PreparedCarInIncident> cars) {
    }

    private void updateIncident(String type, String subtype, Date date, String city, String description, List<PreparedCarInIncident> cars) {
    }

    @Override
    public boolean isDataMissing() {
        return false;
    }
}
