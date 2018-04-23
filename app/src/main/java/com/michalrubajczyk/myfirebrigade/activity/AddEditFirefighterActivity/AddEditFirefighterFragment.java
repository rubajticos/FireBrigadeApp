package com.michalrubajczyk.myfirebrigade.activity.AddEditFirefighterActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.Training;

import java.util.Calendar;
import java.util.List;

public class AddEditFirefighterFragment extends Fragment implements AddEditFirefighterContract.View {
    private static final String TAG = "ADDEDIT FFIGHTER FRAG";

    public static final String ARGUMENT_EDIT_FIREFIGHTER_ID = "ARGUMENT_EDIT_FIREFIGHTER_ID";

    private AddEditFirefighterContract.Presenter mPresenter;

    private EditText mName;
    private EditText mLastName;
    private TextView mBirthday;
    private TextView mExpiryMedicalTests;

    private Button mSaveButton;


    public AddEditFirefighterFragment() {
    }

    public static AddEditFirefighterFragment newInstance() {
        return new AddEditFirefighterFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void setPresenter(AddEditFirefighterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_edit_firefighter_frag, container, false);
        mName = (EditText) root.findViewById(R.id.add_edit_firefighter_name);
        mLastName = (EditText) root.findViewById(R.id.add_edit_firefighter_lastName);
        mBirthday = (TextView) root.findViewById(R.id.add_edit_firefighter_birthdayDate);
        mBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        mBirthday.setText(i2 + "." + i1 + "." + i);
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        R.style.Theme_AppCompat_DayNight_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.show();
            }
        });

        mExpiryMedicalTests = (TextView) root.findViewById(R.id.add_edit_firefighter_expiryMedicalTestsDate);

        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_edit_firefighter_save:
                Log.d("TAG", "Zapis!!!!");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showInwalidFirefighterError() {
        Snackbar.make(mName, R.string.add_firebrigade_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showUpdateFirefighterError() {
        Snackbar.make(mName, R.string.update_firebrigade_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showFirefighter() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setName(String name) {
        mName.setText(name);
    }

    @Override
    public void setLastName(String lastName) {
        mLastName.setText(lastName);
    }

    @Override
    public void setBirthday(String birthday) {
        mBirthday.setText(birthday);
    }

    @Override
    public void setExpiryMedicalTests(String expiryMedicalTests) {
        mExpiryMedicalTests.setText(expiryMedicalTests);
    }

    @Override
    public void setTrainings(List<Training> trainings) {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

}
