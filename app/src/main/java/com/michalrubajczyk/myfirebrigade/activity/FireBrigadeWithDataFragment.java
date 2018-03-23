package com.michalrubajczyk.myfirebrigade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.view.FireBrigadeWithDataView;

/**
 * Created by Michal on 21/03/2018.
 */

public class FireBrigadeWithDataFragment extends Fragment implements FireBrigadeWithDataView {
    private static final String TAG = "FireBrig_frag NO DATA";

    private TextView infoTxt;
    private TextView name;
    private TextView voivodeship;
    private TextView district;
    private TextView community;
    private TextView city;
    private TextView ksrg;
    private TextView firefightersCount;
    private TextView carsCount;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fire_brigade_data, container, false);

        infoTxt = (TextView) view.findViewById(R.id.firebrigade_infotext);
        name = (TextView) view.findViewById(R.id.firebrigade_name);
        voivodeship = (TextView) view.findViewById(R.id.firebrigade_voivodeship);
        district = (TextView) view.findViewById(R.id.firebrigade_district);
        community = (TextView) view.findViewById(R.id.firebrigade_community);
        city = (TextView) view.findViewById(R.id.firebrigade_city);
        ksrg = (TextView) view.findViewById(R.id.firebrigade_ksrg);
        firefightersCount = (TextView) view.findViewById(R.id.firebrigade_firefighters_count);
        carsCount = (TextView) view.findViewById(R.id.firebrigade_cars_count);

        showInfo();
        return view;
    }

    public void showInfo() {
        infoTxt.setText(Html.fromHtml(getString(R.string.infotxt)));
    }

    @Override
    public void showFireBrigadeName(String name) {
        this.name.setText(Html.fromHtml(getString(R.string.name) + name));
    }

    @Override
    public void showFireBrigadeVoivodeship(String voivodeship) {
        this.voivodeship.setText(Html.fromHtml(getString(R.string.voivodeship) + voivodeship));
    }

    @Override
    public void showFireBrigadeDistrict(String district) {
        this.district.setText(Html.fromHtml(getString(R.string.district) + district));
    }

    @Override
    public void showFireBrigadeCommunity(String community) {
        this.community.setText(Html.fromHtml(getString(R.string.community) + community));
    }

    @Override
    public void showFireBrigadeCity(String city) {
        this.city.setText(Html.fromHtml(getString(R.string.city) + city));
    }

    @Override
    public void showFireBrigadeKSRG(boolean ksrg) {
        if (ksrg) {
            this.ksrg.setText(Html.fromHtml(getString(R.string.ksrg_true)));
        }
        this.ksrg.setText(Html.fromHtml(getString(R.string.ksrg_false)));
    }

    @Override
    public void showFirefightersCount(int firefighters) {
        this.firefightersCount.setText(Html.fromHtml(getString(R.string.firefighters_count) + firefighters));
    }

    @Override
    public void showCarsCount(int cars) {
        this.carsCount.setText(Html.fromHtml(getString(R.string.cars_count) + cars));
    }
}
