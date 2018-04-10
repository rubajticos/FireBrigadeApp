package com.michalrubajczyk.myfirebrigade.activity.FireBrigadeActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FireBrigadeRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.ActivityUtils;
import com.michalrubajczyk.myfirebrigade.utils.AuthUserUtils;

public class FireBrigadeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FireBrigadePresenter mFireBrigadePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebrigade_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.firebrigade_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.firebrigade_drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.firebrigade_nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        FireBrigadeFragment fireBrigadeFragment =
                (FireBrigadeFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fireBrigadeFragment == null) {
            fireBrigadeFragment = FireBrigadeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fireBrigadeFragment, R.id.contentFrame
            );
        }

        mFireBrigadePresenter = new FireBrigadePresenter(new FireBrigadeRequestImpl(this), new AuthUserUtils(this), fireBrigadeFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fire_brigade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.firebrigade_nav_view:
                        break;
                    case R.id.firefighters_navigation_menu_item:
                        break;
                    case R.id.cars_navigation_menu_item:
                        break;
                    case R.id.equipment_navigation_menu_item:
                        break;
                    case R.id.incidents_navigation_menu_item:
                        break;
                    case R.id.analyze_navigation_menu_item:
                        break;
                }

                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
