package com.qxd.learn.statusinfo;

public class ComputerInfo {
    private ResponseInfo computer(Object pe) {
        ResponseInfo responseInfo = pe != null ?
                new ResponseInfo(Status.RESPONSE_OK, Info.PARAMS_RIGHT, pe)
                :
                new ResponseInfo(Status.RESPONSE_ERR, Info.NODATA, null);
        return responseInfo;
    }

}
