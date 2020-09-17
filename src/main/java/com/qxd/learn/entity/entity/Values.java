package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/16/0016.
 */
public class Values {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Values(String text) {
        this.text = text;
    }

    public Values() {
    }

    @Override
    public String toString() {
        return "Values{" +
                "text='" + text + '\'' +
                '}';
    }
}
