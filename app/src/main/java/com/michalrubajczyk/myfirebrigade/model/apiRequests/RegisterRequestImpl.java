package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.dto.UserDTO;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Michal on 18/03/2018.
 */

public class RegisterRequestImpl implements RegisterRequest {

    private Context mContext;
    private Gson gson;

    public RegisterRequestImpl(Context context) {
        this.mContext = context;
        this.gson = new Gson();
    }

    @Override
    public void registerUser(UserDTO user, final DataListener dataListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        String url = "https://192.168.0.103:8443/register";

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
        requestQueue.add(jsonObjReq);
    }
}
