package com.michalrubajczyk.myfirebrigade.activity.IncidentActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.dto.IncidentFull;

import java.text.SimpleDateFormat;
import java.util.List;

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.MyViewHolder> {

    List<IncidentFull> incidentsList;

    private final SimpleDateFormat dateFormatter;

    public IncidentAdapter(SimpleDateFormat dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView desc;
        public TextView type;
        public TextView subtype;
        public TextView city;
        public TextView date;

        public MyViewHolder(View view) {
            super(view);
            desc = (TextView) view.findViewById(R.id.incident_list_item_description);
            type = (TextView) view.findViewById(R.id.incident_list_item_type);
            subtype = (TextView) view.findViewById(R.id.incident_list_item_subtype);
            city = (TextView) view.findViewById(R.id.incident_list_item_city);
            date = (TextView) view.findViewById(R.id.incident_list_item_date);
        }
    }

    public IncidentAdapter(List<IncidentFull> incidentsList, SimpleDateFormat dateFormatter) {
        this.incidentsList = incidentsList;
        this.dateFormatter = dateFormatter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.incident_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        IncidentFull incident = incidentsList.get(position);
        holder.desc.setText(incident.getIncident().getDescription());
        holder.type.setText(incident.getIncident().getType());
        if (!Strings.isNullOrEmpty(incident.getIncident().getSubtype())) {
            holder.subtype.setText(" | " + incident.getIncident().getSubtype());
        } else {
            holder.subtype.setText("");
        }

        holder.city.setText(incident.getIncident().getCity());
        holder.date.setText(dateFormatter.format(incident.getIncident().getDate()));
    }

    @Override
    public int getItemCount() {
        return incidentsList.size();
    }

    @Override
    public long getItemId(int position) {
        return incidentsList.get(position).getIncident().getId();
    }

    public void replaceData(List<IncidentFull> incidents) {
        setList(incidents);
        notifyDataSetChanged();
    }

    public void setList(List<IncidentFull> incidents) {
        incidentsList = incidents;
    }


}
