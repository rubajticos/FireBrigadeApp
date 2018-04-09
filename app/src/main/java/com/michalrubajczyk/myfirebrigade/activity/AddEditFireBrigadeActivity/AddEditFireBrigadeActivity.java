package com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FireBrigadeRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;

public class AddEditFireBrigadeActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_FIREBRIGADE = 1;

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

        Integer fireBrigadeId = getIntent().getIntExtra(AddEditFireBrigadeFragment.ARGUMENT_EDIT_FIREBRIGADE_ID, Integer.parseInt(null));

        setToolbarTitle(fireBrigadeId);

        if (addEditFireBrigadeFragment == null) {
            addEditFireBrigadeFragment = AddEditFireBrigadeFragment.newInstance();

            if (getIntent().hasExtra(AddEditFireBrigadeFragment.ARGUMENT_EDIT_FIREBRIGADE_ID)) {
                Bundle bundle = new Bundle();
                bundle.putInt(AddEditFireBrigadeFragment.ARGUMENT_EDIT_FIREBRIGADE_ID, fireBrigadeId);
                addEditFireBrigadeFragment.setArguments(bundle);
            }
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditFireBrigadeFragment, R.id.addEditFireBrigade_content_frame);

        mAddEditFiraBrigadePresenter = new AddEditFireBrigadePresenter(
                fireBrigadeId,
                new FireBrigadeRequestImpl(this),
                addEditFireBrigadeFragment
        );

    }

    private void setToolbarTitle(Integer firebrigadeId) {
        if (firebrigadeId == null) {
            mActionBar.setTitle(R.string.add_firebrigade);
        } else {
            mActionBar.setTitle(R.string.edit_firebrigade);
        }
    }

}
