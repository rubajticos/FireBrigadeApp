package com.michalrubajczyk.myfirebrigade.activity.AddEditFirefighterActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class AddEditFirefighterActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_FIREFIGHTER = 1;

    public static final String SHOULD_LOAD_DATA_FROM_SERVER_KEY = "SHOULD_LOAD_DATA_FROM_SERVER_KEY";

    private AddEditFirefighterPresenter mAddEditFirefighterPresenter;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_firefighter_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addEditFirefighter_toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        AddEditFirefighterFragment addEditFirefighterFragment = (AddEditFirefighterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addEditFireBrigade_content_frame);

        String firefighterId = getIntent().getStringExtra(AddEditFirefighterFragment.ARGUMENT_EDIT_FIREFIGHTER_ID);
        setToolbarTitle(firefighterId);

        if (addEditFirefighterFragment == null) {
            addEditFirefighterFragment = AddEditFirefighterFragment.newInstance();

            if (getIntent().hasExtra(AddEditFirefighterFragment.ARGUMENT_EDIT_FIREFIGHTER_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(AddEditFirefighterFragment.ARGUMENT_EDIT_FIREFIGHTER_ID, firefighterId);
                addEditFirefighterFragment.setArguments(bundle);
            }
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditFirefighterFragment, R.id.addEditFirefighter_content_frame);

        boolean shouldLoadDataFromServer = true;

        if (savedInstanceState != null) {
            shouldLoadDataFromServer = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_SERVER_KEY);
        }

        mAddEditFirefighterPresenter = new AddEditFirefighterPresenter(
                new FirefighterRequestImpl(this),
                addEditFirefighterFragment,
                firefighterId,
                shouldLoadDataFromServer,
                new FireBrigadeUtils(this)
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_edit_firefighter, menu);
        return true;
    }

    private void setToolbarTitle(String firefighterId) {
        if (firefighterId == null) {
            mActionBar.setTitle(getString(R.string.add_edit_firefighter_add_title));
        } else {
            mActionBar.setTitle(getString(R.string.add_edit_firefighter_edit_title));
        }
    }

}
