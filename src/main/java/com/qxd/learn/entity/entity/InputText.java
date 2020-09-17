package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class InputText {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InputText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "InputText{" +
                "text='" + text + '\'' +
                '}';
    }
}
