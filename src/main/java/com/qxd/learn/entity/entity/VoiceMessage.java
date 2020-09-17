package com.qxd.learn.entity.entity;

import java.util.Map;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class VoiceMessage extends BaseMessage {

    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public VoiceMessage(Map<String, String> map, Voice voice) {
        super(map);
        this.voice = voice;
        setMsgType("voice");
    }
}
