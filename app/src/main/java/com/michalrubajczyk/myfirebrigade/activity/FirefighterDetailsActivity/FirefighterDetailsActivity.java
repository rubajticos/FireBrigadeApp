package com.michalrubajczyk.myfirebrigade.activity.FirefighterDetailsActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;

public class FirefighterDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_FIREFIGHTER_ID = "FIREFIGHTER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firefighter_details_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.firefighter_details_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        String firefighterId = getIntent().getStringExtra(EXTRA_FIREFIGHTER_ID);

        FirefighterDetailFragment firefighterDetailFragment = (FirefighterDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.firefighter_details_contentFrame);

        if (firefighterDetailFragment == null) {
            firefighterDetailFragment = FirefighterDetailFragment.newInstance(firefighterId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    firefighterDetailFragment, R.id.firefighter_details_contentFrame);
        }

        FirefighterDetailsTrainingFragment firefighterDetailsTrainingFragment = (FirefighterDetailsTrainingFragment) getSupportFragmentManager()
                .findFragmentById(R.id.firefighter_detail_trainings_contentFrame);

        if (firefighterDetailFragment == null) {
            firefighterDetailFragment = FirefighterDetailFragment.newInstance(firefighterId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    firefighterDetailsTrainingFragment, R.id.firefighter_detail_trainings_contentFrame);
        }

        new FirefighterDetailPresenter(
                new FirefighterRequestImpl(this),
                firefighterDetailFragment,
                Integer.parseInt(firefighterId));

//        new FirefighterDetailsTrainingPresenter()
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
