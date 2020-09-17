package com.qxd.learn.entity.entity;

import java.util.Map;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class MusicMessage extends BaseMessage {
   private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public MusicMessage(Map<String, String> map, Music music) {
        super(map);
        this.music = music;
        setMsgType("music");
    }
}
