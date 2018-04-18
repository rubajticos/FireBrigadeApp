package com.michalrubajczyk.myfirebrigade.activity.FirefighterActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class FirefighterActivity extends AppCompatActivity {
    public static final String EXTRA_FIREBRIGADE_ID = "FIREBRIGADE_ID";


    private FirefighterPresenter mFirefighterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firefighter_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.firefighterToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.activity_firefighter);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        FirefighterFragment firefighterFragment =
                (FirefighterFragment) getSupportFragmentManager().findFragmentById(R.id.firefighter_contentFrame);
        if (firefighterFragment == null) {
            firefighterFragment = FirefighterFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), firefighterFragment, R.id.firefighter_contentFrame
            );
        }

        mFirefighterPresenter = new FirefighterPresenter(new FireBrigadeUtils(this), new FirefighterRequestImpl(this), firefighterFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
