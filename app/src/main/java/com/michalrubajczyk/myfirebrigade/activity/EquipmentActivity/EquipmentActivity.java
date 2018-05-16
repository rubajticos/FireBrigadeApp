package com.michalrubajczyk.myfirebrigade.activity.EquipmentActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.EquipmentRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class EquipmentActivity extends AppCompatActivity {
    public static final String EXTRA_EQUIPMENT_ID = "EQUIPMENT_ID";

    private EquipmentPresenter mEquipmentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.equipmentToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.title_activity_equipment);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        EquipmentFragment equipmentFragment =
                (EquipmentFragment) getSupportFragmentManager().findFragmentById(R.id.equipment_contentFrame);
        if (equipmentFragment == null) {
            equipmentFragment = EquipmentFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), equipmentFragment, R.id.equipment_contentFrame
            );
        }

        mEquipmentPresenter = new EquipmentPresenter(new FireBrigadeUtils(this), new EquipmentRequestImpl(this), equipmentFragment, this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
