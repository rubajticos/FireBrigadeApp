package com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.IncidentRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class AddEditIncidentActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_INCIDENT = 1;

    public static final String SHOULD_LOAD_DATA_FROM_SERVER_KEY = "SHOULD_LOAD_DATA_FROM_SERVER_KEY";

    private AddEditIncidentPresenter mAddEditIncidentPresenter;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_incident_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addEditIncident_toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        AddEditIncidentFragment addEditIncidentFragment = (AddEditIncidentFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addEditIncident_content_frame);

        String incidentId = getIntent().getStringExtra(AddEditIncidentFragment.ARGUMENT_EDIT_INCIDENT_ID);
        setToolbarTitle(incidentId);

        if (addEditIncidentFragment == null) {
            addEditIncidentFragment = AddEditIncidentFragment.newInstance();

            if (getIntent().hasExtra(AddEditIncidentFragment.ARGUMENT_EDIT_INCIDENT_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(AddEditIncidentFragment.ARGUMENT_EDIT_INCIDENT_ID, incidentId);
                addEditIncidentFragment.setArguments(bundle);
            }
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditIncidentFragment, R.id.addEditIncident_content_frame);

        boolean shouldLoadDataFromServer = true;

        if (savedInstanceState != null) {
            shouldLoadDataFromServer = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_SERVER_KEY);
        }

        mAddEditIncidentPresenter = new AddEditIncidentPresenter(
                new IncidentRequestImpl(this),
                new FirefighterRequestImpl(this),
                new CarRequestImpl(this),
                addEditIncidentFragment,
                incidentId,
                shouldLoadDataFromServer,
                new FireBrigadeUtils(this)
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_edit_incident, menu);
        return true;
    }

    private void setToolbarTitle(String firefighterId) {
        if (firefighterId == null) {
            mActionBar.setTitle(getString(R.string.add_edit_incident_add_title));
        } else {
            mActionBar.setTitle(getString(R.string.add_edit_incident_edit_title));
        }
    }

}
