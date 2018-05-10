package com.michalrubajczyk.myfirebrigade.activity.CarDetailActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.CarRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;

public class CarDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_CAR_ID = "CAR_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_details_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.car_details_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        setTitle(R.string.title_activity_car_details);

        String carId = getIntent().getStringExtra(EXTRA_CAR_ID);

        //Car DETAIL
        CarDetailFragment carDetailFragment = (CarDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.car_details_contentFrame);

        if (carDetailFragment == null) {
            carDetailFragment = CarDetailFragment.newInstance(carId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    carDetailFragment, R.id.car_details_contentFrame);
        }

        new CarDetailPresenter(
                new CarRequestImpl(this),
                carDetailFragment,
                Integer.parseInt(carId));

        // TODO: 10/05/2018 wyświetlanie sprzętu na samochodzie
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
