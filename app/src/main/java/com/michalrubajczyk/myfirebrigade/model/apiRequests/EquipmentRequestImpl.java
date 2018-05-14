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
import com.michalrubajczyk.myfirebrigade.model.dto.Equipment;

import org.json.JSONException;
import org.json.JSONObject;

public class EquipmentRequestImpl implements EquipmentRequest {
    private final String BASE_SERVER_URL = ResourcesSingleton.getInstance().getString(R.string.base_server_url);
    private final String TAG = "Equipment Request";

    private Context mContext;

    public EquipmentRequestImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getFireBrigadeEquipment(int fireBrigadeId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/equipments/firebrigade/" + fireBrigadeId;
        JsonArrayRequest getEquipmentsFireBrigadeRequest = new JsonArrayRequest(Request.Method.GET, url,
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
                        Log.d(TAG, "server not response");
                    }
                });
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getEquipmentsFireBrigadeRequest);
    }

    @Override
    public void getActiveEquipmentForCar(int carId, DataListener dataListener) {

    }

    @Override
    public void getInactiveEquipmentForCar(int carId, DataListener dataListener) {

    }

    @Override
    public void createEquipment(Equipment equipment, int fireBrigadeId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/equipment/fireBrigade/" + fireBrigadeId;

        JSONObject jsonObject = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd" +
                "").create();

        try {
            String jsonString = gson.toJson(equipment, Equipment.class);
            Log.d(TAG, "equipmentJSON: " + jsonString);
            jsonObject = new JSONObject(jsonString);
            Log.d(TAG, jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest addEquipmentRequest = new JsonObjectRequest(Request.Method.POST, url,
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
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(addEquipmentRequest);
    }

    @Override
    public void updateEquipment(Equipment equipment, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/equipment";

        JSONObject jsonObject = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd" +
                "").create();

        try {
            String jsonString = gson.toJson(equipment, Equipment.class);
            Log.d(TAG, "equipmentJSON: " + jsonString);
            jsonObject = new JSONObject(jsonString);
            Log.d(TAG, jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest updateEquipmentRequest = new JsonObjectRequest(Request.Method.POST, url,
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
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(updateEquipmentRequest);
    }

    @Override
    public void deleteEquipment(Equipment equipment, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/equipments/" + equipment.getId();
        JsonObjectRequest deleteEquipmentRequest = new JsonObjectRequest(Request.Method.DELETE, url,
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
                        Log.d(TAG, "server not response");
                    }
                });
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(deleteEquipmentRequest);
    }
}
