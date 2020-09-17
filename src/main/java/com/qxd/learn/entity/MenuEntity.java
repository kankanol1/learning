package com.qxd.learn.entity;

import java.util.List;

public class MenuEntity {
    public String type;
    public String name;
    public String key;
    public String url;
    public String appid;
    public String pagepath;
    public List sub_button;

    public MenuEntity(String type, String name, String key) {
        this.type = type;
        this.name = name;
        this.key = key;
    }

    public MenuEntity(String type, String name, String key, String url) {
        this.type = type;
        this.name = name;
        this.key = key;
        this.url = url;
    }

    public MenuEntity(String type, String name, String url, String appid, String pagepath) {
        this.type = type;
        this.name = name;
        this.url = url;
        this.appid = appid;
        this.pagepath = pagepath;
    }


    public MenuEntity(String name, List sub_button) {
        this.name = name;
        this.sub_button = sub_button;
    }
}
