package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/16/0016.
 */
public class Results {
    private  int groupType;
    private  Values values;
    private String resultType;

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public Values getValues() {
        return values;
    }

    public void setValues(Values values) {
        this.values = values;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Results(int groupType, Values values, String resultType) {
        this.groupType = groupType;
        this.values = values;
        this.resultType = resultType;
    }

    public Results() {
    }

    @Override
    public String toString() {
        return "Results{" +
                "groupType=" + groupType +
                ", values=" + values +
                ", resultType='" + resultType + '\'' +
                '}';
    }
}
