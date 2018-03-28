package com.michalrubajczyk.myfirebrigade.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;
import com.michalrubajczyk.myfirebrigade.presenter.FireBrigadePresenter;
import com.michalrubajczyk.myfirebrigade.presenter.FireBrigadePresenterImpl;
import com.michalrubajczyk.myfirebrigade.view.FireBrigadeActivityView;

public class FireBrigadeActivity extends FragmentActivity implements FireBrigadeActivityView, FireBrigadeFragment.MyFirebrigadeActivityListener, FireBrigadeEmptyFragment.MyEmptyFireBrigadeListener {

    private FireBrigadePresenter mPresenter;
    private final FragmentManager fm = getFragmentManager();
    private Fragment mCurrentFragment = null;

    private FireBrigadeDTO mFireBrigade;

    public FireBrigadeActivity() {
        mPresenter = new FireBrigadePresenterImpl(FireBrigadeActivity.this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_brigade);
        mPresenter = new FireBrigadePresenterImpl(FireBrigadeActivity.this, this);

        mPresenter.loadFireBrigadeByUsername();
    }

    @Override
    public void setFireBrigadeFragment() {
        FragmentTransaction ft = this.fm.beginTransaction();
        this.mCurrentFragment = new FireBrigadeFragment();
        ft.replace(R.id.firabrigade_container, this.mCurrentFragment);
        ft.commit();
    }

    @Override
    public void prepareAndShowFireBrigadeData() {
        String brigade = this.mFireBrigade.toString();
        showFireBrigadeDetails(brigade);
    }

    @Override
    public void showFireBrigadeDetails(String string) {
        this.getFragmentManager().executePendingTransactions();
        ((FireBrigadeFragment) this.mCurrentFragment).setFireBrigadeDetails(string);
    }

    @Override
    public void setEmptyFragment() {
        FragmentTransaction ft = this.fm.beginTransaction();
        this.mCurrentFragment = new FireBrigadeEmptyFragment();
        ft.replace(R.id.firabrigade_container, this.mCurrentFragment);
        ft.commit();
    }

    @Override
    public void setFireBrigade(FireBrigadeDTO fireBrigadeDTO) {
        this.mFireBrigade = fireBrigadeDTO;
    }

    @Override
    public void setFireBrigadeCreateFragment() {
        FragmentTransaction ft = this.fm.beginTransaction();
        this.mCurrentFragment = new FireBrigadeCreateFragment();
        ft.replace(R.id.firabrigade_container, this.mCurrentFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
