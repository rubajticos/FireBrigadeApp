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
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;
import com.michalrubajczyk.myfirebrigade.utils.SSLAccept;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FirefighterRequestImpl implements FirefighterRequest {
    private final String BASE_SERVER_URL = ResourcesSingleton.getInstance().getString(R.string.base_server_url);
    private final String TAG = "Firefighter Request";

    private Context mContext;

    public FirefighterRequestImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getFirefightersByFirebrigadeId(int firebrigadeId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/firefighters/firebrigade/" + firebrigadeId;
        JsonArrayRequest getFirefightersRequest = new JsonArrayRequest(Request.Method.GET, url,
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
                        Log.d(TAG, "sever not response");
                    }
                });
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getFirefightersRequest);
    }

    @Override
    public void getFirefighterById(int firefighterId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/firefighter/" + firefighterId;
        JsonObjectRequest getFirefightersRequest = new JsonObjectRequest(Request.Method.GET, url,
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
                        Log.d(TAG, "sever not response");
                    }
                });
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getFirefightersRequest);
    }

    @Override
    public void addFirefighterToFireBrigade(Firefighter firefighter, int firebrigadeId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/firefighter/firebrigade/" + firebrigadeId;

        JSONObject jsonObject = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd" +
                "").create();

        try {
            String jsonString = gson.toJson(firefighter, Firefighter.class);
            Log.d(TAG, jsonString);
            jsonObject = new JSONObject(jsonString);
            Log.d(TAG, jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest addFirefighterRequest = new JsonObjectRequest(Request.Method.POST, url,
                jsonObject,
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
                        Log.d(TAG, "sever not response");
                    }
                });
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(addFirefighterRequest);
    }

    @Override
    public void deleteFirefighter(Firefighter firefighter, DataListener dataListener) {

    }

    @Override
    public void updateFirefighter(Firefighter firefighter, DataListener dataListener) {

    }

    @Override
    public void getFirefighterTrainings(int firefighterId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/trainings/firefighter/" + firefighterId;
        JsonArrayRequest getFirefighterTrainingRequest = new JsonArrayRequest(Request.Method.GET, url,
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
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getFirefighterTrainingRequest);
    }

    @Override
    public void addFirefighterTrainings(List<FirefighterTraining> trainings, DataListener dataListener) {

    }

    @Override
    public void updateFirefighterTrainings(List<FirefighterTraining> trainingList, DataListener dataListener) {

    }
}
