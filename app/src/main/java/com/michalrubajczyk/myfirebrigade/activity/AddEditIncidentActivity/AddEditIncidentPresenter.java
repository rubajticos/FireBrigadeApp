package com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity;

import com.michalrubajczyk.myfirebrigade.dto.additional.PreparedCarInIncident;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.IncidentRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.Car;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    private List<Car> myFireBrigadeCars;

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
            populateIncident();
        }
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
