package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class UserInfo {

    private String apiKey;
    private String userId;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserInfo() {
        this.apiKey = "95336a4533004ec08026c9f330f38234";
        this.userId = "415411";
    }

    public UserInfo(String apiKey, String userId) {
        this.apiKey = apiKey;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "apiKey='" + apiKey + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
