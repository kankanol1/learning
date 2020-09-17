/**
 * Copyright 2019 bejson.com
 */
package com.qxd.learn.entity.entity;

/**
 * Auto-generated: 2019-03-18 15:0:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Sub_button {

    private String type;
    private String name;
    private String url;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public Sub_button(String type, String name, String url, String key) {
        if(type.equals("click")){
            this.type = type;
            this.name = name;
            this.key = key;
            return;
        }else {
            this.type = type;
            this.name = name;
            this.url = url;

        }

    }
}