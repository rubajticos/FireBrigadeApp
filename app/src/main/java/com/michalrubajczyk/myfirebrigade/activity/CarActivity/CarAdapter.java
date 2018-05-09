package com.michalrubajczyk.myfirebrigade.activity.CarActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    List<Car> carList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView model;
        public TextView type;
        public TextView operationalNumbers;
        public TextView plates;

        public MyViewHolder(View view) {
            super(view);
            model = (TextView) view.findViewById(R.id.car_list_item_model);
            type = (TextView) view.findViewById(R.id.car_list_item_type);
            operationalNumbers = (TextView) view.findViewById(R.id.car_list_item_operational_numbers);
            plates = (TextView) view.findViewById(R.id.car_list_item_plates);
        }
    }

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.model.setText(car.getModel());
        holder.type.setText(car.getType());
        holder.operationalNumbers.setText(car.getOperationalNumbers());
        holder.plates.setText(car.getPlates());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    @Override
    public long getItemId(int position) {
        return carList.get(position).getId();
    }

    public void replaceData(List<Car> cars) {
        setList(cars);
        notifyDataSetChanged();
    }

    public void setList(List<Car> cars) {
        carList = cars;
    }


}
