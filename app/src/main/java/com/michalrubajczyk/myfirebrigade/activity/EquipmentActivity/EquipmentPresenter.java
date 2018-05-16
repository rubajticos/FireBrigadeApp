package com.michalrubajczyk.myfirebrigade.activity.EquipmentActivity;

import android.content.Context;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.EquipmentRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.EquipmentAdapterObj;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.util.Arrays;
import java.util.List;

public class EquipmentPresenter implements EquipmentContract.Presenter {
    public static final String TAG = "Equipment Presenter";

    private final EquipmentRequestImpl mEquipmentRequest;

    private final EquipmentContract.View mEquipmentView;

    private final FireBrigadeUtils mFireBrigadeUtils;

    private final int mFireBrigadeId;

    private boolean mFirstLoad = true;

    private final Context mContext;

    public EquipmentPresenter(FireBrigadeUtils fireBrigadeUtils, EquipmentRequestImpl mEquipmentRequest, EquipmentContract.View mEquipmentView, Context context) {
        this.mFireBrigadeUtils = fireBrigadeUtils;
        this.mFireBrigadeId = mFireBrigadeUtils.getFireBrigadeIdFromSharedPreferences();
        this.mEquipmentRequest = mEquipmentRequest;
        this.mEquipmentView = mEquipmentView;
        this.mEquipmentView.setPresenter(this);
        this.mContext = context;
    }

    @Override
    public void start() {
        loadEquipment(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadEquipment(boolean forceUpdade) {
        mEquipmentView.setLoadingIndicator(true);
        mEquipmentRequest.getFireBrigadeEquipmentWithCarInfo(mFireBrigadeId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                List<EquipmentAdapterObj> objList = makeObjectsFromResponse(data);
                mEquipmentView.showEquipment(objList);
                mEquipmentView.hideLoadingIndicator();
            }

            private List<EquipmentAdapterObj> makeObjectsFromResponse(String data) {
                Gson gson = new Gson();
                EquipmentAdapterObj[] equipmentAdapterObjArray = gson.fromJson(data, EquipmentAdapterObj[].class);
                return Arrays.asList(equipmentAdapterObjArray);
            }

            @Override
            public void onError(int code) {
                if (code == 404) {
                    mEquipmentView.hideLoadingIndicator();
                    mEquipmentView.showNoEquipment();
                } else {
                    mEquipmentView.hideLoadingIndicator();
                    mEquipmentView.showLoadingEquipmentError();
                }
            }
        });
    }
}
