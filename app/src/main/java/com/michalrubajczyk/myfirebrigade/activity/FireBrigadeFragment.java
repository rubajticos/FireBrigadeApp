package com.michalrubajczyk.myfirebrigade.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;

/**
 * Created by Michal on 21/03/2018.
 */

public class FireBrigadeFragment extends Fragment {
    private static final String TAG = "FireBrig_frag NO DATA";

    private MyFirebrigadeActivityListener listener;

    private TextView infoTxt;
    private TextView details;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fire_brigade_data, container, false);
        details = (TextView) view.findViewById(R.id.firebrigade_details);
        infoTxt = (TextView) view.findViewById(R.id.firebrigade_infotext);
        showInfo();
        return view;
    }

    public interface MyFirebrigadeActivityListener {
        public void showFireBrigadeDetails(String string);
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
