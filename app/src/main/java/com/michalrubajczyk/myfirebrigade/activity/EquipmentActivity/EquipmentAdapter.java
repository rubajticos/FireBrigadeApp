package com.michalrubajczyk.myfirebrigade.activity.EquipmentActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.EquipmentAdapterObj;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.MyViewHolder> {

    List<EquipmentAdapterObj> equipmentList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView type;
        public TextView subtype;
        public TextView carName;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.equipment_list_item_name);
            type = (TextView) view.findViewById(R.id.equipment_list_item_type);
            subtype = (TextView) view.findViewById(R.id.equipment_list_item_subtype);
            carName = (TextView) view.findViewById(R.id.equipment_list_item_car);
        }
    }

    public EquipmentAdapter(List<EquipmentAdapterObj> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipment_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EquipmentAdapterObj eq = equipmentList.get(position);
        holder.name.setText(eq.getName());
        holder.type.setText(eq.getType());
        if (!Strings.isNullOrEmpty(eq.getCarName())) {
            holder.carName.setText(eq.getCarName());
        }

        if (!Strings.isNullOrEmpty(eq.getSubtype())) {
            holder.subtype.setText(" | " + eq.getSubtype());
        } else {
            holder.subtype.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    @Override
    public long getItemId(int position) {
        return equipmentList.get(position).getId();
    }

    public void replaceData(List<EquipmentAdapterObj> equipment) {
        setList(equipment);
        notifyDataSetChanged();
    }

    public void setList(List<EquipmentAdapterObj> equipment) {
        equipmentList = equipment;
    }


}
