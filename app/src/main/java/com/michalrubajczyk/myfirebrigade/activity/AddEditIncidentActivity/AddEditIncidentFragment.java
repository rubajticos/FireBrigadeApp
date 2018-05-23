package com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.michalrubajczyk.myfirebrigade.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
    LinearLayout mDetailsLayout;

    @BindView(R.id.add_edit_incident_add_car)
    Button mAddCarButton;

    @BindView(R.id.add_edit_incident_formLL)
    LinearLayout mIncidentFormLL;

    Unbinder unbinder;

    private AddEditIncidentContract.Presenter mPresenter;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat dateTimeFormatter;

    private Calendar mCalendar;


    public AddEditIncidentFragment() {
        this.dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        this.dateTimeFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
        this.mCalendar = Calendar.getInstance();
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
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_edit_incident_frag, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, root);

        mDate.setOnClickListener(view -> createDatePickerDialogAndSetDate(mDate));

        mDatetimeOfAlarm.setOnClickListener(view -> {
            createDateTimePickerDialogAndSetDateTime(mDatetimeOfAlarm);
        });

        mAddCarButton.setOnClickListener(view -> createIncidentCarLayout());
        return root;
    }

    private void createDatePickerDialogAndSetDate(TextView mDateTV) {
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener = (DatePicker datePicker, int myYear, int myMonth, int myDay) -> {
            mCalendar.set(myYear, myMonth, myDay);
            mDate.setText(dateFormatter.format(mCalendar.getTime()));
        };

        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.show();
    }

    private void createDateTimePickerDialogAndSetDateTime(TextView mDateTimeTV) {
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener mTimeDataSet = (TimePicker timePicker, int myHour, int myMinute) -> {
            mCalendar.set(Calendar.HOUR_OF_DAY, myHour);
            mCalendar.set(Calendar.MINUTE, myMinute);
            mDateTimeTV.setText(dateTimeFormatter.format(mCalendar.getTime()));
        };

        DatePickerDialog.OnDateSetListener mDateDataSet = (DatePicker datePicker, int myYear, int myMonth, int myDay) -> {
            mCalendar.set(myYear, myMonth, myDay);
            new TimePickerDialog(getActivity(), mTimeDataSet, hour, minute, true).show();
        };

        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                mDateDataSet,
                year, month, day);
        dialog.show();
    }

    private void createIncidentCarLayout() {
        LinearLayout singleCarLL = createCarLL(false);
        String[] cars = new String[]{"Iveco", "Steyr"}; //tymczasowo
        String[] commandors = new String[]{"Zdzisław Moskal", "Piotr Pawłowski"}; //tymczasowo
        String[] drivers = new String[]{"Paweł Pukacz", "Tomasz Szczygieł"}; //tymczasowo

        TextView carLabel = createLabelTextView(getResources().getString(R.string.add_edit_incident_car));
        Spinner carSpinner = createSpinner(cars);
        singleCarLL.addView(carLabel);
        singleCarLL.addView(carSpinner);

        TextView commandorLabel = createLabelTextView(getResources().getString(R.string.add_edit_incident_commander));
        Spinner commandorsSpinner = createSpinner(commandors);
        singleCarLL.addView(commandorLabel);
        singleCarLL.addView(commandorsSpinner);

        TextView driversLabel = createLabelTextView(getResources().getString(R.string.add_edit_incident_driver));
        Spinner driversSpinner = createSpinner(drivers);
        singleCarLL.addView(driversLabel);
        singleCarLL.addView(driversSpinner);

        View spacer = createLineSpacer();
        singleCarLL.addView(spacer);

        mDetailsLayout.addView(singleCarLL);

        // TODO: 24/05/2018 data powrotu i wyjazdu, ratownicy, sprzet
    }

    private View createLineSpacer() {
        View v = new View(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                5
        );
        lp.setMargins(0, 5, 0, 10);
        v.setLayoutParams(lp);

        v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        return v;
    }

    private TextView createLabelTextView(String string) {
        TextView textView = new TextView(getActivity());
        textView.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 10, 0, 0);
        textView.setLayoutParams(layoutParams);

        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(getResources().getColor(R.color.darkful));

        textView.setText(string);
        return textView;
    }

    private LinearLayout createCarLL(boolean horizontal) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (horizontal) {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        }
        linearLayout.setLayoutParams(lp);
        linearLayout.setVisibility(View.VISIBLE);
        return linearLayout;
    }

    private Spinner createSpinner(String[] data) {
        Spinner spinner = new Spinner(getActivity());
        spinner.setVisibility(View.VISIBLE);
        Spinner.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        spinner.setLayoutParams(lp);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, data);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        return spinner;
    }

    private TableRow createCarRow() {
        TableRow row = new TableRow(getActivity().getApplicationContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setVisibility(View.VISIBLE);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return row;
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
