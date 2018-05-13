package com.michalrubajczyk.myfirebrigade.activity.AddEditCarActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class AddEditCarActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_CAR = 1;

    public static final String SHOULD_LOAD_DATA_FROM_SERVER_KEY = "SHOULD_LOAD_DATA_FROM_SERVER_KEY";

    private AddEditCarPresenter mAddEditCarPresenter;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_car_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addEditCar_toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        AddEditCarFragment addEditCarFragment = (AddEditCarFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addEditCar_content_frame);

        String carId = getIntent().getStringExtra(AddEditCarFragment.ARGUMENT_EDIT_CAR_ID);
        setToolbarTitle(carId);

        if (addEditCarFragment == null) {
            addEditCarFragment = AddEditCarFragment.newInstance();

            if (getIntent().hasExtra(AddEditCarFragment.ARGUMENT_EDIT_CAR_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(AddEditCarFragment.ARGUMENT_EDIT_CAR_ID, carId);
                addEditCarFragment.setArguments(bundle);
            }
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditCarFragment, R.id.addEditCar_content_frame);

        boolean shouldLoadDataFromServer = true;

        if (savedInstanceState != null) {
            shouldLoadDataFromServer = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_SERVER_KEY);
        }

        mAddEditCarPresenter = new AddEditCarPresenter(
                new CarRequestImpl(this),
                addEditCarFragment,
                carId,
                shouldLoadDataFromServer,
                new FireBrigadeUtils(this)
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_edit_car, menu);
        return true;
    }

    private void setToolbarTitle(String firefighterId) {
        if (firefighterId == null) {
            mActionBar.setTitle(getString(R.string.add_edit_car_add_title));
        } else {
            mActionBar.setTitle(getString(R.string.add_edit_car_edit_title));
        }
    }

}
