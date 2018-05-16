package com.michalrubajczyk.myfirebrigade.activity.EquipmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.activity.RecyclerTouchListener;
import com.michalrubajczyk.myfirebrigade.model.dto.EquipmentAdapterObj;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class EquipmentFragment extends Fragment implements EquipmentContract.View {
    private static final String TAG = "Car Fragment";

    private EquipmentContract.Presenter mPresenter;

    private EquipmentAdapter mAdapter;

    private View mNoEquipmentView;

    private View mEquipmentView;

    private ImageView mNoEquipmentIcon;
    private TextView mNoEquipmentMainText;
    private TextView mNoEquipmentAddView;

    private ProgressBar mProgressBar;

    private RecyclerView recyclerView;

    public EquipmentFragment() {
    }

    public static EquipmentFragment newInstance() {
        return new EquipmentFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(EquipmentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.equipment_frag, container, false);

        //Setup cars view
        mEquipmentView = (RelativeLayout) root.findViewById(R.id.equipmentRL);
        recyclerView = (RecyclerView) root.findViewById(R.id.equipment_recyclerView);
        mAdapter = new EquipmentAdapter(new ArrayList<>(0));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Log.d(TAG, "Klikniecie samochodu");
//                Integer carId = (int) (long) recyclerView.getAdapter().getItemId(position);
//                Log.d(TAG, "Id wyswietlanego: " + Integer.toString(carId));
//                mPresenter.openCarDetails(carId); todo otwieranie edycji sprzętu
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        //Setup noCars view
        mNoEquipmentView = (LinearLayout) root.findViewById(R.id.noEquipmentRL);
        mNoEquipmentIcon = (ImageView) root.findViewById(R.id.noEquipmentIcon);
        mNoEquipmentMainText = (TextView) root.findViewById(R.id.noEquipmentMainText);
        mNoEquipmentAddView = (TextView) root.findViewById(R.id.noEquipmentAddInfo);
        mNoEquipmentAddView.setOnClickListener(v -> {
            showAddEquipment();
            Snackbar.make(getView(), "Dodawanie sprzętu", Snackbar.LENGTH_LONG).show();
        });

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.equipment_addFloatingButton);
        fab.setOnClickListener(v -> {
            showAddEquipment();
            Snackbar.make(getView(), "Dodawanie sprzętu", Snackbar.LENGTH_LONG).show();
        });

        mProgressBar = (ProgressBar) root.findViewById(R.id.equipmentProgressBar);

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
    public void showEquipment(List<EquipmentAdapterObj> equipmentList) {
        mAdapter.replaceData(equipmentList);

        mEquipmentView.setVisibility(View.VISIBLE);
        mNoEquipmentView.setVisibility(View.GONE);
    }

    @Override
    public void showAddEquipment() {
//        Intent intent = new Intent(getContext(), AddEditCarActivity.class);
//        startActivityForResult(intent, AddEditCarActivity.REQUEST_ADD_CAR);
        // TODO: 15/05/2018 dodawanie sprzetu
    }

    @Override
    public void showEditEquipment() {
        // TODO: 15/05/2018 edytowanie sprzetu
    }

    @Override
    public void showLoadingEquipmentError() {
        showMessage(getString(R.string.equipment_fragment_loading_error));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNoEquipment() {
        mEquipmentView.setVisibility(View.GONE);
        mNoEquipmentView.setVisibility(View.VISIBLE);
        showMessage(getString(R.string.equipment_fragment_loading_error));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
