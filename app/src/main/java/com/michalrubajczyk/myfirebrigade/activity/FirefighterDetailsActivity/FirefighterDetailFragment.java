package com.michalrubajczyk.myfirebrigade.activity.FirefighterDetailsActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;

import java.util.Date;

public class FirefighterDetailFragment extends Fragment implements FirefighterDetailsContract.ViewDetails {

    @NonNull
    private static final String ARGUMENT_FIREFIGHTER_ID = "FIREFIGHTER_ID";

    @NonNull
    private static final int REQUEST_EDIT_FIREFIGHTER = 1;

    private FirefighterDetailsContract.PresenterDetails mPresenter;

    private TextView mFirefighterName;
    private TextView mFirefighterLastName;
    private TextView mFirefighterBirthdayDate;
    private TextView mFirefighterExpiryMedicalTestDate;

    private ProgressBar mProgressBar;

    public static FirefighterDetailFragment newInstance(@Nullable String firefighterId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_FIREFIGHTER_ID, firefighterId);
        FirefighterDetailFragment fragment = new FirefighterDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.firefighter_details_frag, container, false);
        setHasOptionsMenu(true);
        mFirefighterName = (TextView) root.findViewById(R.id.firefighter_detailName);
        mFirefighterLastName = (TextView) root.findViewById(R.id.firefighter_detailLastName);
        mFirefighterBirthdayDate = (TextView) root.findViewById(R.id.firefighter_detailBirthdayDate);
        mFirefighterExpiryMedicalTestDate = (TextView) root.findViewById(R.id.firefighter_detailExpiryMedicalTestDate);
        mProgressBar = (ProgressBar) root.findViewById(R.id.firefighter_details_ProgressBar);

        FloatingActionButton editFirefighterFloatingButton =
                (FloatingActionButton) getActivity().findViewById(R.id.editFirefighterFab);
        editFirefighterFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage("Edycja strażaka");
            }
        });

        return root;
    }


    @Override
    public void setPresenter(FirefighterDetailsContract.PresenterDetails presenter) {
        mPresenter = presenter;
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMissingFirefighter() {
        showMessage(getString(R.string.firefighter_detail_missing));
    }

    @Override
    public void hideName() {
        mFirefighterName.setVisibility(View.GONE);
    }

    @Override
    public void showName(String name) {
        mFirefighterName.setVisibility(View.VISIBLE);
        mFirefighterName.setText(name);
    }

    @Override
    public void hideLastName() {
        mFirefighterLastName.setVisibility(View.GONE);

    }

    @Override
    public void showLastName(String lastName) {
        mFirefighterLastName.setVisibility(View.VISIBLE);
        mFirefighterLastName.setText(lastName);
    }

    @Override
    public void hideBirthday() {
        mFirefighterBirthdayDate.setVisibility(View.GONE);
    }

    @Override
    public void showBirthday(String birthdayDate) {
        mFirefighterBirthdayDate.setVisibility(View.VISIBLE);
        mFirefighterBirthdayDate.setText(birthdayDate);
    }

    @Override
    public void hideExpiryMedicalTest() {
        mFirefighterExpiryMedicalTestDate.setVisibility(View.GONE);
    }

    @Override
    public void showExpiryMedicalTest(String medicalTestsDate) {
        mFirefighterExpiryMedicalTestDate.setVisibility(View.VISIBLE);
        mFirefighterExpiryMedicalTestDate.setText(medicalTestsDate);
    }

    @Override
    public void showEditFirefighter(int firefighterId) {
        showMessage("Edycja strażaka");
    }

    @Override
    public boolean isActive() {
        return false;
    }


}
