package com.qxd.learn.entity;

import javax.persistence.*;

@Entity
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //设置自动增长
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private long salary;

    /**
     * Gets the value of id.
     *
     * @return the value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     * You can use setId() to set the value of id
     *
     * @param id id
     */
    public void setId(Integer id) {
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
     * Gets the value of salary.
     *
     * @return the value of salary
     */
    public long getSalary() {
        return salary;
    }

    /**
     * Sets the salary.
     * You can use setSalary() to set the value of salary
     *
     * @param salary salary
     */
    public void setSalary(long salary) {
        this.salary = salary;
    }

    public ProfessorEntity(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }

    public ProfessorEntity() {
    }

    public ProfessorEntity(String name) {
        this.name = name;
    }

    public ProfessorEntity(Integer id, String name, long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ProfessorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
