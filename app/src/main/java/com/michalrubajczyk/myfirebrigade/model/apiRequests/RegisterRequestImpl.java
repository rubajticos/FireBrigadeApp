package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;
import com.michalrubajczyk.myfirebrigade.model.dto.UserDTO;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Michal on 18/03/2018.
 */

public class RegisterRequestImpl implements RegisterRequest {
    private final String BASE_SERVER_URL = ResourcesSingleton.getInstance().getString(R.string.base_server_url);

    private Context mContext;
    private Gson gson;

    public RegisterRequestImpl(Context context) {
        this.mContext = context;
        this.gson = new Gson();
    }

    @Override
    public void registerUser(UserDTO user, final DataListener dataListener) {
        String url = BASE_SERVER_URL + "/register";

        JSONObject userJSON = new JSONObject();
        try {
            userJSON.put("userId", null);
            userJSON.put("username", user.getUsername());
            userJSON.put("password", user.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url,
                userJSON,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("rejestracja", response.toString());
                        dataListener.onSuccess(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Log.d("rejestracja", error.toString());
                            dataListener.onError(error.networkResponse.statusCode);
                        } catch (NullPointerException e) {
                            Log.d("rejestracja", error.toString());
                            dataListener.onError(-999);
                        }
                    }
                }
        );
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(jsonObjReq);
    }
}
