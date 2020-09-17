package com.qxd.learn.libs;

import com.qxd.learn.statusinfo.Info;
import com.qxd.learn.statusinfo.ResponseInfo;
import com.qxd.learn.statusinfo.Status;

public class Computer {
    private ResponseInfo responseInfo;


    /**
     * Gets the value of responseInfo.
     *
     * @return the value of responseInfo
     */
    public ResponseInfo getResponseInfo(Object pe) {
        this.responseInfo = pe != null ?
                new ResponseInfo(Status.RESPONSE_OK, com.qxd.learn.statusinfo.Info.PARAMS_RIGHT, pe)
                :
                new ResponseInfo(Status.RESPONSE_ERR, Info.NODATA, null);
        return this.responseInfo;
    }

   /* public static ResponseInfo computer(Object pe) {
        this.responseInfo = pe != null ?
                new ResponseInfo(Status.RESPONSE_OK, com.qxd.learn.statusinfo.Info.PARAMS_RIGHT, pe)
                :
                new ResponseInfo(Status.RESPONSE_ERR, Info.NODATA, null);
        return this.responseInfo;
    }*/
}
