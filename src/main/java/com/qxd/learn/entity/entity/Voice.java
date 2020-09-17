package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class Voice {
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Voice(String mediaId) {
        this.mediaId = mediaId;
    }
}
