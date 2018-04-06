package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Michal on 23/03/2018.
 */

public class FireBrigadeRequestImpl implements FireBrigadeRequest {
    private final String BASE_SERVER_URL = ResourcesSingleton.getInstance().getString(R.string.base_server_url);
    private final String TAG = "FireBrigade Request";

    private Context mContext;

    public FireBrigadeRequestImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getFireBrigadeByUsername(String username, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/firebrigade/user/" + username;

        JsonObjectRequest getFireBrigadeRequest = new JsonObjectRequest(Request.Method.GET, url,
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
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getFireBrigadeRequest);
    }

    @Override
    public void addFireBrigadeToUser(FireBrigadeDTO fireBrigadeDTO, String username, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/firebrigade/user/" + username;
        JSONObject jsonObject = null;
        Gson gson = new Gson();
        try {
            String jsonString = gson.toJson(fireBrigadeDTO, FireBrigadeDTO.class);
            Log.d(TAG, jsonString);
            jsonObject = new JSONObject(jsonString);
            Log.d(TAG, jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest addFireBrigadeRequest = new JsonObjectRequest(Request.Method.POST, url,
                jsonObject,
                response -> {
                    Log.d(TAG, response.toString());
                    dataListener.onSuccess(response.toString());
                },
                error -> {
                    try {
                        Log.d(TAG, error.toString());
                        dataListener.onError(error.networkResponse.statusCode);
                    } catch (NullPointerException e) {
                        Log.d(TAG, e.toString());
                        dataListener.onError(-999);
                    }
                }
        );
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(addFireBrigadeRequest);
    }

}
