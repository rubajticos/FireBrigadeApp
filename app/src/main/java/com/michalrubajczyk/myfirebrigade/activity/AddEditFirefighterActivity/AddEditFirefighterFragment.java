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
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            TableRow row = createTrainingRow();
            Spinner trainingNameSpinner = createTrainingSpinner();
            createArrayAdapterForTrainingSpinner(trainingNameSpinner, mTrainingNames);
            TextView trainingDate = createTrainingTextView();
            trainingDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            trainingDate.setText(i2 + "." + i1 + "." + i);
                        }
                    };
                    createAndShowDatePickerDialog(mDateSetListener);
                }
            });

            Button removeTrainingButton = createButtonForRemoveTraining();
            removeTrainingButton.setOnClickListener(view1 -> {
                mTrainingsTL.removeView(row);
            });

            row.addView(trainingNameSpinner);
            row.addView(trainingDate);
            row.addView(removeTrainingButton);
            mTrainingsTL.addView(row);
        });

        setHasOptionsMenu(true);
        return root;
    }


    private TableRow createTrainingRow() {
        TableRow row = new TableRow(getActivity().getApplicationContext());
        row.setVisibility(View.VISIBLE);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        row.setLayoutParams(lp);
        return row;
    }

    private Spinner createTrainingSpinner() {
        Spinner spinner = new Spinner(getActivity().getApplicationContext());
        createArrayAdapterForTrainingSpinner(spinner, mTrainingNames);
        spinner.setVisibility(View.VISIBLE);
        return spinner;
    }

    private void createArrayAdapterForTrainingSpinner(Spinner spinner, List<String> valuesList) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, valuesList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private TextView createTrainingTextView() {
        TextView textView = new TextView(getActivity().getApplicationContext());
        textView.setText("Training Date");
        textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 4));
        textView.setBackground(getResources().getDrawable(R.drawable.border_bottom));
        textView.setVisibility(View.VISIBLE);
        return textView;
    }

    private Button createButtonForRemoveTraining() {
        Button removeButton = new Button(getActivity().getApplicationContext());
        removeButton.setBackgroundResource(R.drawable.ic_remove_circle_black_24dp);
        removeButton.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 4));
        removeButton.setVisibility(View.VISIBLE);
        return removeButton;
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
                Log.d(TAG, "Zapis!!!!");
                mPresenter.saveFirefighter(mName.getText().toString(), mLastName.getText().toString(), mBirthday.getText().toString(), mExpiryMedicalTests.getText().toString(), prepareTrainingsMap());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private HashMap<String, String> prepareTrainingsMap() {
        HashMap<String, String> trainings = new HashMap<>();
        List<TableRow> trainingRows = prepareTrainingRows();
        for (TableRow row :
                trainingRows) {
            Spinner spinner = (Spinner) row.getChildAt(0);
            String trainingName = spinner.getSelectedItem().toString();
            TextView trainingDateTv = (TextView) row.getChildAt(1);
            String trainingDate = trainingDateTv.getText().toString();

            trainings.put(trainingName, trainingDate);
        }
        return trainings;
    }

    private List<TableRow> prepareTrainingRows() {
        List<TableRow> trainnigRows = new ArrayList<>();
        for (int i = 0; i < mTrainingsTL.getChildCount(); i++) {
            trainnigRows.add((TableRow) mTrainingsTL.getChildAt(i));
        }
        return trainnigRows;
    }


    @Override
    public void showInwalidFirefighterError() {
        Snackbar.make(mName, R.string.add_firebrigade_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showInwalidTrainingsError() {
        Snackbar.make(mName, R.string.add_firebrigade_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showServerError() {
        Snackbar.make(mName, R.string.add_edit_firefighter_server_error, Snackbar.LENGTH_LONG).show();
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
    public void setTrainings(HashMap<String, String> trainings) {
        for (Map.Entry<String, String> entry : trainings.entrySet()) {
            String trainingName = entry.getKey();
            String trainingDate = entry.getValue();

            TableRow trainingRow = createTrainingRow();
            Spinner trainingSpinner = createTrainingSpinner();
            trainingSpinner.setSelection(getIndexByValue(trainingSpinner, trainingName));

            TextView dateTextView = createTrainingTextView();
            dateTextView.setText(trainingDate);

            Button removeItemBtn = createButtonForRemoveTraining();
            removeItemBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTrainingsTL.removeView(trainingRow);
                }
            });

            trainingRow.addView(trainingSpinner);
            trainingRow.addView(dateTextView);
            trainingRow.addView(removeItemBtn);
            mTrainingsTL.addView(trainingRow);
        }
    }

    private int getIndexByValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

}
