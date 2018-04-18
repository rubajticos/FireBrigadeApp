package com.michalrubajczyk.myfirebrigade.activity.FirefighterActivity;

import android.app.Notification;
import android.content.Intent;
import android.media.Image;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Strings;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FirefighterFragment extends Fragment implements FirefighterContract.View {
    private static final String TAG = "Firefighter Fragment";

    private FirefighterContract.Presenter mPresenter;

    private FirefighterAdapter mAdapter;

    private View mNoFirefighterView;

    private View mFirefighterView;

    private ImageView mNoFirefightersIcon;
    private TextView mNoFirefightersMainText;
    private TextView mNoFirefightersAddView;

    private ProgressBar mProgressBar;

    private RecyclerView recyclerView;

    public FirefighterFragment() {
    }

    public static FirefighterFragment newInstance() {
        return new FirefighterFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(FirefighterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.firefighter_frag, container, false);

        //Setup firefighter view
        mFirefighterView = (RelativeLayout) root.findViewById(R.id.firefighterRL);
        recyclerView = (RecyclerView) root.findViewById(R.id.firefighter_recyclerView);
        mAdapter = new FirefighterAdapter(new ArrayList<>(0));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        //Setup noFirefighter view
        mNoFirefighterView = (LinearLayout) root.findViewById(R.id.noFirefighterRL);
        mNoFirefightersIcon = (ImageView) root.findViewById(R.id.noFirefightersIcon);
        mNoFirefightersMainText = (TextView) root.findViewById(R.id.noFirefightersMainText);
        mNoFirefightersAddView = (TextView) root.findViewById(R.id.noFirefighterAddInfo);
        mNoFirefightersAddView.setOnClickListener(v -> {
            showAddFirefighter();
            Snackbar.make(getView(), "Dodawanie strażaka", Snackbar.LENGTH_LONG).show();
        });

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.firefighter_addFloatingButton);
        fab.setOnClickListener(v -> {
            showAddFirefighter();
            Snackbar.make(getView(), "Dodawanie strażaka", Snackbar.LENGTH_LONG).show();
        });

        mProgressBar = (ProgressBar) root.findViewById(R.id.firefighterProgressBar);

        setHasOptionsMenu(true);

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
    public void showFirefighters(List<Firefighter> firefightersList) {
        mAdapter.replaceData(firefightersList);

        mFirefighterView.setVisibility(View.VISIBLE);
        mNoFirefighterView.setVisibility(View.GONE);
    }

    @Override
    public void showAddFirefighter() {
        // TODO: 18/04/2018 przejscie do dodawania strazaka
//        Intent intent = new Intent(getContext(), AddEditFirefighterActivity.class);
//        startActivityForResult(intent, AddEditFirefighterActivity.REQUEST_ADD_FIREFIGHTER);
    }

    @Override
    public void showFirefighterDetailUi(int firefighterId) {
        // TODO: 18/04/2018 przejscie do edycji strazaka
//        Intent intent = new Intent(getContext(), FirefighterDetailActivity.class);
//        intent.putExtra(FirefighterDetailActivity.EXTRA_FIREFIGHTER_ID, firefighterId);
    }

    @Override
    public void showLoadingFirefightersError() {
        showMessage(getString(R.string.firefighter_loading_error));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNoFirefighters() {
        mFirefighterView.setVisibility(View.GONE);
        mNoFirefighterView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
