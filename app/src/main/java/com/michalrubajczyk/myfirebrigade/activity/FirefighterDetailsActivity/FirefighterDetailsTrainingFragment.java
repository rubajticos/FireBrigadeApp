package com.michalrubajczyk.myfirebrigade.activity.FirefighterDetailsActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;

import java.util.ArrayList;
import java.util.List;

public class FirefighterDetailsTrainingFragment extends Fragment implements FirefighterDetailsContract.ViewDetailsTraining {
    private static final String TAG = "Firefighter Detail Trainings";

    @NonNull
    private static final String ARGUMENT_FIREFIGHTER_ID = "FIREFIGHTER_ID";

    private FirefighterDetailsContract.PresenterDetailsTraining mPresenter;

    private FirefighterDetailsTrainingAdapter mAdapter;

    private View mTrainingsView;
    private View mNoTrainingsView;

    private ProgressBar mProgressBar;

    private RecyclerView mRecyclerView;

    public FirefighterDetailsTrainingFragment() {
    }

    public static FirefighterDetailsTrainingFragment newInstance(@Nullable String firefighterId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_FIREFIGHTER_ID, firefighterId);
        FirefighterDetailsTrainingFragment fragment = new FirefighterDetailsTrainingFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(FirefighterDetailsContract.PresenterDetailsTraining presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.firefighter_details_training_frag, container, false);

        //Setup firefighter detail training view
        mTrainingsView = (RelativeLayout) root.findViewById(R.id.firefighter_detail_trainingRL);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.firefighter_detail_trainingRecyclerView);
        mAdapter = new FirefighterDetailsTrainingAdapter(new ArrayList<>(0));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        //Setup NO firefighter detail training view
        mNoTrainingsView = (LinearLayout) root.findViewById(R.id.noFirefighter_detail_trainingLL);

        mProgressBar = (ProgressBar) root.findViewById(R.id.firefighter_detail_trainingProgressBar);

        return root;
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
    public void showTrainings(List<FirefighterTraining> trainings) {
        mAdapter.replaceData(trainings);

        mTrainingsView.setVisibility(View.VISIBLE);
        mNoTrainingsView.setVisibility(View.GONE);
    }

    @Override
    public void showNoTrainings() {
        mTrainingsView.setVisibility(View.GONE);
        mNoTrainingsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingFirefightersError() {
        showMessage(getString(R.string.firefighter_detail_training_loading_error));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }


}
