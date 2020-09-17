package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/16/0016.
 */
public class Intent {
    private int code;
    private String intentName;
    private Parameters parameters;
    private String actionName;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Intent(int code, String intentName, Parameters parameters, String actionName) {
        this.code = code;
        this.intentName = intentName;
        this.parameters = parameters;
        this.actionName = actionName;
    }

    public Intent() {
    }

    @Override
    public String toString() {
        return "Intent{" +
                "code=" + code +
                ", intentName='" + intentName + '\'' +
                ", parameters=" + parameters +
                ", actionName='" + actionName + '\'' +
                '}';
    }
}
