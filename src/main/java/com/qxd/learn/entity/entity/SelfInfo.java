package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class SelfInfo {

    private String city;
    private String province;
    private String street;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public SelfInfo(String city, String province, String street) {
        this.city = city;
        this.province = province;
        this.street = street;

    }
    public SelfInfo() {

        this.city = "北京";
        this.province = "北京";
        this.street = "信息路";
    }

    @Override
    public String toString() {
        return "SelfInfo{" +
                "city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
