package com.michalrubajczyk.myfirebrigade.activity.FireBrigadeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity.AddEditFireBrigadeActivity;
import com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity.AddEditFireBrigadeFragment;
import com.michalrubajczyk.myfirebrigade.activity.LoginActivity.LoginActivity;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigade;

/**
 * Created by Michal on 21/03/2018.
 */

public class FireBrigadeFragment extends Fragment implements FireBrigadeContract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "FireBrigade Fragment";

    private FireBrigadeContract.Presenter mPresenter;

    private View mNoFireBrigadeView;

    private TextView mFirebrigadeDataView;

    private ImageView mNoFireBrigadeIcon;

    private TextView mNoFireBrigadeMainView;

    private TextView mNoFirebrigadeAddView;

    private LinearLayout mFireBrigadeView;

    private String mFireBrigadeId;

    private FloatingActionButton mFloatingButton;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public FireBrigadeFragment() {
    }

    public static FireBrigadeFragment newInstance() {
        return new FireBrigadeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(FireBrigadeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.firebrigade_frag, container, false);

        //Setup Firebrigade view
        mFirebrigadeDataView = (TextView) root.findViewById(R.id.firebrigadeDataTV);
        mFireBrigadeView = (LinearLayout) root.findViewById(R.id.firebrigadeLL);

        //Setuo no data view
        mNoFireBrigadeView = root.findViewById(R.id.noFirebrigadeLL);
        mNoFireBrigadeIcon = (ImageView) root.findViewById(R.id.noFirebrigadeIcon);
        mNoFireBrigadeMainView = (TextView) root.findViewById(R.id.noFireBrigadeMain);
        mNoFirebrigadeAddView = (TextView) root.findViewById(R.id.noFirebrigadeAddInfo);
        mNoFirebrigadeAddView.setOnClickListener(v -> showAddFireBrigade());


        mFloatingButton =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_firebrigade);
        mFloatingButton.setImageResource(R.drawable.ic_add_circle);
        mFloatingButton.setOnClickListener(view -> {
            showAddFireBrigade();
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.firebrigade_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.alarm_red));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.loadFireBrigade(false);
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_firebrigade_edit:
                showEditFireBrigade();
                break;
            case R.id.action_firebrigade_refresh:
                mPresenter.loadFireBrigade(false);
        }
        return true;
    }

    @Override
    public void showLoadingSpinner(boolean active) {

        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingSpinner(boolean active) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showFireBrigade(FireBrigade fireBrigade) {
        mFirebrigadeDataView.setText(Html.fromHtml(fireBrigade.toString()));
        mFireBrigadeId = Integer.toString(fireBrigade.getIdFireBrigade());
        mFireBrigadeView.setVisibility(View.VISIBLE);
        mFloatingButton.setVisibility(View.GONE);
        mNoFireBrigadeView.setVisibility(View.GONE);
    }

    @Override
    public void showAddFireBrigade() {
        Intent intent = new Intent(getContext(), AddEditFireBrigadeActivity.class);
        startActivityForResult(intent, AddEditFireBrigadeActivity.REQUEST_ADD_FIREBRIGADE);
        showMessage("Dodawanie jednostki");
    }

    @Override
    public void showEditFireBrigade() {
        Intent intent = new Intent(getContext(), AddEditFireBrigadeActivity.class);
        Log.d("mFireBrigadeId", mFireBrigadeId);
        intent.putExtra(AddEditFireBrigadeFragment.ARGUMENT_EDIT_FIREBRIGADE_ID, mFireBrigadeId);
        startActivityForResult(intent, AddEditFireBrigadeActivity.REQUEST_ADD_FIREBRIGADE);
        showMessage("Edytowanie jednostki");
    }

    @Override
    public void showLoadingFireBrigadeError() {
        showMessage("Błąd podczas ładowania jednostki");
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNoFireBrigade() {
        mFireBrigadeView.setVisibility(View.GONE);
        mFloatingButton.setVisibility(View.VISIBLE);
        mNoFireBrigadeView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLogin() {
        Log.d(TAG, "showLogin() - przejscie do logowania");
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        onDestroy();
    }

    @Override
    public void onRefresh() {
        mPresenter.loadFireBrigade(false);
    }
}