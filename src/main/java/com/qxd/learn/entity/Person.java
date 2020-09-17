package com.qxd.learn.entity;


import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "EMP")
public class Person {
    @Id
    @Column(name = "EMP_ID")
    private long id;
    @Column(columnDefinition = "VARCHAR(40)")
    private String name;
    private String surname;

    public Person(){}

    public Person(String name,String surname){
        this.name=name;
        this.surname =surname;
    }

    /**
     * Gets the value of id.
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     * You can use setId() to set the value of id
     *
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the value of name.
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * You can use setName() to set the value of name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of surname.
     *
     * @return the value of surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     * You can use setSurname() to set the value of surname
     *
     * @param surname surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
