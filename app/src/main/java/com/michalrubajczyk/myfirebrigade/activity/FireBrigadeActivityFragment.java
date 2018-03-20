package com.michalrubajczyk.myfirebrigade.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michalrubajczyk.myfirebrigade.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FireBrigadeActivityFragment extends Fragment {

    public FireBrigadeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fire_brigade, container, false);
    }
}
