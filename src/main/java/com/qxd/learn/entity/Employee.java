package com.qxd.learn.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccessTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String toString() {
        return "Employee " + getId() +
                ": name: " + getName() +
                " lastAccessTime: " + getLastAccessTime();
    }
}