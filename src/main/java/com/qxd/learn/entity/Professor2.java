package com.qxd.learn.entity;

import javax.persistence.*;

@Access(AccessType.FIELD)
@Entity
public class Professor2 {
    public static String LOCAL_AREA_CODE = "999";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //设置自动增长
    @Column(name = "id")
    private int id;

    private String name;
    private long salary;

    @Transient
    private String phoneNum;


    /**
     * Gets the value of phoneNum.
     *
     * @return the value of phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Sets the phoneNum.
     * You can use setPhoneNum() to set the value of phoneNum
     *
     * @param phoneNum phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Access(AccessType.PROPERTY)
    @Column(name="PHONE")
    protected String getPhoneNumberForDb(){
        if(null != phoneNum && phoneNum.length()==11)
            return phoneNum;
        else
            return LOCAL_AREA_CODE + phoneNum;
    }

    protected void setPhoneNumberForDb(String num){
        if(num.startsWith(LOCAL_AREA_CODE))
            phoneNum=num.substring(3);
        else
            phoneNum = num;
    }










    /**
     * Gets the value of id.
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     * You can use setId() to set the value of id
     *
     * @param id id
     */
    public void setId(int id) {
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
    @Override
    public String toString() {
        return "Professor2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Professor2() {
    }

    public Professor2(int id, String name, long salary, String phoneNum) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.phoneNum = phoneNum;
    }
}
