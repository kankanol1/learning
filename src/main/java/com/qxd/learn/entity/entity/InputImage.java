package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class InputImage {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputImage(String url) {
        this.url = url;

    }
    public InputImage( ) {
        // this.url = url;
        this.url = "imageUrl";
    }

    @Override
    public String toString() {
        return "InputImage{" +
                "url='" + url + '\'' +
                '}';
    }
}
