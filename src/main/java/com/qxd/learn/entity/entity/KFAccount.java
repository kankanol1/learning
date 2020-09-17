package com.qxd.learn.entity.entity;

public class KFAccount {
    public String kf_account;
    public String nickname;
    public String password;

    public String getKf_account() {
        return kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public KFAccount(String kf_account, String nickname, String password) {
        this.kf_account = kf_account;
        this.nickname = nickname;
        this.password = password;
    }
}
