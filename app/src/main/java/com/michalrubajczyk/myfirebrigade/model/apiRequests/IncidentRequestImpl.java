package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;

public class IncidentRequestImpl implements IncidentRequest {
    private final String BASE_SERVER_URL = ResourcesSingleton.getInstance().getString(R.string.base_server_url);
    private final String TAG = "Incident Request";

    private Context mContext;

    public IncidentRequestImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getIncidentById(int incidentId, DataListener dataListener) {

    }

    @Override
    public void getIncidentsByFireBrigadeId(int fireBrigadeId, DataListener dataListener) {

    }
}
