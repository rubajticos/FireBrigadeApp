package com.michalrubajczyk.myfirebrigade.activity.IncidentDetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.activity.AddEditCarActivity.AddEditCarActivity;
import com.michalrubajczyk.myfirebrigade.activity.AddEditCarActivity.AddEditCarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IncidentDetailFragment extends Fragment implements IncidentDetailsContract.ViewDetails {

    @NonNull
    private static final String ARGUMENT_INCIDENT_ID = "INCIDENT_ID";
    private String mIncidentId;

    @NonNull
    private static final int REQUEST_EDIT_INCIDENT = 1;

    @BindView(R.id.indident_detail_progressBar)
    ProgressBar mIndidentDetailProgressBar;

    @BindView(R.id.incident_details_description)
    TextView mIncidentDetailsDescription;

    @BindView(R.id.incident_details_city)
    TextView mIncidentDetailsCity;

    @BindView(R.id.incident_details_date)
    TextView mIncidentDetailsDate;

    @BindView(R.id.incident_details_date_of_alarm)
    TextView mIncidentDetailsDateOfAlarm;

    @BindView(R.id.incident_details_type)
    TextView mIncidentDetailsType;

    @BindView(R.id.incident_details_subtype)
    TextView mIncidentDetailsSubtype;

    @BindView(R.id.view1)
    View view1;

    @BindView(R.id.incident_detail_carInfoLL)
    LinearLayout mIncidentDetailCarInfoLL;

    @BindView(R.id.incident_detailLL)
    LinearLayout mIncidentDetailLL;

    Unbinder unbinder;

    private IncidentDetailsContract.PresenterDetails mPresenter;

    public static IncidentDetailFragment newInstance(@Nullable String incidentId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_INCIDENT_ID, incidentId);
        IncidentDetailFragment fragment = new IncidentDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.incident_details_frag, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, root);

        FloatingActionButton editFirefighterFloatingButton =
                (FloatingActionButton) getActivity().findViewById(R.id.editIncidentFab);
        editFirefighterFloatingButton.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AddEditCarActivity.class);
            Log.d("CarId", mIncidentId);
            intent.putExtra(AddEditCarFragment.ARGUMENT_EDIT_CAR_ID, mIncidentId);
            startActivity(intent);
            showMessage("Edytowanie samochodu");
        });

        return root;
    }


    @Override
    public void setPresenter(IncidentDetailsContract.PresenterDetails presenter) {
        mPresenter = presenter;
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mIndidentDetailProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mIndidentDetailProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMissingIncident() {
        showMessage(getString(R.string.incident_detail_missing_incident));
    }

    @Override
    public void setIncidentId(String incidentId) {
        this.mIncidentId = incidentId;
    }


    @Override
    public void showDescription(String description) {
        mIncidentDetailsDescription.setText(description);
    }

    @Override
    public void showCity(String city) {
        mIncidentDetailsCity.setText(city);
    }

    @Override
    public void showDate(String date) {
        mIncidentDetailsDate.setText(date);
    }

    @Override
    public void showDateOfAlarm(String dateOfAlarm) {
        mIncidentDetailsDateOfAlarm.setText(dateOfAlarm);
    }

    @Override
    public void showType(String type) {
        mIncidentDetailsType.setText(type);
    }

    @Override
    public void showSubType(String subtype) {
        mIncidentDetailsSubtype.setText(subtype);

    }

    @Override
    public void showCarInfo(String carName, String departureTime, String returnTime, String commander, String driver, String firefighters, String usedEquipment) {
        View childCarView = getLayoutInflater().inflate(R.layout.incident_details_car_incident_layout, null);
        CarViewHolder carViewHolder = new CarViewHolder(childCarView);
        carViewHolder.mIncidentDetailsCarInfoCarName.setText(carName);
        carViewHolder.mIncidentDetailsCarInfoDepartureTime.setText(departureTime);
        carViewHolder.mIncidentDetailsCarInfoReturnTime.setText(returnTime);
        carViewHolder.mIncidentDetailsCarInfoCommander.setText(commander);
        carViewHolder.mIncidentDetailsCarInfoDriver.setText(driver);
        carViewHolder.mIncidentDetailsCarInfoFirefighters.setText(firefighters);
        carViewHolder.mIncidentDetailsCarInfoUsedEquipment.setText(usedEquipment);

        mIncidentDetailCarInfoLL.addView(childCarView);
    }

    @Override
    public void setActivityName(String name) {
        getActivity().setTitle(name);
    }

    @Override
    public void showEditIncident(int incidentId) {
        showMessage("Edycja zdarzenia");
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

    static class CarViewHolder {
        @BindView(R.id.incident_details_car_info_carName)
        TextView mIncidentDetailsCarInfoCarName;
        @BindView(R.id.incident_details_car_info_departureTime)
        TextView mIncidentDetailsCarInfoDepartureTime;
        @BindView(R.id.incident_details_car_info_returnTime)
        TextView mIncidentDetailsCarInfoReturnTime;
        @BindView(R.id.incident_details_car_info_commander)
        TextView mIncidentDetailsCarInfoCommander;
        @BindView(R.id.incident_details_car_info_driver)
        TextView mIncidentDetailsCarInfoDriver;
        @BindView(R.id.incident_details_car_info_firefighters)
        TextView mIncidentDetailsCarInfoFirefighters;
        @BindView(R.id.incident_details_car_info_used_equipment)
        TextView mIncidentDetailsCarInfoUsedEquipment;

        CarViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
