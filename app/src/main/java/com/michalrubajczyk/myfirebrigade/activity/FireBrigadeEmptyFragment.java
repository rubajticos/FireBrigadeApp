package com.michalrubajczyk.myfirebrigade.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.michalrubajczyk.myfirebrigade.R;

/**
 * Created by Michal on 21/03/2018.
 */

public class FireBrigadeEmptyFragment extends Fragment {
    private static final String TAG = "FireBrig_frag NO DATA";


    private Button addFireBrigade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fire_brigade_no_data, container, false);
        addFireBrigade = (Button) view.findViewById(R.id.add_firebrigade);

        addFireBrigade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Start new Firebrigade fragment...");
            }
        });
        return view;
    }
}
