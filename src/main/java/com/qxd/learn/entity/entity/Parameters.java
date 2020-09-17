package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/16/0016.
 */
public class Parameters {
    private String date;
    private String city;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Parameters(String date, String city) {
        this.date = date;
        this.city = city;
    }

    public Parameters() {
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "date='" + date + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
