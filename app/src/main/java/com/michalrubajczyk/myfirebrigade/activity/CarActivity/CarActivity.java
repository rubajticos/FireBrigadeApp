package com.michalrubajczyk.myfirebrigade.activity.CarActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class CarActivity extends AppCompatActivity {
    public static final String EXTRA_FIREBRIGADE_ID = "FIREBRIGADE_ID";

    private CarPresenter mCarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.carToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.title_activity_car);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        CarFragment carFragment =
                (CarFragment) getSupportFragmentManager().findFragmentById(R.id.car_contentFrame);
        if (carFragment == null) {
            carFragment = CarFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), carFragment, R.id.car_contentFrame
            );
        }

        mCarPresenter = new CarPresenter(new FireBrigadeUtils(this), new CarRequestImpl(this), carFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
