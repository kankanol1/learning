/**
 * Copyright 2019 bejson.com
 */
package com.qxd.learn.entity.entity;

import java.util.List;

/**
 * Auto-generated: 2019-03-18 14:59:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Button {

    private String type;
    private String name;
    private String key;
    private List<Sub_button> sub_button;

    public List<Sub_button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Sub_button> sub_button) {
        this.sub_button = sub_button;
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

    public void setKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }

    public Button(String type, String name, String key, List<Sub_button> sub_button) {
        this.type = type;
        this.name = name;
        this.key = key;
        this.sub_button = sub_button;
    }

    public Button(String type, String name, String key) {
        this.type = type;
        this.name = name;
        this.key = key;
    }

    public Button(String name, List<Sub_button> sub_button) {
        this.name = name;
        this.sub_button = sub_button;
    }
}