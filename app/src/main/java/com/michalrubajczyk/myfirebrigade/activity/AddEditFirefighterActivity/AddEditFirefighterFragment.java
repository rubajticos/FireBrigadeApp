package com.michalrubajczyk.myfirebrigade.activity.AddEditFirefighterActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.Training;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddEditFirefighterFragment extends Fragment implements AddEditFirefighterContract.View {
    private static final String TAG = "ADDEDIT FFIGHTER FRAG";

    public static final String ARGUMENT_EDIT_FIREFIGHTER_ID = "ARGUMENT_EDIT_FIREFIGHTER_ID";

    private AddEditFirefighterContract.Presenter mPresenter;

    private List<String> mTrainingNames = new ArrayList<>();

    private EditText mName;
    private EditText mLastName;
    private TextView mBirthday;
    private TextView mExpiryMedicalTests;

    private TableLayout mTrainingsTL;

    private Button mAddTrainingButton;


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

    @Override
    public void setTrainingNames(List<String> mTrainingNames) {
        this.mTrainingNames = mTrainingNames;
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
                DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        mBirthday.setText(i2 + "." + i1 + "." + i);
                    }
                };
                createAndShowDatePickerDialog(mDateSetListener);
            }
        });

        mExpiryMedicalTests = (TextView) root.findViewById(R.id.add_edit_firefighter_expiryMedicalTestsDate);
        mExpiryMedicalTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        mExpiryMedicalTests.setText(i2 + "." + i1 + "." + i);
                    }
                };
                createAndShowDatePickerDialog(mDateSetListener);
            }
        });

        mTrainingsTL = (TableLayout) root.findViewById(R.id.add_edit_firefighter_trainings_TL);
        mTrainingsTL.removeAllViews();
        mAddTrainingButton = (Button) root.findViewById(R.id.add_edit_firefighter_add_training);
        mAddTrainingButton.setOnClickListener(view -> {
            Log.d(TAG, "kilknieto dodawanie wyszkolenia");
            TableRow row = new TableRow(getActivity().getApplicationContext());
            row.setVisibility(View.VISIBLE);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            Spinner trainingNameSpinner = new Spinner(getActivity().getApplicationContext());
            trainingNameSpinner.setVisibility(View.VISIBLE);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, mTrainingNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            trainingNameSpinner.setAdapter(dataAdapter);

            TextView trainingDate = new TextView(getActivity().getApplicationContext());
            trainingDate.setText("Training Date");
            trainingDate.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            trainingDate.setBackground(getResources().getDrawable(R.drawable.border_bottom));
            trainingDate.setVisibility(View.VISIBLE);

            Button removeButton = new Button(getActivity().getApplicationContext());
            removeButton.setText("-");
            removeButton.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            removeButton.setVisibility(View.VISIBLE);
            removeButton.setOnClickListener(view1 -> {
                mTrainingsTL.removeView(row);
            });

            row.addView(trainingNameSpinner);
            row.addView(trainingDate);
            row.addView(removeButton);
            mTrainingsTL.addView(row);
        });

        setHasOptionsMenu(true);
        return root;
    }

    private void createAndShowDatePickerDialog(DatePickerDialog.OnDateSetListener mDateSetListener) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.show();
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
