package com.michalrubajczyk.myfirebrigade.activity.IncidentDetailActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.IncidentRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;

public class IncidentDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_INCIDENT_ID = "INCIDENT_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident_details_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.incident_details_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        setTitle(R.string.title_activity_incident_details);

        String incidentId = getIntent().getStringExtra(EXTRA_INCIDENT_ID);

        //Incident DETAIL
        IncidentDetailFragment incidentDetailFragment = (IncidentDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.incident_details_contentFrame);

        if (incidentDetailFragment == null) {
            incidentDetailFragment = IncidentDetailFragment.newInstance(incidentId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    incidentDetailFragment, R.id.incident_details_contentFrame);
        }

        new IncidentDetailPresenter(
                new IncidentRequestImpl(this),
                incidentDetailFragment,
                Integer.parseInt(incidentId));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
