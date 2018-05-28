package com.michalrubajczyk.myfirebrigade.activity.IncidentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.activity.AddEditIncidentActivity.AddEditIncidentActivity;
import com.michalrubajczyk.myfirebrigade.activity.RecyclerTouchListener;
import com.michalrubajczyk.myfirebrigade.model.dto.IncidentFull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class IncidentFragment extends Fragment implements IncidentContract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "Incident Fragment";

    private IncidentContract.Presenter mPresenter;

    private IncidentAdapter mAdapter;

    private View mNoIncidentsView;

    private View mIncidentsView;

    private ImageView mNoIncidentIcon;
    private TextView mNoIncidentMainText;
    private TextView mNoIncidentAddView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView recyclerView;

    public IncidentFragment() {
    }

    public static IncidentFragment newInstance() {
        return new IncidentFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(IncidentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.incident_frag, container, false);

        //Setup cars view
        mIncidentsView = (RelativeLayout) root.findViewById(R.id.incidentRL);
        recyclerView = (RecyclerView) root.findViewById(R.id.incident_recyclerView);
        mAdapter = new IncidentAdapter(new ArrayList<>(0), new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "Klikniecie zdarzenia");
                Integer equipmentId = (int) recyclerView.getAdapter().getItemId(position);
                Log.d(TAG, "Id kliknietego zdarzenia: " + Integer.toString(equipmentId));
                showDetailsIncident(Integer.toString(equipmentId));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        //Setup noCars view
        mNoIncidentsView = (LinearLayout) root.findViewById(R.id.noIncidentRL);
        mNoIncidentIcon = (ImageView) root.findViewById(R.id.noIncidentIcon);
        mNoIncidentMainText = (TextView) root.findViewById(R.id.noIncidentMainText);
        mNoIncidentAddView = (TextView) root.findViewById(R.id.noIncidentAddInfo);
        mNoIncidentAddView.setOnClickListener(v -> {
            showAddIncident();
            Snackbar.make(getView(), "Dodawanie zdarzenia", Snackbar.LENGTH_LONG).show();
        });

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.incident_addFloatingButton);
        fab.setOnClickListener(v -> {
            showAddIncident();
            Snackbar.make(getView(), "Dodawanie zdarzenia", Snackbar.LENGTH_LONG).show();
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.incident_swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.alarm_red));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.loadIncidents(false);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mSwipeRefreshLayout.setEnabled(mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingIndicator() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showIncidents(List<IncidentFull> incidentFullList) {
        mAdapter.replaceData(incidentFullList);

        mIncidentsView.setVisibility(View.VISIBLE);
        mNoIncidentsView.setVisibility(View.GONE);
    }

    @Override
    public void showAddIncident() {
        Intent intent = new Intent(getContext(), AddEditIncidentActivity.class);
        startActivityForResult(intent, AddEditIncidentActivity.REQUEST_ADD_INCIDENT);
    }

    @Override
    public void showDetailsIncident(String incidentId) {
//        Intent intent = new Intent(getContext(), AddEditEquipmentActivity.class);
//        intent.putExtra(AddEditEquipmentFragment.ARGUMENT_EDIT_EQUIPMENT_ID, incidentId);
//        startActivity(intent);
        // TODO: 28/05/2018 przechodzenie do szczegolow
    }

    @Override
    public void showLoadingIncidentError() {
        showMessage(getString(R.string.incident_fragment_loading_error));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNoIncidents() {
        mIncidentsView.setVisibility(View.GONE);
        mNoIncidentsView.setVisibility(View.VISIBLE);
        showMessage(getString(R.string.incident_fragment_no_incident));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onRefresh() {
        mPresenter.loadIncidents(false);
    }
}
