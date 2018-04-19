package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;
import com.michalrubajczyk.myfirebrigade.utils.SSLAccept;

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

    }

    @Override
    public void deleteFirefighter(Firefighter firefighter, DataListener dataListener) {

    }

    @Override
    public void updateFirefighter(Firefighter firefighter, DataListener dataListener) {

    }

    @Override
    public void getFirefighterTrainings(Firefighter firefighter, DataListener dataListener) {

    }

    @Override
    public void updateFirefighterTrainings(List<FirefighterTraining> trainingList, DataListener dataListener) {

    }
}
