package com.qxd.learn.statusinfo;

public class ResponseInfo {


    public static final String Status = "status";

    public static final String Info = "info";


    private String status;

    private String info;

    private Object data;

    /**
     * Gets the value of data.
     *
     * @return the value of data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the data.
     * You can use setData() to set the value of data
     *
     * @param data data
     */
    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {

        this.status = status;
    }


    public String getInfo() {
        return info;
    }


    public void setInfo(String info) {
        this.info = info;
    }

    public ResponseInfo() {
    }

    public ResponseInfo(String status, String info, Object data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }
}
