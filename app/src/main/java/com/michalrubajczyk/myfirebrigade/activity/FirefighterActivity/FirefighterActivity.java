package com.michalrubajczyk.myfirebrigade.activity.FirefighterActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;

public class FirefighterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firefighter_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.firefighterToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.activity_firefighter);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        ab.setDisplayShowHomeEnabled(true);

        FirefighterFragment firefighterFragment =
                (FirefighterFragment) getSupportFragmentManager().findFragmentById(R.id.firefighter_contentFrame);
        if (firefighterFragment == null) {
            firefighterFragment = FirefighterFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), firefighterFragment, R.id.firefighter_contentFrame
            );
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
