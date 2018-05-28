package com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.PreparedCarInIncident;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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

    @BindView(R.id.add_edit_incident_type)
    Spinner mIncidentType;

    @BindView(R.id.add_edit_incident_subtype)
    Spinner mIncidentSubtype;

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

    private String[] firefighterItems;
    private String[] commandersItems;
    private String[] driversItems;
    private HashMap<String, String[]> carsItems;


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

        String[] mainCategories = getResources().getStringArray(R.array.incident_main_categories);
        ArrayAdapter<String> mainTypeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mainCategories);
        mainTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String[] mainFirstValueSubcategories = getResources().getStringArray(R.array.incident_pozar_subcategories);
        String[] mainSecondValueSubcategories = getResources().getStringArray(R.array.incident_miejscowe_subcategories);
        String[] mainThirdValueSubcategories = getResources().getStringArray(R.array.incident_cwiczenia_subcategories);
        String[] mainFourthValueSubcategories = getResources().getStringArray(R.array.incident_falszywe_subcategories);
        String[] mainFifthValueSubcategories = getResources().getStringArray(R.array.incident_zabezpieczenie_subcategories);

        mIncidentType.setAdapter(mainTypeAdapter);
        mIncidentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        setSubcategoriesAdapter(mainFirstValueSubcategories);
                        break;
                    case 1:
                        setSubcategoriesAdapter(mainSecondValueSubcategories);
                        break;
                    case 2:
                        setSubcategoriesAdapter(mainThirdValueSubcategories);
                        break;
                    case 3:
                        setSubcategoriesAdapter(mainFourthValueSubcategories);
                        break;
                    case 4:
                        setSubcategoriesAdapter(mainFifthValueSubcategories);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        setSubcategoriesAdapter(mainFirstValueSubcategories);

        mAddCarButton.setOnClickListener(view -> createIncidentCarLayout());
        return root;
    }

    private void setSubcategoriesAdapter(String[] subcategoriesArray) {
        ArrayAdapter<String> subtypeAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, subcategoriesArray);
        subtypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIncidentSubtype.setAdapter(subtypeAdapter);
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

        //CAR and EQUIPMENT section
        TextView carLabel = createLabelTextView(getResources().getString(R.string.add_edit_incident_car));
        String[] carNames = prepareCarNames(carsItems);
        Spinner carSpinner = createSpinner(carNames);


        TextView equipmentLabel = createLabelTextView("Użyty sprzęt");
        TextView equipmentsTV = createSimpleTextView("Wybrany sprzęt");
        Button chooseEquipmentButton = createButton("Wybierz użyty sprzęt");

        carSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedValue = carSpinner.getSelectedItem().toString();
                String[] actualItems = carsItems.get(selectedValue);
                boolean[] actualCheckedItems = new boolean[actualItems.length];
                ArrayList<Integer> mActualEquipmentSelected = new ArrayList<>();
                chooseEquipmentButton.setOnClickListener(view1 -> {
                    createMultichoiceDialogWithData(actualItems, actualCheckedItems, mActualEquipmentSelected, equipmentsTV);
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //DATE OF DEPARTURE section
        TextView dateOfDepartureLabel = createLabelTextView("Data i godzina wyjazdu");
        TextView dateOfDepartureTv = createSimpleTextView("");
        dateOfDepartureTv.setOnClickListener(view -> createDateTimePickerDialogAndSetDateTime(dateOfDepartureTv));

        //DATE OF RETURN section
        TextView dateOfReturnLabel = createLabelTextView("Data i godzina powrotu");
        TextView dateOfReturnTv = createSimpleTextView("");
        dateOfReturnTv.setOnClickListener(view -> createDateTimePickerDialogAndSetDateTime(dateOfReturnTv));

        //COMMANDER section
        TextView commandorLabel = createLabelTextView(getResources().getString(R.string.add_edit_incident_commander));
        Spinner commandorsSpinner = createSpinner(commandersItems);

        //DRIVER section
        TextView driversLabel = createLabelTextView(getResources().getString(R.string.add_edit_incident_driver));
        Spinner driversSpinner = createSpinner(driversItems);

        //FIREFIGHTERS section
        boolean[] myFirefightersChecked = new boolean[firefighterItems.length];
        ArrayList<Integer> mFirefightersSelected = new ArrayList<>();
        TextView firefightersLabel = createLabelTextView("Ratownicy");
        TextView firefightersTV = createSimpleTextView("Wybrani ratownicy...");
        Button chooseFirefightersButton = createButton("Wybierz ratowników");

        chooseFirefightersButton.setOnClickListener(view -> {
            createMultichoiceDialogWithData(firefighterItems, myFirefightersChecked, mFirefightersSelected, firefightersTV);
        });

        //BUILD VIEW section
        singleCarLL.addView(carLabel);
        singleCarLL.addView(carSpinner);

        singleCarLL.addView(dateOfDepartureLabel);
        singleCarLL.addView(dateOfDepartureTv);

        singleCarLL.addView(dateOfReturnLabel);
        singleCarLL.addView(dateOfReturnTv);

        singleCarLL.addView(commandorLabel);
        singleCarLL.addView(commandorsSpinner);

        singleCarLL.addView(driversLabel);
        singleCarLL.addView(driversSpinner);

        singleCarLL.addView(firefightersLabel);
        singleCarLL.addView(chooseFirefightersButton);
        singleCarLL.addView(firefightersTV);


        singleCarLL.addView(equipmentLabel);
        singleCarLL.addView(chooseEquipmentButton);
        singleCarLL.addView(equipmentsTV);

        //END SECTION
        Button removeCarBtn = createButton("Usuń ten samochód");
        removeCarBtn.setOnClickListener(view -> {
            mDetailsLayout.removeView(singleCarLL);
        });

        singleCarLL.addView(removeCarBtn);

        View spacer = createLineSpacer();
        singleCarLL.addView(spacer);

        mDetailsLayout.addView(singleCarLL);
    }

    private String[] prepareCarNames(HashMap<String, String[]> carsItems) {
        Object[] carNames = carsItems.keySet().toArray();
        return Arrays.asList(carNames).toArray(new String[carNames.length]);
    }

    private Button createButton(String text) {
        Button button = new Button(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 10);
        button.setLayoutParams(lp);
        button.setText(text);
        button.setVisibility(View.VISIBLE);
        return button;
    }

    private TextView createSimpleTextView(String text) {
        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 10);
        textView.setLayoutParams(lp);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textView.setText(text);
        textView.setVisibility(View.VISIBLE);
        return textView;
    }

    private void createMultichoiceDialogWithData(String[] items, boolean[] checkedItems, ArrayList<Integer> mItems, TextView resultTv) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setTitle("Wybierz ratowników");
        mBuilder.setMultiChoiceItems(items, checkedItems, (dialogInterface, position, isChecked) -> {
            if (isChecked) {
                mItems.add(position);
            } else if (mItems.contains(position)) {
                mItems.remove(Integer.valueOf(position));
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", (dialogInterface, which) -> {
            String item = "";
            for (int i = 0; i < mItems.size(); i++) {
                item = item + items[mItems.get(i)];
                if (i != mItems.size() - 1) {
                    item = item + ", ";
                }
            }
            resultTv.setText(item);
        });

        mBuilder.setNegativeButton("Anuluj", (dialogInterface, i) -> dialogInterface.dismiss());

        mBuilder.setNeutralButton("Wyczyść", (dialogInterface, which) -> {
            for (int i = 0; i < checkedItems.length; i++) {
                checkedItems[i] = false;
                mItems.clear();
                resultTv.setText("");
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_edit_incident_save:
                performIncident();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void performIncident() {
        Log.d(TAG, "Dodanych samochodow:" + mDetailsLayout.getChildCount());
        int numOfCars = mDetailsLayout.getChildCount();

        String description = mDescription.getText().toString();
        String city = mCity.getText().toString();
        String date = mDate.getText().toString();
        String dateTimeOfAlarm = mDatetimeOfAlarm.getText().toString();
        String type = mIncidentType.getSelectedItem().toString();
        String subtype = mIncidentSubtype.getSelectedItem().toString();

        List<PreparedCarInIncident> listOfCars = new ArrayList<>();

        for (int i = 0; i < numOfCars; i++) {
            LinearLayout carLL = (LinearLayout) mDetailsLayout.getChildAt(i);

            Spinner car = (Spinner) carLL.getChildAt(1);
            String carName = car.getSelectedItem().toString();

            TextView dateOfDeparture = (TextView) carLL.getChildAt(3);
            String departureDateTime = dateOfDeparture.getText().toString();

            TextView dateOfReturn = (TextView) carLL.getChildAt(5);
            String returnDateTime = dateOfReturn.getText().toString();

            Spinner commander = (Spinner) carLL.getChildAt(7);
            String commanderName = commander.getSelectedItem().toString();

            Spinner driver = (Spinner) carLL.getChildAt(9);
            String driverName = driver.getSelectedItem().toString();

            TextView firefighters = (TextView) carLL.getChildAt(12);
            String firefightersName = firefighters.getText().toString();

            TextView equipment = (TextView) carLL.getChildAt(15);
            String equipments = equipment.getText().toString();
            Log.d(TAG, "koniec samochodu nr" + i);

            PreparedCarInIncident preparedCarInIncident = new PreparedCarInIncident();
            preparedCarInIncident.setCarName(carName);
            preparedCarInIncident.setDatetimeOfDeparture(departureDateTime);
            preparedCarInIncident.setDatetimeOfReturn(returnDateTime);
            preparedCarInIncident.setCommanderName(commanderName);
            preparedCarInIncident.setDriverName(driverName);
            preparedCarInIncident.setFirefightersNames(PreparedCarInIncident.makeAStringList(firefightersName, ", "));
            preparedCarInIncident.setEquipmentNames(PreparedCarInIncident.makeAStringList(equipments, ", "));

            listOfCars.add(preparedCarInIncident);
        }

        mPresenter.saveIncident(type, subtype, date, dateTimeOfAlarm, city, description, listOfCars);

        Log.d(TAG, "Przygotowane zdarzenie");
    }

    @Override
    public void setCarsNames(HashMap<String, String[]> carNames) {
        carsItems = carNames;
    }

    @Override
    public void setCommandorsNames(String[] commandersNames) {
        commandersItems = commandersNames;
    }

    @Override
    public void setDriversNames(String[] driversNames) {
        driversItems = driversNames;
    }

    @Override
    public void setFirefighterNames(String[] firefighters) {
        firefighterItems = firefighters;
    }

    @Override
    public void showInwalidIncidentError() {

    }

    @Override
    public void showNumOfPeopleExceeded(String carName) {

    }

    @Override
    public void showAddSuccess() {
        showMessage("Dodanie zdarzenia udane!");
        showIncidents();
    }

    @Override
    public void showAddError(int code) {
        showMessage("Dodawanie nieudane. Kod błędu: " + code);
    }

    @Override
    public void showIncidents() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    private void showMessage(String s) {
        Snackbar.make(getView(), s, Snackbar.LENGTH_LONG).show();
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
