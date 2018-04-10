package com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;

public class AddEditFireBrigadeFragment extends Fragment implements AddEditFireBrigadeContract.View {

    public static final String ARGUMENT_EDIT_FIREBRIGADE_ID = "ARGUMENT_EDIT_FIREBRIGADE_ID";

    private AddEditFireBrigadeContract.Presenter mPresenter;

    private TextView mName;
    private TextView mVoivodeship;
    private TextView mDistrict;
    private TextView mCommunity;
    private TextView mCity;
    private CheckBox mKsrg;

    private Button mSaveButton;

    public AddEditFireBrigadeFragment() {
    }

    public static AddEditFireBrigadeFragment newInstance() {
        return new AddEditFireBrigadeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(AddEditFireBrigadeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_edit_firebrigade_frag, container, false);
        mName = (TextView) root.findViewById(R.id.firebrigade_name);
        mVoivodeship = (TextView) root.findViewById(R.id.firebrigade_voivodeship);
        mDistrict = (TextView) root.findViewById(R.id.firebrigade_district);
        mCommunity = (TextView) root.findViewById(R.id.firebrigade_community);
        mCity = (TextView) root.findViewById(R.id.firebrigade_city);
        mKsrg = (CheckBox) root.findViewById(R.id.firebrigade_ksrg);

        mSaveButton = (Button) root.findViewById(R.id.firebrigade_save);
        mSaveButton.setOnClickListener(view -> {
            Log.d("Save Fbrigade btn", "wcisnieto zapisywanie jednostki");

            mPresenter.saveFireBrigade(mName.getText().toString(), mVoivodeship.getText().toString(), mDistrict.getText().toString(), mCommunity.getText().toString(), mCity.getText().toString(), mKsrg.isChecked());
        });
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void showInvalidFirebrigadeError() {
        Snackbar.make(mName, R.string.add_firebrigade_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showUpdateFirebrigadeError() {
        Snackbar.make(mName, R.string.update_firebrigade_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showFirebrigade() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setName(String name) {
        mName.setText(name);
    }

    @Override
    public void setVoivodeship(String voivodeship) {
        mVoivodeship.setText(voivodeship);
    }

    @Override
    public void setDistrict(String district) {
        mDistrict.setText(district);
    }

    @Override
    public void setCommunity(String community) {
        mCommunity.setText(community);
    }

    @Override
    public void setCity(String city) {
        mCity.setText(city);
    }

    @Override
    public void setKsrg(boolean isKsrg) {
        mKsrg.setChecked(isKsrg);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
