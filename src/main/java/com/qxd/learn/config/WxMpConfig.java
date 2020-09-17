package com.qxd.learn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Binary Wang
 */
@Configuration
public class WxMpConfig {
    private String token = "kankan";
    private String appid = "wxfad3666c617a12e9";
    private String appSecret = "ed0315ed3060a4fa2dd545ffd7d97a2b";
    private String aesKey = "";

    public String getToken() {
        return this.token;
    }

    public String getAppid() {
        return this.appid;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public String getAesKey() {
        return this.aesKey;
    }

}
