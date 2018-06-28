package com.michalrubajczyk.myfirebrigade.activity.StatisticsActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StatisticsFragment extends Fragment implements StatisticsContract.ViewDetails {

    @BindView(R.id.statistics_progress)
    ProgressBar mStatisticsProgress;
    @BindView(R.id.statistics_info)
    TextView mStatisticsInfo;
    @BindView(R.id.num_of_firefighters)
    TextView mNumOfFirefighters;
    @BindView(R.id.num_of_commanders)
    TextView mNumOfCommanders;
    @BindView(R.id.num_of_drivers)
    TextView mNumOfDrivers;
    @BindView(R.id.num_of_cars)
    TextView mNumOfCars;
    @BindView(R.id.num_of_equipment)
    TextView mNumOfEquipment;
    @BindView(R.id.num_of_incidents)
    TextView mNumOfIncidents;
    @BindView(R.id.num_of_fire)
    TextView mNumOfFire;
    @BindView(R.id.num_of_local_threat)
    TextView mNumOfLocalThreat;
    @BindView(R.id.num_of_trainings)
    TextView mNumOfTrainings;
    @BindView(R.id.num_of_false_alarms)
    TextView mNumOfFalseAlarms;
    @BindView(R.id.num_of_securing_area)
    TextView mNumOfSecuringArea;
    Unbinder unbinder;
    private StatisticsContract.PresenterDetails mPresenter;

    public static StatisticsFragment newInstance() {
        StatisticsFragment fragment = new StatisticsFragment();
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
        View root = inflater.inflate(R.layout.statistics_frag, container, false);
        setHasOptionsMenu(true);

        unbinder = ButterKnife.bind(this, root);
        return root;
    }


    @Override
    public void setPresenter(StatisticsContract.PresenterDetails presenter) {
        mPresenter = presenter;
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void setLoadingIndicator() {
        mStatisticsProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mStatisticsProgress.setVisibility(View.GONE);
    }

    @Override
    public void showNumOfFirefighter(String num) {
        mNumOfFirefighters.setText(num);
    }

    @Override
    public void showNumOfCommanders(String num) {
        mNumOfCommanders.setText(num);
    }

    @Override
    public void showNumOfDrivers(String num) {
        mNumOfDrivers.setText(num);
    }

    @Override
    public void showNumOfCars(String num) {
        mNumOfCars.setText(num);
    }

    @Override
    public void showNumOfTools(String num) {
        mNumOfEquipment.setText(num);
    }

    @Override
    public void showNumOfIncidents(String num) {
        mNumOfIncidents.setText(num);
    }

    @Override
    public void showNumOfFire(String num) {
        mNumOfFire.setText(num);
    }

    @Override
    public void showNumOfLocalThreat(String num) {
        mNumOfLocalThreat.setText(num);
    }

    @Override
    public void showNumOfTrainings(String num) {
        mNumOfTrainings.setText(num);
    }

    @Override
    public void showNumOfFalseAlarms(String num) {
        mNumOfFalseAlarms.setText(num);
    }

    @Override
    public void showNumOfSecureAres(String num) {
        mNumOfSecuringArea.setText(num);
    }

    @Override
    public void showError() {
        showMessage("Wystąpił błąd podczas ładowania statystyk.");
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
