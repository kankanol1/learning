package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/18/0018.
 */
public class ACCESSTOKEN {
    private String access_token;
    private long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public ACCESSTOKEN(String access_token, int expires_in) {
        this.access_token = access_token;
        this.expires_in = System.currentTimeMillis()+Long.valueOf(expires_in)*1000;
    }
    public  boolean getTrueToken(){
        if (System.currentTimeMillis()>this.expires_in){
            return true;
        }
        return false;
    }
}
