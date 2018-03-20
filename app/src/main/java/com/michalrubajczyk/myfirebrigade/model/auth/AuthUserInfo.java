package com.michalrubajczyk.myfirebrigade.model.auth;

/**
 * Created by Michal on 20/03/2018.
 */

public class AuthUserInfo {

    private String username;
    private String access_token;
    private String refresh_token;

    public AuthUserInfo(String username, String access_token, String refresh_token) {
        this.username = username;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public String toString() {
        return "AuthUserInfo{" +
                "access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                '}';
    }
}
