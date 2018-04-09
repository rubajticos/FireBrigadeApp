package com.michalrubajczyk.myfirebrigade.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;
import com.michalrubajczyk.myfirebrigade.presenter.FireBrigadePresenter;
import com.michalrubajczyk.myfirebrigade.presenter.FireBrigadePresenterImpl;
import com.michalrubajczyk.myfirebrigade.view.FireBrigadeActivityView;

public class FireBrigadeActivity extends AppCompatActivity
        implements
        FireBrigadeActivityView,
        FireBrigadeFragment.MyFirebrigadeActivityListener,
        FireBrigadeEmptyFragment.MyEmptyFireBrigadeListener,
        FireBrigadeCreateFragment.MyCreateFireBrigadeListener,
        FireBrigadeEditFragment.MyEditFireBrigadeListener{

    private FireBrigadePresenter mPresenter;
    private final FragmentManager fm = getFragmentManager();
    private Fragment mCurrentFragment = null;


    public FireBrigadeActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_brigade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.fire_brigade_toolbar);
        setSupportActionBar(toolbar);
        mPresenter = new FireBrigadePresenterImpl(this, this);

        mPresenter.loadFireBrigadeByUsername();
    }

    @Override
    public void showFireBrigade(String fireBrigade) {
        showFireBrigadeDetails(fireBrigade);
    }

    @Override
    public void setFireBrigadeFragment() {
        FragmentTransaction ft = this.fm.beginTransaction();
        this.mCurrentFragment = new FireBrigadeFragment();
        ft.replace(R.id.firabrigade_container, this.mCurrentFragment);
        ft.commit();
    }

    @Override
    public void loadFireBrigadeForUser() {
        mPresenter.loadFireBrigadeByUsername();
    }

    @Override
    public void showFireBrigadeDetails(String string) {
        this.getFragmentManager().executePendingTransactions();
        ((FireBrigadeFragment) this.mCurrentFragment).setFireBrigadeDetails(string);
    }

    @Override
    public void setFireBrigadeEditFragment() {
        FragmentTransaction ft = this.fm.beginTransaction();
        this.mCurrentFragment = new FireBrigadeEditFragment();
        ft.replace(R.id.firabrigade_container, this.mCurrentFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void setEmptyFragment() {
        FragmentTransaction ft = this.fm.beginTransaction();
        this.mCurrentFragment = new FireBrigadeEmptyFragment();
        ft.replace(R.id.firabrigade_container, this.mCurrentFragment);
        ft.commit();
    }


    @Override
    public void setFireBrigadeCreateFragment() {
        FragmentTransaction ft = this.fm.beginTransaction();
        this.mCurrentFragment = new FireBrigadeCreateFragment();
        ft.replace(R.id.firabrigade_container, this.mCurrentFragment);
        ft.commit();
    }

    @Override
    public void validatePreparedFireBrigade(FireBrigadeDTO fireBrigadeDTO) {
        if (mPresenter.validateFireBrigade(fireBrigadeDTO)) {
            callValidationSuccess();
        } else {
            callValidationFailure();
        }
    }

    @Override
    public void updateFireBrigade(FireBrigadeDTO fireBrigadeDTO) {

    }

    @Override
    public void addFireBrigadeToUser(FireBrigadeDTO fireBrigadeDTO) {
        mPresenter.createFireBrigadeToUser(fireBrigadeDTO);
    }

    @Override
    public void callCreatingFailure() {
        this.getFragmentManager().executePendingTransactions();
        ((FireBrigadeCreateFragment) this.mCurrentFragment).creatingFailure();
    }

    @Override
    public void callCreatingSuccess() {
        this.getFragmentManager().executePendingTransactions();
        ((FireBrigadeCreateFragment) this.mCurrentFragment).creatingSuccess();
    }

    @Override
    public void callValidationSuccess() {
        this.getFragmentManager().executePendingTransactions();
        ((FireBrigadeCreateFragment) this.mCurrentFragment).validationSuccess();
    }

    @Override
    public void callValidationFailure() {
        this.getFragmentManager().executePendingTransactions();
        ((FireBrigadeCreateFragment) this.mCurrentFragment).validationFailure();
    }

    @Override
    public void showCreatingLoading() {
        this.getFragmentManager().executePendingTransactions();
        ((FireBrigadeCreateFragment) this.mCurrentFragment).progressDialogShow();
    }

    @Override
    public void dismissCreatingLoading() {
        this.getFragmentManager().executePendingTransactions();
        ((FireBrigadeCreateFragment) this.mCurrentFragment).progressDialogDismiss();
    }

    @Override
    public void showFireBrigadeToEdit() {
//        this.getFragmentManager().executePendingTransactions();
        ((FireBrigadeEditFragment) this.mCurrentFragment).prepareEditForm(null);
    }
}
