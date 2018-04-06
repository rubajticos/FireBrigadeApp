package com.michalrubajczyk.myfirebrigade.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.michalrubajczyk.myfirebrigade.R;

/**
 * Created by Michal on 21/03/2018.
 */

public class FireBrigadeFragment extends Fragment {
    private static final String TAG = "FireBrig_frag NO DATA";

    private MyFirebrigadeActivityListener listener;

    private TextView infoTxt;
    private TextView details;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fire_brigade_data, container, false);
        details = (TextView) view.findViewById(R.id.firebrigade_details);
        infoTxt = (TextView) view.findViewById(R.id.firebrigade_infotext);
        showInfo();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fire_brigade, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_firebrigade_edit:
                Toast.makeText(getActivity().getApplicationContext(), "Edycja jednostki", Toast.LENGTH_SHORT)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public interface MyFirebrigadeActivityListener {
        public void showFireBrigadeDetails(String string);

        public void setFireBrigadeEditFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MyFirebrigadeActivityListener) {
            listener = (MyFirebrigadeActivityListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + "musi implementować MyFirebrigadeActivityListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void setFireBrigadeDetails(String str) {
        details.setText(Html.fromHtml(str));
    }

    public void showInfo() {
        infoTxt.setText(Html.fromHtml(getString(R.string.infotxt)));
    }
}
