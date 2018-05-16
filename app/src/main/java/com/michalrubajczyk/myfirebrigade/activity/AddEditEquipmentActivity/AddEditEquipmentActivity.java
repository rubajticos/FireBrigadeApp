package com.michalrubajczyk.myfirebrigade.activity.AddEditEquipmentActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.EquipmentRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class AddEditEquipmentActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_EQUIPMENT = 1;

    public static final String SHOULD_LOAD_DATA_FROM_SERVER_KEY = "SHOULD_LOAD_DATA_FROM_SERVER_KEY";

    private AddEditEquipmentPresenter mAddEditEquipmentPresenter;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_equipment_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addEditEquipment_toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        AddEditEquipmentFragment addEditEquipmentFragment = (AddEditEquipmentFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addEditEquipment_content_frame);

        String equipmentId = getIntent().getStringExtra(AddEditEquipmentFragment.ARGUMENT_EDIT_EQUIPMENT_ID);
        setToolbarTitle(equipmentId);

        if (addEditEquipmentFragment == null) {
            addEditEquipmentFragment = AddEditEquipmentFragment.newInstance();

            if (getIntent().hasExtra(AddEditEquipmentFragment.ARGUMENT_EDIT_EQUIPMENT_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(AddEditEquipmentFragment.ARGUMENT_EDIT_EQUIPMENT_ID, equipmentId);
                addEditEquipmentFragment.setArguments(bundle);
            }
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditEquipmentFragment, R.id.addEditEquipment_content_frame);

        boolean shouldLoadDataFromServer = true;

        if (savedInstanceState != null) {
            shouldLoadDataFromServer = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_SERVER_KEY);
        }

        mAddEditEquipmentPresenter = new AddEditEquipmentPresenter(
                new FirefighterRequestImpl(this),
                new EquipmentRequestImpl(this),
                addEditEquipmentFragment,
                equipmentId,
                shouldLoadDataFromServer,
                new FireBrigadeUtils(this)
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_edit_equipment, menu);
        return true;
    }

    private void setToolbarTitle(String firefighterId) {
        if (firefighterId == null) {
            mActionBar.setTitle(getString(R.string.add_edit_equipment_add_title));
        } else {
            mActionBar.setTitle(getString(R.string.add_edit_equipment_edit_title));
        }
    }

}
