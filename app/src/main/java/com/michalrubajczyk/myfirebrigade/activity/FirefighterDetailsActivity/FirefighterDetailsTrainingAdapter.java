package com.michalrubajczyk.myfirebrigade.activity.FirefighterDetailsActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FirefighterDetailsTrainingAdapter extends RecyclerView.Adapter<FirefighterDetailsTrainingAdapter.MyViewHolder> {

    List<FirefighterTraining> firefighterTrainingList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView trainingName;
        public TextView trainingDate;
        public String noTrainingDateText;

        public MyViewHolder(View view) {
            super(view);
            trainingName = (TextView) view.findViewById(R.id.firefighter_training_list_item_trainingName);
            trainingDate = (TextView) view.findViewById(R.id.firefighter_training_list_item_trainingDate);
            noTrainingDateText = view.getResources().getString(R.string.firefighter_no_trainingDate);
        }

    }

    public FirefighterDetailsTrainingAdapter(List<FirefighterTraining> firefighterTrainingList) {
        this.firefighterTrainingList = firefighterTrainingList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.firefighter_details_training_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FirefighterTraining firefighterTraining = firefighterTrainingList.get(position);
        holder.trainingName.setText(firefighterTraining.getTraining().getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date trainingDate = firefighterTraining.getTrainingDate();
        if (trainingDate != null) {
            holder.trainingDate.setText(dateFormat.format(firefighterTraining.getTrainingDate()));
        } else {
            holder.trainingDate.setText(holder.noTrainingDateText);
        }
    }

    @Override
    public int getItemCount() {
        return firefighterTrainingList.size();
    }

    public void replaceData(List<FirefighterTraining> firefighterTrainings) {
        setList(firefighterTrainings);
        notifyDataSetChanged();
    }

    private void setList(List<FirefighterTraining> firefighterTrainings) {
        firefighterTrainingList = firefighterTrainings;
    }
}
