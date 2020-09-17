package com.qxd.learn.entity.entity;

import java.util.Map;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class ImageMessge extends BaseMessage {

    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageMessge(Map<String, String> map, Image image) {
        super(map);
        this.image = image;
        setMsgType("image");
    }
}
