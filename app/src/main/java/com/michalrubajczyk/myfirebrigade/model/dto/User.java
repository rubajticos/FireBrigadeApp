package com.michalrubajczyk.myfirebrigade.model.dto;

/**
 * Created by Michal on 18/03/2018.
 */

public class User {

    private Integer userId;
    private String username;
    private String password;

    public User(String username, String password) {
        this(null, username, password);
    }

    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
