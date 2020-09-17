package com.qxd.learn.entity.wx;

import javax.persistence.*;

@Entity
public class JSAPITicketEntiry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //设置自动增长
    @Column(name = "id")
    private Integer id;

    @Column(name = "ticket")
    public String ticket;

    @Column(name = "expires_in")
    public String expires_in;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public JSAPITicketEntiry(String ticket, String expires_in) {
        this.ticket = ticket;
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "JSAPITicketEntiry{" +
                "id=" + id +
                ", ticket='" + ticket + '\'' +
                ", expires_in='" + expires_in + '\'' +
                '}';
    }

    public JSAPITicketEntiry() {
    }
}
