package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;

public class StatisticsRequestImpl implements StatisticsRequest {
    private final String BASE_SERVER_URL = ResourcesSingleton.getInstance().getString(R.string.base_server_url);
    private final String TAG = "Statistics Request";

    private Context mContext;

    public StatisticsRequestImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getStatisticsForFireBrigade(int fireBrigadeId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/stats/firebrigade/" + fireBrigadeId;
        JsonObjectRequest getStatistics = new JsonObjectRequest(Request.Method.GET, url,
                null,
                response -> {
                    dataListener.onSuccess(response.toString());
                    Log.d(TAG, response.toString());
                },
                error -> {
                    try {
                        dataListener.onError(error.networkResponse.statusCode);
                        Log.d(TAG, error.toString());
                    } catch (NullPointerException e) {
                        dataListener.onError(-999);
                        Log.d(TAG, "#getStatisticsForFireBrigade() - server not response");
                    }
                });
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getStatistics);

    }
}
