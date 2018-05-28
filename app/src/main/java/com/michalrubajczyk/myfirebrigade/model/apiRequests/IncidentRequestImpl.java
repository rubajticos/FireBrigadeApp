package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;
import com.michalrubajczyk.myfirebrigade.model.dto.IncidentFull;

import org.json.JSONException;
import org.json.JSONObject;

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
        String url = BASE_SERVER_URL + "/incident/firebrigade/" + fireBrigadeId;
        JsonArrayRequest getIncidents = new JsonArrayRequest(Request.Method.GET, url,
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
                        Log.d(TAG, "#getFirefighterTrainings() - server not response");
                    }
                });
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getIncidents);
    }

    @Override
    public void getFirefightersAndCars(int fireBrigadeId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/incident/prepare/firebrigade/" + fireBrigadeId;
        JsonObjectRequest getFirefightersAndCars = new JsonObjectRequest(Request.Method.GET, url,
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
                        Log.d(TAG, "#getFirefighterTrainings() - server not response");
                    }
                });
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getFirefightersAndCars);
    }

    @Override
    public void addIncident(IncidentFull incident, int fireBrigadeId, DataListener dataListener) {
        JSONObject jsonObject = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

        try {
            String incidentJson = gson.toJson(incident);
            Log.d(TAG, "IncidentJSON: " + incidentJson);
            jsonObject = new JSONObject(incidentJson);
            Log.d(TAG, jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String url = BASE_SERVER_URL + "/incident/firebrigade/" + fireBrigadeId;
        JsonObjectRequest addIncidentRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                response -> {
                    dataListener.onSuccess(response.toString());
                    Log.d(TAG, response.toString());
                },
                error -> {
                    try {
                        dataListener.onError(error.networkResponse.statusCode);
                        Log.d(TAG, error.toString());
                    } catch (Exception e) {
                        dataListener.onError(-999);
                        Log.d(TAG, "#addFirefighterTrainings() - server not response");
                    }

                });
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(addIncidentRequest);
    }
}
