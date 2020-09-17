package com.qxd.learn.entity.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {
    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public TextMessage(Map<String,String> map,String content){
        super(map);
        //设置文本消息类型msgtype
        this.setMsgType("text");
        this.content=content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "content='" + content + '\'' +
                '}';
    }
}
