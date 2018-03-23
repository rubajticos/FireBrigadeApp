package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;

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
    public void addFireBrigadeForUser() {

    }

    FireBrigadeDTO parseResponseToFireBrigadeDTO(String response) {
        Gson gson = new Gson();
        FireBrigadeDTO fireBrigadeDTO = new FireBrigadeDTO();
        fireBrigadeDTO = gson.fromJson(response, FireBrigadeDTO.class);
        return fireBrigadeDTO;
    }
}
