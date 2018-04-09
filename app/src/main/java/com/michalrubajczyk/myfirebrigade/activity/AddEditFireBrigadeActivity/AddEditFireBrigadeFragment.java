package com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity;

import android.support.v4.app.Fragment;

public class AddEditFireBrigadeFragment extends Fragment implements AddEditFireBrigadeContract.View {

    public static final String ARGUMENT_EDIT_FIREBRIGADE_ID = "ARGUMENT_EDIT_FIREBRIGADE_ID";


    public AddEditFireBrigadeFragment() {
    }

    public static AddEditFireBrigadeFragment newInstance() {
        return new AddEditFireBrigadeFragment();
    }

    @Override
    public void setPresenter(AddEditFireBrigadeContract.Presenter presenter) {

    }
}
