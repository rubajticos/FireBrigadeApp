package com.michalrubajczyk.myfirebrigade.activity.AnalysisActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.AnalysisRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class AnalysisActivity extends AppCompatActivity {
    public static final String EXTRA_FIREBRIGADE_ID = "FIREBRIGADE_ID";

    private AnalysisPresenter mAnalysisPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.analysisToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.title_activity_analysis);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        AnalysisFragment analysisFragment =
                (AnalysisFragment) getSupportFragmentManager().findFragmentById(R.id.analysis_contentFrame);
        if (analysisFragment == null) {
            analysisFragment = AnalysisFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), analysisFragment, R.id.analysis_contentFrame
            );
        }

        mAnalysisPresenter = new AnalysisPresenter(new FireBrigadeUtils(this), new AnalysisRequestImpl(this), analysisFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
