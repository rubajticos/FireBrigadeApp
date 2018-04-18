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

    private View mNoFirefighterView;

    private RelativeLayout mFirefighterView;

    private ImageView mNoFirefightersIcon;
    private TextView mNoFirefightersMainText;
    private TextView mNoFirefightersAddView;

    private ProgressBar mProgressBar;

    private String mFirefighterid;

    private List<Firefighter> firefighterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FirefighterAdapter mAdapter;

    public FirefighterFragment() {
    }

    public static FirefighterFragment newInstance() {
        return new FirefighterFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(FirefighterContract.Presenter presenter) {

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

        //Setup noFirefighter view
        mNoFirefighterView = root.findViewById(R.id.noFirefighterRL);
        mNoFirefightersIcon = (ImageView) root.findViewById(R.id.noFirefightersIcon);
        mNoFirefightersMainText = (TextView) root.findViewById(R.id.noFirefightersMainText);
        mNoFirefightersAddView = (TextView) root.findViewById(R.id.noFirefighterAddInfo);
        mNoFirefightersAddView.setOnClickListener(v -> Snackbar.make(getView(), "Dodawanie strażaka", Snackbar.LENGTH_LONG).show());

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.firefighter_addFloatingButton);
        fab.setOnClickListener(v -> Snackbar.make(getView(), "Dodawanie strażaka", Snackbar.LENGTH_LONG).show());

        mProgressBar = (ProgressBar) root.findViewById(R.id.firefighterProgressBar);

        setHasOptionsMenu(true);

        recyclerView = (RecyclerView) root.findViewById(R.id.firefighter_recyclerView);
        mAdapter = new FirefighterAdapter(firefighterList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareFirefighterData();

        return root;
    }

    private void prepareFirefighterData() {
        Firefighter firefighter = new Firefighter();
        firefighter.setName("Testowy");
        firefighter.setLastName("strażak");
        firefighter.setExpiryMedicalTest(new Date(118, 4, 18));
        firefighterList.add(firefighter);

        mAdapter.notifyDataSetChanged();
    }


}
