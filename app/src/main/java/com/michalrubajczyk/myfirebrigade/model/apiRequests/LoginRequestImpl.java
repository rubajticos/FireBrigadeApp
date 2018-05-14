package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.model.ResourcesSingleton;
import com.michalrubajczyk.myfirebrigade.model.auth.AuthUserInfo;
import com.michalrubajczyk.myfirebrigade.utils.AuthUserUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michal on 19/03/2018.
 */

public class LoginRequestImpl implements LoginRequest {
    private final String BASE_SERVER_URL = ResourcesSingleton.getInstance().getString(R.string.base_server_url);

    private static String TRUSTED_LOGIN = "my-trusted-client";
    private static String TRUSTED_PASSWORD = "secret";

    private AuthUserUtils authUserUtils;


    private Context mContext;

    public LoginRequestImpl(Context mContext) {
        this.mContext = mContext;
        authUserUtils = new AuthUserUtils(mContext);
    }

    @Override
    public void loginUserAndGetAccessToken(String login, String password, final DataListener dataListener) {
        String url = BASE_SERVER_URL + "/oauth/token?grant_type=password&username=" + login + "&password=" + password;

        JsonObjectRequest loginAndGetTokenRequest = new JsonObjectRequest(Request.Method.POST, url,
                null,
                response -> {
                    Log.d("login response", response.toString());
                    AuthUserInfo authUserInfo = authUserUtils.createAuthUserInfoFromResponse(response);
                    authUserUtils.addAuthUserInfoToSharedPreferences(authUserInfo, login);
                    dataListener.onSuccess("success");
                    Log.d("authUserInfo", authUserInfo.toString());
                },
                error -> {
                    try {
                        dataListener.onError(error.networkResponse.statusCode);
                        Log.d("login error", error.toString());
                    } catch (NullPointerException e) {
                        dataListener.onError(-999);
                        Log.d("server error", "serwer nie odpowiada");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                String auth = getBasicAuth();
                headers.put("Authorization", auth);
                return headers;
            }
        };
        RequestQueueSingleton.getInstance(mContext).addToRequestQueue(loginAndGetTokenRequest);
    }


    private String getBasicAuth() {
        String credentials = TRUSTED_LOGIN + ":" + TRUSTED_PASSWORD;
        String auth = "Basic "
                + Base64.encodeToString(credentials.getBytes(),
                Base64.NO_WRAP);
        return auth;
    }
}
