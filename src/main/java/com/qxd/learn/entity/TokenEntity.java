package com.qxd.learn.entity;

import javax.persistence.*;

@Entity
public class TokenEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //设置自动增长
    @Column(name = "id")
    private Integer id;

    @Column(name = "access_token")
    public String access_token;

    @Column(name = "expires_in")
    public String expires_in;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public TokenEntity(String access_token, String expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public TokenEntity() {
    }

    @Override
    public String toString() {
        return "TokenEntity{" +
                "id=" + id +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                '}';
    }
}
