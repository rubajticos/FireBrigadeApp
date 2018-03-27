package com.michalrubajczyk.myfirebrigade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.view.FireBrigadeCreateView;

/**
 * Created by Michal on 21/03/2018.
 */

public class FireBrigadeCreateFragment extends Fragment implements FireBrigadeCreateView {
    private static final String TAG = "AddFirebrigade fragment";

    private EditText fireBrigadeName;
    private EditText fireBrigadeVoivodeship;
    private EditText fireBrigadeDistrict;
    private EditText fireBrigadeCommunity;
    private EditText fireBrigadeCity;
    private CheckBox fireBrigadeKSRG;
    private Button addFireBrigade;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_add_firebrigade, container, false);

        fireBrigadeName = (EditText) view.findViewById(R.id.firebrigade_details);
        fireBrigadeVoivodeship = (EditText) view.findViewById(R.id.firebrigade_voivodeship);
        fireBrigadeDistrict = (EditText) view.findViewById(R.id.firebrigade_district);
        fireBrigadeCommunity = (EditText) view.findViewById(R.id.firebrigade_community);
        fireBrigadeCity = (EditText) view.findViewById(R.id.firebrigade_city);
        fireBrigadeKSRG = (CheckBox) view.findViewById(R.id.firebrigade_ksrg_checkbox);


        addFireBrigade = (Button) view.findViewById(R.id.add_firebrigade);
        addFireBrigade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }

    @Override
    public void validateFields() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void progressDialogShow() {

    }

    @Override
    public void progressDialogDismiss() {

    }
}
