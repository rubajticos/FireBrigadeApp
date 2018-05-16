package com.michalrubajczyk.myfirebrigade.activity.AddEditEquipmentActivity;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.EquipmentRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.text.SimpleDateFormat;

public class AddEditEquipmentPresenter implements AddEditEquipmentContract.Presenter {
    public static final String TAG = "AdEd Firefither PRES";

    private final FirefighterRequestImpl mFirefighterRequest;

    private final EquipmentRequestImpl mEquipmentRequest;

    private final AddEditEquipmentContract.View mAddEditFirefighterView;

    private final String mEquipmentId;

    private boolean mIsDataMissing;

    private FireBrigadeUtils mFirebrigadeUtils;

    private SimpleDateFormat simpleDateFormat;

    public AddEditEquipmentPresenter(FirefighterRequestImpl mFirefighterRequest,
                                     EquipmentRequestImpl mEquipmentRequest,
                                     AddEditEquipmentContract.View mAddEditFirefighterView,
                                     String mEquipmentId,
                                     boolean mIsDataMissing,
                                     FireBrigadeUtils mFirebrigadeUtils) {
        this.mFirefighterRequest = mFirefighterRequest;
        this.mEquipmentRequest = mEquipmentRequest;
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
            populateEquipment();
        }
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
    }

    private void updateEquipment(String name, String type, String carName) {
    }

    @Override
    public void populateEquipment() {

    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }


}
