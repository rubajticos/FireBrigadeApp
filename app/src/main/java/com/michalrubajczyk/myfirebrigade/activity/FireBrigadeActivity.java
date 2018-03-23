package com.michalrubajczyk.myfirebrigade.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;
import com.michalrubajczyk.myfirebrigade.presenter.FireBrigadeMainPresenter;
import com.michalrubajczyk.myfirebrigade.presenter.FireBrigadeMainPresenterImpl;
import com.michalrubajczyk.myfirebrigade.view.FireBrigadeMainView;

public class FireBrigadeActivity extends AppCompatActivity implements FireBrigadeMainView {

    private FireBrigadeMainPresenter mPresenter;

    private SectionStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;

    public FireBrigadeActivity() {
        mPresenter = new FireBrigadeMainPresenterImpl(FireBrigadeActivity.this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_brigade);

        mPresenter = new FireBrigadeMainPresenterImpl(FireBrigadeActivity.this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        mSectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        mPresenter.getFireBrigadeByUsername();

    }

    private void setupViewPager(ViewPager viewPager) {
        mSectionStatePagerAdapter.addFragment(new FireBrigadeNoDataFragment(), "NO DATA fragment");
        mSectionStatePagerAdapter.addFragment(new FireBrigadeWithDataFragment(), "DATA fragment");
        mSectionStatePagerAdapter.addFragment(new AddFireBrigadeFragment(), "Add FireBrigade fragment");
        viewPager.setAdapter(mSectionStatePagerAdapter);
    }

    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void setFragmentWithFirebrigade(FireBrigadeDTO fireBrigadeDTO) {
        mViewPager.setCurrentItem(1);
        FireBrigadeWithDataFragment fragment = (FireBrigadeWithDataFragment) mSectionStatePagerAdapter.getItem(mViewPager.getCurrentItem());
        fragment.showFireBrigade(fireBrigadeDTO.toString());
    }

    @Override
    public void setFragmentNoFirebrigade() {
        mViewPager.setCurrentItem(0);
    }
}
