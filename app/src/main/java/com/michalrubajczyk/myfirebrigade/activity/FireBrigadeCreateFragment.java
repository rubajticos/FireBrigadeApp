package com.michalrubajczyk.myfirebrigade.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;
import com.michalrubajczyk.myfirebrigade.view.FireBrigadeCreateView;

/**
 * Created by Michal on 21/03/2018.
 */

public class FireBrigadeCreateFragment extends Fragment implements FireBrigadeCreateView {
    private static final String TAG = "AddFirebrigade fragment";

    private MyCreateFireBrigadeListener listener;

    private EditText fireBrigadeName;
    private EditText fireBrigadeVoivodeship;
    private EditText fireBrigadeDistrict;
    private EditText fireBrigadeCommunity;
    private EditText fireBrigadeCity;
    private CheckBox fireBrigadeKSRG;
    private Button addFireBrigade;

    private ProgressDialog progressDialog;

    private FireBrigadeDTO fireBrigadeDTO = new FireBrigadeDTO();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_create_firebrigade, container, false);

        fireBrigadeName = (EditText) view.findViewById(R.id.firebrigade_details);
        fireBrigadeVoivodeship = (EditText) view.findViewById(R.id.firebrigade_voivodeship);
        fireBrigadeDistrict = (EditText) view.findViewById(R.id.firebrigade_district);
        fireBrigadeCommunity = (EditText) view.findViewById(R.id.firebrigade_community);
        fireBrigadeCity = (EditText) view.findViewById(R.id.firebrigade_city);
        fireBrigadeKSRG = (CheckBox) view.findViewById(R.id.firebrigade_ksrg_checkbox);

        progressDialog = new ProgressDialog(getActivity());

        addFireBrigade = (Button) view.findViewById(R.id.firebrigade_add);
        addFireBrigade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareFireBrigade();
                listener.validatePreparedFireBrigade(fireBrigadeDTO);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MyCreateFireBrigadeListener) {
            listener = (MyCreateFireBrigadeListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + "musi implementować MyCreateFireBrigadeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface MyCreateFireBrigadeListener {
        public void validatePreparedFireBrigade(FireBrigadeDTO fireBrigadeDTO);

        public void addFireBrigadeToUser(FireBrigadeDTO fireBrigadeDTO);

        public void setFireBrigadeFragment();

        public void loadFireBrigadeForUser();
    }

    @Override
    public void validationSuccess() {
        Log.d(TAG, "weryfikacja danych pomyślna");
        createFireBrigade();
    }

    public void createFireBrigade() {
        listener.addFireBrigadeToUser(this.fireBrigadeDTO);
    }

    @Override
    public void validationFailure() {
        Log.d(TAG, "weryfikacja danych niepomyślna");
        Toast.makeText(getActivity().getApplicationContext(), "Weryfikacja niepomyślna, wszystkie pola muszą być wypełnione", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void creatingSuccess() {
        Log.d(TAG, "dodawanie jednostki do uzytkownika udane");
        listener.loadFireBrigadeForUser();
    }

    @Override
    public void creatingFailure() {
        Log.d(TAG, "dodawanie jednostki do uzytkownika nieudane");
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.firebrigae_create_failure), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void progressDialogShow() {
        progressDialog.setMessage("Dodawanie jednostki...");
        progressDialog.show();
    }

    @Override
    public void progressDialogDismiss() {
        progressDialog.dismiss();
    }

    private void prepareFireBrigade() {
        this.fireBrigadeDTO.setName(this.fireBrigadeName.getText().toString());
        this.fireBrigadeDTO.setVoivodeship(this.fireBrigadeVoivodeship.getText().toString());
        this.fireBrigadeDTO.setDistrict(this.fireBrigadeDistrict.getText().toString());
        this.fireBrigadeDTO.setCommunity(this.fireBrigadeCommunity.getText().toString());
        this.fireBrigadeDTO.setCity(this.fireBrigadeCity.getText().toString());
        this.fireBrigadeDTO.setKsrg(this.fireBrigadeKSRG.isChecked());
    }
}
