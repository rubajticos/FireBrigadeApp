package com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity;

import android.util.Log;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FireBrigadeRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigade;
import com.michalrubajczyk.myfirebrigade.utils.AuthUserUtils;

public class AddEditFireBrigadePresenter implements AddEditFireBrigadeContract.Presenter {

    private final FireBrigadeRequestImpl mFireBrigadeRequest;

    private final AddEditFireBrigadeContract.View mAddEditFirebrigadeView;

    private final String mFirebrigadeId;

    private boolean mIsDataMissing;

    private AuthUserUtils mUserUtils;


    public AddEditFireBrigadePresenter(String fireBrigadeId,
                                       FireBrigadeRequestImpl fireBrigadeRequest,
                                       AuthUserUtils userUtils,
                                       AddEditFireBrigadeContract.View addEditFireBrigadeFragment,
                                       boolean shouldLoadDataFromServer) {
        mFirebrigadeId = fireBrigadeId;
        mFireBrigadeRequest = fireBrigadeRequest;
        mAddEditFirebrigadeView = addEditFireBrigadeFragment;
        mUserUtils = userUtils;
        mIsDataMissing = shouldLoadDataFromServer;

        mAddEditFirebrigadeView.setPresenter(this);
    }


    @Override
    public void start() {
        if (!isNewFirebrigade() && mIsDataMissing) {
            populateFirebrigade();
        }
    }

    @Override
    public void saveFireBrigade(String name, String voivodeship, String district, String community, String city, boolean ksrg) {
        Log.d("is new firebrigade", String.valueOf(isNewFirebrigade() + " " + mFirebrigadeId));
        if (isNewFirebrigade()) {
            createFirebrigade(name, voivodeship, district, community, city, ksrg);
        } else {
            updateFirebrigade(name, voivodeship, district, community, city, ksrg);
        }
    }

    @Override
    public void populateFirebrigade() {
        if (isNewFirebrigade()) {
            throw new RuntimeException("populateFirebrigade() was called but task is new");
        }
        int firebrigadeId = Integer.parseInt(mFirebrigadeId);
        mFireBrigadeRequest.getFireBrigade(firebrigadeId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                FireBrigade fireBrigade = new FireBrigade(data);
                if (mAddEditFirebrigadeView.isActive()) {
                    mAddEditFirebrigadeView.setName(fireBrigade.getName());
                    mAddEditFirebrigadeView.setVoivodeship(fireBrigade.getVoivodeship());
                    mAddEditFirebrigadeView.setDistrict(fireBrigade.getDistrict());
                    mAddEditFirebrigadeView.setCommunity(fireBrigade.getCommunity());
                    mAddEditFirebrigadeView.setCity(fireBrigade.getCity());
                    mAddEditFirebrigadeView.setKsrg(fireBrigade.isKsrg());
                }
                mIsDataMissing = false;
            }

            @Override
            public void onError(int code) {
                if (mAddEditFirebrigadeView.isActive()) {
                    mAddEditFirebrigadeView.showInvalidFirebrigadeError();
                }
            }
        });
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }


    private void createFirebrigade(String name, String voivodeship, String district, String community, String city, boolean ksrg) {
        FireBrigade newFirebrigade = new FireBrigade(name, voivodeship, district, community, city, ksrg);
        if (newFirebrigade.isEmpty()) {
            mAddEditFirebrigadeView.showInvalidFirebrigadeError();
        } else {
            mFireBrigadeRequest.addFireBrigadeToUser(newFirebrigade, mUserUtils.getUsernameFromSharedPreferences(), new DataListener() {
                @Override
                public void onSuccess(String data) {
                    mAddEditFirebrigadeView.showFirebrigade();
                }

                @Override
                public void onError(int code) {
                    mAddEditFirebrigadeView.showInvalidFirebrigadeError();
                }
            });
        }
    }


    private void updateFirebrigade(String name, String voivodeship, String district, String community, String city, boolean ksrg) {
        if (isNewFirebrigade()) {
            throw new RuntimeException("updateFirebrigade() was called but firebrigade is new");
        }
        int firebrigadeId = Integer.parseInt(mFirebrigadeId);

        FireBrigade updateFirebrigade = new FireBrigade(firebrigadeId, name, voivodeship, district, community, city, ksrg);
        mFireBrigadeRequest.updateFirebrigade(updateFirebrigade, new DataListener() {
            @Override
            public void onSuccess(String data) {
                mAddEditFirebrigadeView.showFirebrigade();
            }

            @Override
            public void onError(int code) {
                mAddEditFirebrigadeView.showUpdateFirebrigadeError();
            }
        });
    }

    private boolean isNewFirebrigade() {
        return mFirebrigadeId == null;
    }

}
