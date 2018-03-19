package com.michalrubajczyk.myfirebrigade.model.apiRequests;

/**
 * Created by Michal on 19/03/2018.
 */

public interface LoginRequest {

    void loginUserAndGetAccessToken();

    void getAccessTokenFromRefreshToken();
}
