package com.michalrubajczyk.myfirebrigade.activity.CarActivity;

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
import com.michalrubajczyk.myfirebrigade.activity.AddEditCarActivity.AddEditCarActivity;
import com.michalrubajczyk.myfirebrigade.activity.CarDetailActivity.CarDetailsActivity;
import com.michalrubajczyk.myfirebrigade.activity.RecyclerTouchListener;
import com.michalrubajczyk.myfirebrigade.model.dto.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class CarFragment extends Fragment implements CarContract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "Car Fragment";

    private CarContract.Presenter mPresenter;

    private CarAdapter mAdapter;

    private View mNoCarsView;

    private View mCarsView;

    private ImageView mNoCarsIcon;
    private TextView mNoCarsMainText;
    private TextView mNoCarsAddView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView recyclerView;

    public CarFragment() {
    }

    public static CarFragment newInstance() {
        return new CarFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(CarContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.car_frag, container, false);

        //Setup cars view
        mCarsView = (RelativeLayout) root.findViewById(R.id.carRL);
        recyclerView = (RecyclerView) root.findViewById(R.id.car_recyclerView);
        mAdapter = new CarAdapter(new ArrayList<>(0));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "Klikniecie samochodu");
                Integer carId = (int) (long) recyclerView.getAdapter().getItemId(position);
                Log.d(TAG, "Id wyswietlanego: " + Integer.toString(carId));
                mPresenter.openCarDetails(carId);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        //Setup noCars view
        mNoCarsView = (LinearLayout) root.findViewById(R.id.noCarsRL);
        mNoCarsIcon = (ImageView) root.findViewById(R.id.noCarsIcon);
        mNoCarsMainText = (TextView) root.findViewById(R.id.noCarsMainText);
        mNoCarsAddView = (TextView) root.findViewById(R.id.noCarsAddInfo);
        mNoCarsAddView.setOnClickListener(v -> {
            showAddCar();
            Snackbar.make(getView(), "Dodawanie samochodu", Snackbar.LENGTH_LONG).show();
        });

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.car_addFloatingButton);
        fab.setOnClickListener(v -> {
            showAddCar();
            Snackbar.make(getView(), "Dodawanie samochodu", Snackbar.LENGTH_LONG).show();
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.car_swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.alarm_red));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.loadCars(false);
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
    public void showCars(List<Car> carsList) {
        mAdapter.replaceData(carsList);

        mCarsView.setVisibility(View.VISIBLE);
        mNoCarsView.setVisibility(View.GONE);
    }

    @Override
    public void showAddCar() {
        Intent intent = new Intent(getContext(), AddEditCarActivity.class);
        startActivityForResult(intent, AddEditCarActivity.REQUEST_ADD_CAR);
    }

    @Override
    public void showCarDetailUi(int carId) {
        Intent intent = new Intent(getContext(), CarDetailsActivity.class);
        intent.putExtra(CarDetailsActivity.EXTRA_CAR_ID, Integer.toString(carId));
        startActivity(intent);
    }

    @Override
    public void showLoadingCarsError() {
        showMessage(getString(R.string.car_fragment_loading_error));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNoCars() {
        mCarsView.setVisibility(View.GONE);
        mNoCarsView.setVisibility(View.VISIBLE);
        showMessage(getString(R.string.car_fragment_loading_error));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onRefresh() {

    }
}
