package com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddEditIncidentFragment extends Fragment implements AddEditIncidentContract.View {
    private static final String TAG = "ADD/EDIT INCIDENT FRAG";

    public static final String ARGUMENT_EDIT_INCIDENT_ID = "ARGUMENT_EDIT_INCIDENT_ID";
    @BindView(R.id.add_edit_incident_description)
    TextInputEditText mDescription;

    @BindView(R.id.add_edit_incident_city)
    TextInputEditText mCity;

    @BindView(R.id.add_edit_incident_date)
    TextView mDate;

    @BindView(R.id.add_edit_incident_datetime_of_alarm)
    TextView mDatetimeOfAlarm;

    @BindView(R.id.add_edit_incident_details)
    TableLayout mDetailsLayout;

    @BindView(R.id.add_edit_incident_add_car)
    Button mAddCarButton;

    @BindView(R.id.add_edit_incident_formLL)
    LinearLayout mIncidentFormLL;

    Unbinder unbinder;

    private AddEditIncidentContract.Presenter mPresenter;


    public AddEditIncidentFragment() {
    }

    public static AddEditIncidentFragment newInstance() {
        return new AddEditIncidentFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(AddEditIncidentContract.Presenter presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_edit_incident_frag, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setCarNames(Map<String, Integer> carNames) {

    }

    @Override
    public void setCommandersNames(Map<String, Integer> commandersNames) {

    }

    @Override
    public void setDriversNames(Map<String, Integer> driversNames) {

    }

    @Override
    public void showInwalidIncidentError() {

    }

    @Override
    public void showNumOfPeopleExceeded(String carName) {

    }

    @Override
    public void showNoCars() {

    }

    @Override
    public void showNoFirefighters() {

    }

    @Override
    public void showNoCommanders() {

    }

    @Override
    public void showNoDrivers() {

    }

    @Override
    public void showNoEquipmentInCar() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setType(String type) {

    }

    @Override
    public void setSubtype(String subtype) {

    }

    @Override
    public void setDate(String date) {

    }

    @Override
    public void setCity(String city) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
