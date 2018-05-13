package com.michalrubajczyk.myfirebrigade.activity.AddEditCarActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.michalrubajczyk.myfirebrigade.R;

public class AddEditCarFragment extends Fragment implements AddEditCarContract.View {
    private static final String TAG = "ADDEDIT CAR FRAG";

    public static final String ARGUMENT_EDIT_CAR_ID = "ARGUMENT_EDIT_CAR_ID";

    private AddEditCarContract.Presenter mPresenter;

    private EditText mModel;
    private EditText mOperationalNumbers;
    private TextView mPlates;
    private TextView mType;
    private TextView mWater;
    private TextView mFoam;
    private TextView mMotorPumpPerformance;
    private TextView mNumberOfSeats;
    private ProgressBar mProgressBar;

    public AddEditCarFragment() {
    }

    public static AddEditCarFragment newInstance() {
        return new AddEditCarFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void setPresenter(AddEditCarContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_edit_car_frag, container, false);
        mModel = (EditText) root.findViewById(R.id.add_edit_car_model);
        mOperationalNumbers = (EditText) root.findViewById(R.id.add_edit_car_operational_numbers);
        mPlates = (EditText) root.findViewById(R.id.add_edit_car_plates);
        mType = (EditText) root.findViewById(R.id.add_edit_car_type);
        mWater = (EditText) root.findViewById(R.id.add_edit_car_water);
        mFoam = (EditText) root.findViewById(R.id.add_edit_car_foam);
        mMotorPumpPerformance = (EditText) root.findViewById(R.id.add_edit_car_motor_pump_performance);
        mNumberOfSeats = (EditText) root.findViewById(R.id.add_edit_car_number_of_seats);

        mProgressBar = (ProgressBar) root.findViewById(R.id.add_edit_car_progress_bar);

        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_edit_car_save:
                saveAction();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveAction() {
        Log.d(TAG, "Zapis!!!!");
        String model = mModel.getText().toString();
        String operationalNumbers = mOperationalNumbers.getText().toString();
        String plates = mPlates.getText().toString();
        String type = mType.getText().toString();

        int water;
        if (!Strings.isNullOrEmpty(mWater.getText().toString())) {
            water = Integer.parseInt(mWater.getText().toString());
        } else {
            water = 0;
        }

        int foam;
        if (!Strings.isNullOrEmpty(mFoam.getText().toString())) {
            foam = Integer.parseInt(mFoam.getText().toString());
        } else {
            foam = 0;
        }

        int motorPumpPerformance;
        if (!Strings.isNullOrEmpty(mMotorPumpPerformance.getText().toString())) {
            motorPumpPerformance = Integer.parseInt(mMotorPumpPerformance.getText().toString());
        } else {
            motorPumpPerformance = 0;
        }

        int numOfSeats;
        if (!Strings.isNullOrEmpty(mNumberOfSeats.getText().toString())) {
            numOfSeats = Integer.parseInt(mNumberOfSeats.getText().toString());
        } else {
            numOfSeats = 0;
        }

        mPresenter.saveCar(model, operationalNumbers, plates, type, water, foam, motorPumpPerformance, numOfSeats);
    }


    @Override
    public void showInwalidCarError() {
        Snackbar.make(getView(), R.string.add_edit_car_invalid_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showServerError() {
        Snackbar.make(getView(), R.string.add_edit_car_server_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showUpdateCarError() {
        Snackbar.make(getView(), R.string.add_edit_car_update_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showAddCarError() {
        Snackbar.make(getView(), R.string.add_edit_car_create_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showPopulateCarError() {
        Snackbar.make(getView(), R.string.add_edit_car_populate_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showAddCarSuccess() {
        Snackbar.make(getView(), R.string.add_edit_car_create_success, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showUpdateCarSuccess() {
        Snackbar.make(getView(), R.string.add_edit_car_update_success, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showCars() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setModel(String name) {
        mModel.setText(name);
    }

    @Override
    public void setOperationalNumbers(String operationalNumbers) {
        mOperationalNumbers.setText(operationalNumbers);
    }

    @Override
    public void setPlates(String plates) {
        mPlates.setText(plates);
    }

    @Override
    public void setType(String type) {
        mType.setText(type);
    }

    @Override
    public void setWater(int water) {
        mWater.setText(Integer.toString(water));
    }

    @Override
    public void setFoam(int foam) {
        mFoam.setText(Integer.toString(foam));
    }

    @Override
    public void setMotorPumpPerformance(int motorPumpPerformance) {
        mMotorPumpPerformance.setText(Integer.toString(motorPumpPerformance));
    }

    @Override
    public void setNumberOfSeats(int numberOfSeats) {
        mNumberOfSeats.setText(Integer.toString(numberOfSeats));
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

}
