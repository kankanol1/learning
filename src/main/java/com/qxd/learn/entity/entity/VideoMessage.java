package com.qxd.learn.entity.entity;

import java.util.Map;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class VideoMessage extends BaseMessage {

    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public VideoMessage(Map<String, String> map, Video video) {
        super(map);
        this.video = video;
        setMsgType("video");
    }
}
