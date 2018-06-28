package com.michalrubajczyk.myfirebrigade.activity.StatisticsActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.StatisticsRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class StatisticsActivity extends AppCompatActivity {
    public static final String EXTRA_CAR_ID = "CAR_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.statisticsToolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        setTitle(getString(R.string.title_statistics_activity));

        StatisticsFragment statisticsFragment = (StatisticsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.statistics_contentFrame);

        if (statisticsFragment == null) {
            statisticsFragment = StatisticsFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    statisticsFragment, R.id.statistics_contentFrame);
        }

        new StatisticsPresenter(
                new StatisticsRequestImpl(this),
                statisticsFragment,
                new FireBrigadeUtils(this));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
