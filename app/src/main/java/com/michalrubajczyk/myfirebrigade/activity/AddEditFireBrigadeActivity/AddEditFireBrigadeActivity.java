package com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FireBrigadeRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.AuthUserUtils;

public class AddEditFireBrigadeActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_FIREBRIGADE = 1;

    public static final String SHOULD_LOAD_DATA_FROM_SERVER_KEY = "SHOULD_LOAD_DATA_FROM_SERVER_KEY";

    private AddEditFireBrigadePresenter mAddEditFiraBrigadePresenter;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_firebrigade_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addEditFirebrigade_toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        AddEditFireBrigadeFragment addEditFireBrigadeFragment = (AddEditFireBrigadeFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addEditFireBrigade_content_frame);

        String fireBrigadeId = getIntent().getStringExtra(AddEditFireBrigadeFragment.ARGUMENT_EDIT_FIREBRIGADE_ID);
        setToolbarTitle(fireBrigadeId);

        if (addEditFireBrigadeFragment == null) {
            addEditFireBrigadeFragment = AddEditFireBrigadeFragment.newInstance();

            if (getIntent().hasExtra(AddEditFireBrigadeFragment.ARGUMENT_EDIT_FIREBRIGADE_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(AddEditFireBrigadeFragment.ARGUMENT_EDIT_FIREBRIGADE_ID, fireBrigadeId);
                addEditFireBrigadeFragment.setArguments(bundle);
            }
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditFireBrigadeFragment, R.id.addEditFireBrigade_content_frame);

        boolean shouldLoadDataFromServer = true;

        // Prevent the presenter from loading data from the repository if this is a config change.
        if (savedInstanceState != null) {
            // Data might not have loaded when the config change happen, so we saved the state.
            shouldLoadDataFromServer = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_SERVER_KEY);
        }

        mAddEditFiraBrigadePresenter = new AddEditFireBrigadePresenter(
                fireBrigadeId,
                new FireBrigadeRequestImpl(this),
                new AuthUserUtils(this),
                addEditFireBrigadeFragment,
                shouldLoadDataFromServer
        );

    }

    private void setToolbarTitle(String firebrigadeId) {
        if (firebrigadeId == null) {
            mActionBar.setTitle(R.string.add_firebrigade);
        } else {
            mActionBar.setTitle(R.string.edit_firebrigade);
        }
    }

}
