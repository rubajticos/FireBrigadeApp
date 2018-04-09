package com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.FireBrigadeRequestImpl;

public class AddEditFireBrigadePresenter implements AddEditFireBrigadeContract.Presenter {

    private final FireBrigadeRequestImpl mFireBrigadeRequest;

    private final AddEditFireBrigadeContract.View mAddEditFirebrigadeView;

    private final Integer mFirebrigadeId;


    public AddEditFireBrigadePresenter(Integer fireBrigadeId, FireBrigadeRequestImpl fireBrigadeRequest, AddEditFireBrigadeContract.View addEditFireBrigadeFragment) {
        mFirebrigadeId = fireBrigadeId;
        mFireBrigadeRequest = fireBrigadeRequest;
        mAddEditFirebrigadeView = addEditFireBrigadeFragment;

        mAddEditFirebrigadeView.setPresenter(this);
    }

    @Override
    public void saveFireBrigade(String name, String voivodeship, String district, String community, String city, boolean ksrg) {
//        if (isNewFirebrigade()){
//            createFirebrigade();
//        } else {
//            updateFirebrigade();
        // TODO: 10/04/2018 dokonczyc
//        }
    }

    @Override
    public void start() {

    }
}
