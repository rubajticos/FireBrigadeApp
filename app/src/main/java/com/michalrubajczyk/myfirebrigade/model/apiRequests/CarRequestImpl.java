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
import com.michalrubajczyk.myfirebrigade.model.dto.Car;

import org.json.JSONException;
import org.json.JSONObject;

public class CarRequestImpl implements CarRequest {
    private final String BASE_SERVER_URL = ResourcesSingleton.getInstance().getString(R.string.base_server_url);
    private final String TAG = "Car Request";

    private Context mContext;

    public CarRequestImpl(Context context) {
        this.mContext = context;
    }


    @Override
    public void getCarById(int carId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/car/" + carId;
        JsonObjectRequest getCarByIdRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    dataListener.onSuccess(response.toString());
                    Log.d(TAG, "Car from response: " + response.toString());
                },
                error -> {
                    if (error != null) {
                        dataListener.onError(error.networkResponse.statusCode);
                        Log.d(TAG, "getCarByIdRequest - Error: " + error.toString());
                    } else {
                        dataListener.onError(-999);
                        Log.d(TAG, "getCarByIdRequest - Error: SERVER NOT RESPONSE");
                    }
                });

        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getCarByIdRequest);
    }

    @Override
    public void getCarsByFireBrigadeId(int fireBrigadeId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/cars/" + fireBrigadeId;
        JsonArrayRequest getCarsForFireBrigadeRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    dataListener.onSuccess(response.toString());
                    Log.d(TAG, "Cars for FireBrigade from response: " + response.toString());
                },
                error -> {
                    if (error != null) {
                        dataListener.onError(error.networkResponse.statusCode);
                        Log.d(TAG, "getCarsForFireBrigadeRequest - Error: " + error.toString());
                    } else {
                        dataListener.onError(-999);
                        Log.d(TAG, "getCarsForFireBrigadeRequest - Error: SERVER NOT RESPONSE");
                    }
                });

        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(getCarsForFireBrigadeRequest);
    }

    @Override
    public void addCarToFireBrigade(Car car, int fireBrigadeId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/car/firebrigade/" + fireBrigadeId;

        JSONObject jsonObject = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        try {
            String jsonString = gson.toJson(car, Car.class);
            Log.d(TAG, "addCarToFireBrigade() - created JSON from Obj: " + jsonString);
            jsonObject = new JSONObject(jsonString);
            Log.d(TAG, "addCarToFireBrigade() - created JSONObject from String: " + jsonString.toString());
        } catch (JSONException e) {
            Log.d(TAG, e.getClass().toString() + "\n" + e.getMessage());
        }

        JsonObjectRequest addCarRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                response -> {
                    dataListener.onSuccess(response.toString());
                    Log.d(TAG, "Added Car from response: " + response.toString());
                },
                error -> {
                    if (error != null) {
                        dataListener.onError(error.networkResponse.statusCode);
                        Log.d(TAG, "addCarRequest - Error: " + error.toString());
                    } else {
                        dataListener.onError(-999);
                        Log.d(TAG, "addCarRequest - Error: SERVER NOT RESPONSE");
                    }
                });

        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(addCarRequest);
    }

    @Override
    public void updateCar(Car car, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/car";

        JSONObject jsonObject = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        try {
            String jsonString = gson.toJson(car, Car.class);
            Log.d(TAG, "updateCar() - updated JSON from Obj: " + jsonString);
            jsonObject = new JSONObject(jsonString);
            Log.d(TAG, "updateCar() - updated JSONObject from String: " + jsonString.toString());
        } catch (JSONException e) {
            Log.d(TAG, e.getClass().toString() + "\n" + e.getMessage());
        }

        JsonObjectRequest updateCarRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,
                response -> {
                    dataListener.onSuccess(response.toString());
                    Log.d(TAG, "Updated Car from response: " + response.toString());
                },
                error -> {
                    if (error != null) {
                        dataListener.onError(error.networkResponse.statusCode);
                        Log.d(TAG, "updateCarRequest - Error: " + error.toString());
                    } else {
                        dataListener.onError(-999);
                        Log.d(TAG, "updateCarRequest - Error: SERVER NOT RESPONSE");
                    }
                });

        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(updateCarRequest);
    }

    @Override
    public void deleteCar(int carId, DataListener dataListener) {
        String url = BASE_SERVER_URL + "/car/" + carId;
        JsonObjectRequest deleteCarRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
                response -> {
                    dataListener.onSuccess(response.toString());
                    Log.d(TAG, "Car removal with ID = " + carId + " successully completed");
                },
                error -> {
                    if (error != null) {
                        dataListener.onError(error.networkResponse.statusCode);
                        Log.d(TAG, "deleteCarRequest - Error: " + error.toString());
                    } else {
                        dataListener.onError(-999);
                        Log.d(TAG, "deleteCarRequest - Error: SERVER NOT RESPONSE");
                    }

                });

        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(deleteCarRequest);
    }
}
