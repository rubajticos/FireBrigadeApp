package com.michalrubajczyk.myfirebrigade.activity.IncidentActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.IncidentRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class IncidentActivity extends AppCompatActivity {
    public static final String EXTRA_INCIDENT_ID = "INCIDENT_ID";

    private IncidentPresenter mIncidentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.incidentToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.title_activity_incident);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        IncidentFragment incidentFragment =
                (IncidentFragment) getSupportFragmentManager().findFragmentById(R.id.incident_contentFrame);
        if (incidentFragment == null) {
            incidentFragment = IncidentFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), incidentFragment, R.id.incident_contentFrame
            );
        }

        mIncidentPresenter = new IncidentPresenter(new FireBrigadeUtils(this), new IncidentRequestImpl(this), incidentFragment, this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
