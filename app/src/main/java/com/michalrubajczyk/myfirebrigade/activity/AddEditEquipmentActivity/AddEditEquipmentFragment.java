package com.michalrubajczyk.myfirebrigade.activity.AddEditEquipmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.activity.AddEditCarActivity.AddEditCarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddEditEquipmentFragment extends Fragment implements AddEditEquipmentContract.View {
    private static final String TAG = "ADDEDIT EQUIPMENT FRAG";

    public static final String ARGUMENT_EDIT_EQUIPMENT_ID = "ARGUMENT_EDIT_EQUIPMENT_ID";
    @BindView(R.id.add_edit_equipment_name)
    TextInputEditText mName;

    @BindView(R.id.add_edit_equipment_type)
    Spinner mType;

    @BindView(R.id.add_edit_equipment_subtype)
    Spinner mSubtype;

    @BindView(R.id.add_edit_equipment_car_spinner)
    Spinner mCarSpinner;

    @BindView(R.id.add_edit_equipment_go_to_add_car_btn)
    Button mAddCarBtn;

    @BindView(R.id.add_edit_equipment_no_car_sectionLL)
    View mNoCarSectionLL;

    @BindView(R.id.add_edit_equipment_car_sectionLL)
    View mCarSectionLL;

    @BindView(R.id.add_edit_equipment_progress_bar)
    ProgressBar mProgressBar;
    Unbinder unbinder;

    private AddEditEquipmentContract.Presenter mPresenter;

    private List<String> mCarNames = new ArrayList<>();


    public AddEditEquipmentFragment() {
    }

    public static AddEditEquipmentFragment newInstance() {
        return new AddEditEquipmentFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void setPresenter(AddEditEquipmentContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_edit_equipment_frag, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, root);

        mAddCarBtn.setOnClickListener(view -> {
            navigateToAddCar();
            getActivity().finish();
        });

        String[] mainCategories = getResources().getStringArray(R.array.equipment_main_categories);
        ArrayAdapter<String> mainTypeAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, mainCategories);
        mainTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String[] mainFirstValueSubcategories = getResources().getStringArray(R.array.equipment_sprzet_pozarniczy_subcategories);
        String[] mainSecondValueSubcategories = getResources().getStringArray(R.array.equipment_sprzet_ratowniczy_subcategories);
        String[] mainThirdValueSubcategories = getResources().getStringArray(R.array.equipment_sprzet_ochrony_osobistej_subcategories);
        String[] mainFourthValueSubcategories = getResources().getStringArray(R.array.equipment_pozostale_subcategories);

        mType.setAdapter(mainTypeAdapter);

        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        setSubcategoriesAdapter(mainFirstValueSubcategories);

        ArrayAdapter<String> carsAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, mCarNames);

        return root;
    }

    private void setSubcategoriesAdapter(String[] subcategoriesArray) {
        ArrayAdapter<String> subtypeAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, subcategoriesArray);
        subtypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSubtype.setAdapter(subtypeAdapter);
    }

    private void navigateToAddCar() {
        Intent intent = new Intent(getContext(), AddEditCarActivity.class);
        startActivity(intent);
    }


    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setCarNames(List<String> carNamesList) {
        mCarNames = carNamesList;
        ArrayAdapter<String> carAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, mCarNames);
        carAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCarSpinner.setAdapter(carAdapter);
    }

    @Override
    public void showInwalidEquipmentError() {
        showMessage(getString(R.string.add_edit_equipment_inwalid_equipment_error));
        mName.setError(getString(R.string.add_edit_equipment_inwalid_equipment_error));
    }

    @Override
    public void showServerError() {
        showMessage(getString(R.string.add_edit_car_server_error));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showAddEquipmentError() {
        showMessage(getString(R.string.add_edit_equipment_add_equipment_error));
    }

    @Override
    public void showUpdateEquipmentError() {
        showMessage(getString(R.string.add_edit_equipment_update_equipment_error));
    }

    @Override
    public void showEquipments() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showNoCars() {
        Log.d(TAG, "called showNoCars()");
        mCarSectionLL.setVisibility(View.GONE);
        mNoCarSectionLL.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCars() {
        Log.d(TAG, "called showCars()");
        mCarSectionLL.setVisibility(View.VISIBLE);
        mNoCarSectionLL.setVisibility(View.GONE);
    }

    @Override
    public void setName(String name) {
        mName.setText(name);
    }

    @Override
    public void setTypes(String type, String subtype) {
        mType.setSelection(getIndexByValue(mType, type));
        mSubtype.setSelection(getIndexByValue(mSubtype, subtype));
    }

    private int getIndexByValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            Log.d(TAG, value + " : " + spinner.getItemAtPosition(i));
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public void setCar(String carName) {
        mCarSpinner.setSelection(getIndexByValue(mCarSpinner, carName));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
