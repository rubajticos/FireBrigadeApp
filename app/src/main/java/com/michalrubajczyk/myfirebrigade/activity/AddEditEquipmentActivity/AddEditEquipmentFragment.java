package com.michalrubajczyk.myfirebrigade.activity.AddEditEquipmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.activity.AddEditCarActivity.AddEditCarActivity;

import java.util.ArrayList;
import java.util.List;

public class AddEditEquipmentFragment extends Fragment implements AddEditEquipmentContract.View {
    private static final String TAG = "ADDEDIT EQUIPMENT FRAG";

    public static final String ARGUMENT_EDIT_EQUIPMENT_ID = "ARGUMENT_EDIT_EQUIPMENT_ID";

    private AddEditEquipmentContract.Presenter mPresenter;

    private List<String> mCarNames = new ArrayList<>();

    private EditText mName;
    private EditText mType;
    private Spinner mCarSpinner;
    private LinearLayout mCarSectionLL;
    private LinearLayout mNoCarSectionLL;
    private Button mAddCarBtn;

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
        mName = (EditText) root.findViewById(R.id.add_edit_equipment_name);
        mType = (EditText) root.findViewById(R.id.add_edit_firefighter_lastName);
        mCarSectionLL = (LinearLayout) root.findViewById(R.id.add_edit_equipment_car_sectionLL);
        mNoCarSectionLL = (LinearLayout) root.findViewById(R.id.add_edit_equipment_no_car_sectionLL);
        mAddCarBtn = (Button) root.findViewById(R.id.add_edit_equipment_go_to_add_car_btn);
        mAddCarBtn.setOnClickListener(view -> {
            navigateToAddCar();
        });
        mCarSpinner = (Spinner) root.findViewById(R.id.add_edit_equipment_car_spinner);

        setHasOptionsMenu(true);
        return root;
    }

    private void navigateToAddCar() {
        Intent intent = new Intent(getContext(), AddEditCarActivity.class);
        startActivity(intent);
    }


    @Override
    public void setCarNames(List<String> mCarNames) {

    }

    @Override
    public void showInwalidEquipmentError() {

    }

    @Override
    public void showServerError() {

    }

    @Override
    public void showAddEquipmentError() {

    }

    @Override
    public void showUpdateEquipmentError() {

    }

    @Override
    public void showEquipments() {

    }

    @Override
    public void showNoCars() {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setType(String type) {

    }

    @Override
    public void setCar(String carName) {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

}
