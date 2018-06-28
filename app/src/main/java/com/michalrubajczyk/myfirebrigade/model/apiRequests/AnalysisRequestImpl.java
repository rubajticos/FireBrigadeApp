package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;

public class AnalysisRequestImpl implements AnalysisRequest {
    private final String BASE_SERVER_URL = ResourcesSingleton.getInstance().getString(R.string.base_server_url);
    private final String TAG = "Analysis Request";

    private Context mContext;

    public AnalysisRequestImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void analysisDetail(boolean fullReport, int fireBrigadeId, DataListener dataListener) {

    }

    @Override
    public void analysisGeneral(boolean fullReport, int fireBrigadeId, DataListener dataListener) {

    }
}
