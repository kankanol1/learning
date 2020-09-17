package com.qxd.learn.entity.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class Articles {
    @XStreamAlias("item")
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Articles(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "item=" + item +
                '}';
    }
}
