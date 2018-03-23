package com.michalrubajczyk.myfirebrigade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.view.FireBrigadeWithDataView;

/**
 * Created by Michal on 21/03/2018.
 */

public class FireBrigadeWithDataFragment extends Fragment implements FireBrigadeWithDataView {
    private static final String TAG = "FireBrig_frag NO DATA";

    private TextView tmpTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fire_brigade_data, container, false);

        tmpTextView = (TextView) view.findViewById(R.id.tmp_tv);

        return view;
    }

    @Override
    public void showFireBrigade(String fireBrigade) {
        tmpTextView.setText(fireBrigade);
    }
}
