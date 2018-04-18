package com.michalrubajczyk.myfirebrigade.activity.FirefighterActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;

import org.w3c.dom.Text;

import java.util.List;

public class FirefighterAdapter extends RecyclerView.Adapter<FirefighterAdapter.MyViewHolder> {

    List<Firefighter> firefighterList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView lastName;
        public TextView expiryMedicalTest;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.firefighter_list_item_name);
            lastName = (TextView) view.findViewById(R.id.firefighter_list_item_lastname);
            expiryMedicalTest = (TextView) view.findViewById(R.id.firefighter_list_item_expiryMedicalTest);
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
        holder.lastName.setText(firefighter.getExpiryMedicalTest().toString());
    }

    @Override
    public int getItemCount() {
        return firefighterList.size();
    }


}
