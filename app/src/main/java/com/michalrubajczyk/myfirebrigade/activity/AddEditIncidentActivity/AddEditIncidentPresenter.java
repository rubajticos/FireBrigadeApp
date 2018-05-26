package com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity;

import android.util.Log;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.IncidentRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.CarIncident;
import com.michalrubajczyk.myfirebrigade.model.dto.CarWithEquipment;
import com.michalrubajczyk.myfirebrigade.model.dto.CarsAndFirefighters;
import com.michalrubajczyk.myfirebrigade.model.dto.Equipment;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeIncident;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.model.dto.Incident;
import com.michalrubajczyk.myfirebrigade.model.dto.IncidentFull;
import com.michalrubajczyk.myfirebrigade.model.dto.PreparedCarInIncident;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.text.ParseException;
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

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat datetimeFormatter;

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

        dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        datetimeFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

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
    public void saveIncident(String type, String subtype, String date, String city, String description, List<PreparedCarInIncident> cars) {
        if (isNewIncicent()) {
            createIncident(type, subtype, date, city, description, cars);
        } else {
            updateIncident(type, subtype, date, city, description, cars);
        }
    }

    private void createIncident(String type, String subtype, String date, String city, String description, List<PreparedCarInIncident> cars) {
        Incident incident = new Incident();
        incident.setType(type);
        incident.setSubtype(subtype);
        try {
            incident.setDate(dateFormatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        incident.setCity(city);
        incident.setDescription(description);

        List<CarIncident> carIncidentList = prepareCarIncidentList(cars);

        List<FireBrigadeIncident> fireBrigadeIncidents = new ArrayList<>();
        FireBrigadeIncident fireBrigadeIncident = new FireBrigadeIncident();
        try {
            fireBrigadeIncident.setDateTimeOfAlarm(dateFormatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fireBrigadeIncidents.add(fireBrigadeIncident);

        IncidentFull incidentFull = new IncidentFull(incident, carIncidentList, fireBrigadeIncidents);

        // TODO: 26/05/2018 nie dodaje samochodow

        mIncidentRequest.addIncident(incidentFull, mFirebrigadeUtils.getFireBrigadeIdFromSharedPreferences(), new DataListener() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "Dodawanie zdarzenia udane");
            }

            @Override
            public void onError(int code) {

            }
        });


    }

    private List<CarIncident> prepareCarIncidentList(List<PreparedCarInIncident> cars) {
        List<CarIncident> carIncidents = new ArrayList<>();
        for (PreparedCarInIncident preparedCarInIncident : cars) {
            CarIncident carIncident = new CarIncident();

            for (CarWithEquipment item : myFireBrigadeCars) {
                if (item.getCar().getModel().equals(preparedCarInIncident.getCarName())) {
                    carIncident.setCar(item.getCar());
                    List<Equipment> carEquipments = prepareEquipments(item.getEquipments(), preparedCarInIncident.getEquipmentNames());
                    carIncident.setUsedEquipments(carEquipments);
                }
            }

            Firefighter commander = findFirefighter(preparedCarInIncident.getCommanderName(), myFireBrigadeCommanders);
            carIncident.setCommander(commander);

            Firefighter driver = findFirefighter(preparedCarInIncident.getDriverName(), myFireBrigadeDrivers);
            carIncident.setDriver(driver);

            try {
                Date datetimeOfDeparture = datetimeFormatter.parse(preparedCarInIncident.getDatetimeOfDeparture());
                carIncident.setDateTimeOfDeparture(datetimeOfDeparture);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date datetimeOfReturn = datetimeFormatter.parse(preparedCarInIncident.getDatetimeOfReturn());
                carIncident.setDateTimeOfDeparture(datetimeOfReturn);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return carIncidents;
    }

    private Firefighter findFirefighter(String commanderName, List<Firefighter> firefighters) {
        for (Firefighter f : firefighters) {
            String commanderConcat = f.getName() + " " + f.getLastName();
            if (commanderConcat.equals(commanderName)) {
                return f;
            }
        }

        return null;
    }

    private List<Equipment> prepareEquipments(List<Equipment> equipments, List<String> equipmentNames) {
        List<Equipment> equipmentList = new ArrayList<>();
        for (String s : equipmentNames) {
            for (Equipment e : equipments) {
                if (e.getName().equals(s)) {
                    equipmentList.add(e);
                }
            }
        }
        return equipmentList;
    }

    private void updateIncident(String type, String subtype, String date, String city, String description, List<PreparedCarInIncident> cars) {
    }

    @Override
    public boolean isDataMissing() {
        return false;
    }
}
