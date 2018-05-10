package com.michalrubajczyk.myfirebrigade.activity.CarDetailActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;

public class CarDetailFragment extends Fragment implements CarDetailsContract.ViewDetails {

    @NonNull
    private static final String ARGUMENT_CAR_ID = "CAR_ID";

    @NonNull
    private static final int REQUEST_EDIT_CAR = 1;

    private CarDetailsContract.PresenterDetails mPresenter;

    private String mCarId;
    private TextView mCarModel;
    private TextView mCarOperationalNumbers;
    private TextView mCarPlates;
    private TextView mCarType;
    private TextView mCarWater;
    private TextView mCarFoam;
    private TextView mCarMotorPumpPerformance;
    private TextView mCarNumberOfSeats;

    private ProgressBar mProgressBar;

    public static CarDetailFragment newInstance(@Nullable String firefighterId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_CAR_ID, firefighterId);
        CarDetailFragment fragment = new CarDetailFragment();
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
        View root = inflater.inflate(R.layout.car_details_frag, container, false);
        setHasOptionsMenu(true);
        mCarModel = (TextView) root.findViewById(R.id.car_detailModel);
        mCarOperationalNumbers = (TextView) root.findViewById(R.id.car_detailOperationalNumbers);
        mCarPlates = (TextView) root.findViewById(R.id.car_detailPlates);
        mCarType = (TextView) root.findViewById(R.id.car_detailType);
        mCarWater = (TextView) root.findViewById(R.id.car_detailWater);
        mCarFoam = (TextView) root.findViewById(R.id.car_detailFoam);
        mCarMotorPumpPerformance = (TextView) root.findViewById(R.id.car_detailMotorPumpPerformance);
        mCarNumberOfSeats = (TextView) root.findViewById(R.id.car_detailNumberOfSeats);
        mProgressBar = (ProgressBar) root.findViewById(R.id.car_details_ProgressBar);

        FloatingActionButton editFirefighterFloatingButton =
                (FloatingActionButton) getActivity().findViewById(R.id.editCarFab);
        editFirefighterFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), AddEditCarActivity.class);
//                Log.d("CarId", mCarId);
//                intent.putExtra(AddEditCarFragment.ARGUMENT_EDIT_CAR_ID, mCarId);
//                startActivity(intent);
//                showMessage("Edytowanie samochodu"); todo przejscie do edycji samochodu
            }
        });

        return root;
    }


    @Override
    public void setPresenter(CarDetailsContract.PresenterDetails presenter) {
        mPresenter = presenter;
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMissingCar() {
        showMessage(getString(R.string.firefighter_detail_missing));
    }

    @Override
    public void setCarId(String carId) {
        this.mCarId = carId;
    }

    @Override
    public void hideModel() {
        mCarModel.setVisibility(View.GONE);
    }

    @Override
    public void showModel(String name) {
        mCarModel.setVisibility(View.VISIBLE);
        mCarModel.setText(name);
    }

    @Override
    public void hideOperationalNumbers() {
        mCarOperationalNumbers.setVisibility(View.GONE);

    }

    @Override
    public void showOperationalNumbers(String operationalNumbers) {
        mCarOperationalNumbers.setVisibility(View.VISIBLE);
        mCarOperationalNumbers.setText(operationalNumbers);
    }

    @Override
    public void hidePlates() {
        mCarPlates.setVisibility(View.GONE);
    }

    @Override
    public void showPlates(String birthdayDate) {
        mCarPlates.setVisibility(View.VISIBLE);
        mCarPlates.setText(birthdayDate);
    }

    @Override
    public void hideType() {
        mCarMotorPumpPerformance.setVisibility(View.GONE);
    }

    @Override
    public void showType(String type) {
        mCarType.setVisibility(View.VISIBLE);
        mCarType.setText(type);
    }

    @Override
    public void hideWater() {
        mCarWater.setVisibility(View.GONE);
    }

    @Override
    public void showWater(int water) {
        mCarWater.setVisibility(View.VISIBLE);
        mCarWater.setText(Integer.toString(water));
    }

    @Override
    public void hideFoam() {
        mCarFoam.setVisibility(View.GONE);
    }

    @Override
    public void showFoam(int foam) {
        mCarFoam.setVisibility(View.VISIBLE);
        mCarFoam.setText(Integer.toString(foam));
    }

    @Override
    public void hideMotorPumpPerformance() {
        mCarMotorPumpPerformance.setVisibility(View.GONE);
    }

    @Override
    public void showMotoPumpPerformance(int motorPumpPerformance) {
        mCarMotorPumpPerformance.setVisibility(View.VISIBLE);
        mCarMotorPumpPerformance.setText(Integer.toString(motorPumpPerformance));
    }

    @Override
    public void hideNumberOfSeats() {
        mCarNumberOfSeats.setVisibility(View.GONE);
    }

    @Override
    public void showNumberOfSeats(int numberOfSeats) {
        mCarNumberOfSeats.setVisibility(View.VISIBLE);
        mCarNumberOfSeats.setText(Integer.toString(numberOfSeats));
    }

    @Override
    public void setActivityName(String name) {
        getActivity().setTitle(name);
    }

    @Override
    public void showEditCar(int carId) {
        showMessage("Edycja samochodu");
    }

    @Override
    public boolean isActive() {
        return false;
    }


}
