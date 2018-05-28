package com.michalrubajczyk.myfirebrigade.activity.IncidentActivity;

import android.content.Context;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.IncidentRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.IncidentFull;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.util.List;

public class IncidentPresenter implements IncidentContract.Presenter {
    public static final String TAG = "Equipment Presenter";

    private final IncidentRequestImpl mIncidentRequest;

    private final IncidentContract.View mIncidentView;

    private final FireBrigadeUtils mFireBrigadeUtils;

    private final int mFireBrigadeId;

    private boolean mFirstLoad = true;

    private final Context mContext;

    public IncidentPresenter(FireBrigadeUtils fireBrigadeUtils, IncidentRequestImpl mIncidentRequest, IncidentContract.View mIncidentView, Context context) {
        this.mFireBrigadeUtils = fireBrigadeUtils;
        this.mFireBrigadeId = mFireBrigadeUtils.getFireBrigadeIdFromSharedPreferences();
        this.mIncidentRequest = mIncidentRequest;
        this.mIncidentView = mIncidentView;
        this.mIncidentView.setPresenter(this);
        this.mContext = context;
    }

    @Override
    public void start() {
        loadIncidents(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadIncidents(boolean forceUpdade) {
        mIncidentView.setLoadingIndicator(true);
        mIncidentRequest.getIncidentsByFireBrigadeId(mFireBrigadeId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                List<IncidentFull> objList = IncidentFull.makeObjectsFromJson(data);
                mIncidentView.showIncidents(objList);
                mIncidentView.hideLoadingIndicator();
            }


            @Override
            public void onError(int code) {
                if (code == 404) {
                    mIncidentView.hideLoadingIndicator();
                    mIncidentView.showNoIncidents();
                } else {
                    mIncidentView.hideLoadingIndicator();
                    mIncidentView.showLoadingIncidentError();
                }
            }
        });
    }
}
