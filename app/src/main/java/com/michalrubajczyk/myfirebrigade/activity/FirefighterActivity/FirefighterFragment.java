package com.michalrubajczyk.myfirebrigade.activity.FirefighterActivity;

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
import com.michalrubajczyk.myfirebrigade.activity.AddEditFirefighterActivity.AddEditFirefighterActivity;
import com.michalrubajczyk.myfirebrigade.activity.FirefighterDetailsActivity.FirefighterDetailsActivity;
import com.michalrubajczyk.myfirebrigade.activity.RecyclerTouchListener;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FirefighterFragment extends Fragment implements FirefighterContract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "Firefighter Fragment";

    private FirefighterContract.Presenter mPresenter;

    private FirefighterAdapter mAdapter;

    private View mNoFirefighterView;

    private View mFirefighterView;

    private ImageView mNoFirefightersIcon;
    private TextView mNoFirefightersMainText;
    private TextView mNoFirefightersAddView;

    private RecyclerView recyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

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
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "Klikniecie strazaka");
                Integer firefighterId = (int) (long) recyclerView.getAdapter().getItemId(position);
                Log.d(TAG, "Id wyswietlanego: " + Integer.toString(firefighterId));
                mPresenter.openFirefighterDetails(firefighterId);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


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

        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.firefighter_swipeRefreshlayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.alarm_red));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.loadFirefighters(false);
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
    public void showFirefighters(List<Firefighter> firefightersList) {
        mAdapter.replaceData(firefightersList);

        mFirefighterView.setVisibility(View.VISIBLE);
        mNoFirefighterView.setVisibility(View.GONE);
    }

    @Override
    public void showAddFirefighter() {
        Intent intent = new Intent(getContext(), AddEditFirefighterActivity.class);
        startActivityForResult(intent, AddEditFirefighterActivity.REQUEST_ADD_FIREFIGHTER);
    }

    @Override
    public void showFirefighterDetailUi(int firefighterId) {
        Intent intent = new Intent(getContext(), FirefighterDetailsActivity.class);
        intent.putExtra(FirefighterDetailsActivity.EXTRA_FIREFIGHTER_ID, Integer.toString(firefighterId));
        startActivity(intent);
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
        showMessage(getString(R.string.firefighters_no_content));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onRefresh() {
        mPresenter.loadFirefighters(false);
    }
}
