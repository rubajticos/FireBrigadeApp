package com.michalrubajczyk.myfirebrigade.activity.FirefighterActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FirefighterAdapter extends RecyclerView.Adapter<FirefighterAdapter.MyViewHolder> {

    List<Firefighter> firefighterList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView lastName;
        public TextView expiryMedicalTest;

        private String medicalTestPreText;
        private String noMedicalTestDate;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.firefighter_list_item_name);
            lastName = (TextView) view.findViewById(R.id.firefighter_list_item_lastname);
            expiryMedicalTest = (TextView) view.findViewById(R.id.firefighter_list_item_expiryMedicalTest);
            medicalTestPreText = view.getResources().getString(R.string.firefighter_list_item_medical_tests_first_sentence);
            noMedicalTestDate = view.getResources().getString(R.string.firefighter_no_medical_tests);
        }
    }

    public FirefighterAdapter(List<Firefighter> firefighterList) {
        this.firefighterList = firefighterList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.firefighter_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Firefighter firefighter = firefighterList.get(position);
        holder.name.setText(firefighter.getName());
        holder.lastName.setText(firefighter.getLastName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expiryMedicalTestDate = firefighter.getExpiryMedicalTest();
        if (expiryMedicalTestDate != null) {
            holder.expiryMedicalTest.setText(holder.medicalTestPreText + " " + dateFormat.format(expiryMedicalTestDate));
        } else {
            holder.expiryMedicalTest.setText(holder.noMedicalTestDate);
        }
    }

    @Override
    public int getItemCount() {
        return firefighterList.size();
    }

    @Override
    public long getItemId(int position) {
        return firefighterList.get(position).getIdFirefighter();
    }

    public void replaceData(List<Firefighter> firefighters) {
        setList(firefighters);
        notifyDataSetChanged();
    }

    public void setList(List<Firefighter> firefighters) {
        firefighterList = firefighters;
    }


}
