package com.michalrubajczyk.myfirebrigade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.view.RegisterFireBrigadeView;

/**
 * Created by Michal on 21/03/2018.
 */

public class AddFireBrigadeFragment extends Fragment implements RegisterFireBrigadeView {
    private static final String TAG = "AddFirebrigade fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_add_firebrigade, container, false);
        return view;
    }

    @Override
    public void validateFields() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void progressDialogShow() {

    }

    @Override
    public void progressDialogDismiss() {

    }
}
