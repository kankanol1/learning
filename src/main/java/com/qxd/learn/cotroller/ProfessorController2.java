package com.qxd.learn.cotroller;

import com.qxd.learn.entity.Professor2;
import com.qxd.learn.service.ProfessorDaoImpl2;
import com.qxd.learn.statusinfo.Info;
import com.qxd.learn.statusinfo.ResponseInfo;
import com.qxd.learn.statusinfo.Status;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@ApiSort(value = 2)//:给接口排序
@Controller
@RequestMapping("/api/sys/professor2")
@Api(value = "教授2", tags = "教授API2")
public class ProfessorController2 {

    @Autowired
    ProfessorDaoImpl2 personDao2;

    @ApiOperationSupport(order=1)
    @ApiOperation(value = "创建")
    @RequestMapping(value = "/create2", method = RequestMethod.POST)
    @ResponseBody
    public Object test(){
        return computer(personDao2.test());
    }
    /**
     * response数据处理
     * @param pe
     * @return
     */
    private ResponseInfo computer(Object pe) {
        ResponseInfo responseInfo = pe != null ?
                new ResponseInfo(Status.RESPONSE_OK, com.qxd.learn.statusinfo.Info.PARAMS_RIGHT, pe)
                :
                new ResponseInfo(Status.RESPONSE_ERR, Info.NODATA, null);
        return responseInfo;
    }
}
