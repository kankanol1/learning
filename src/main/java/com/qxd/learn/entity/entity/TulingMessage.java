package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class TulingMessage {
    private int reqType;

    private Perception perception;

    private UserInfo userInfo;

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public Perception getPerception() {
        return perception;
    }

    public void setPerception(Perception perception) {
        this.perception = perception;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public TulingMessage( Perception perception, UserInfo userInfo) {
        this.reqType = 0;
        this.perception = perception;
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "TulingMessage{" +
                "reqType=" + reqType +
                ", perception=" + perception +
                ", userInfo=" + userInfo +
                '}';
    }
}
