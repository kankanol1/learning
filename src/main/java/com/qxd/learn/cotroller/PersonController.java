package com.qxd.learn.cotroller;

import com.qxd.learn.service.PersonDaoImpl;
import com.qxd.learn.statusinfo.Info;
import com.qxd.learn.statusinfo.ResponseInfo;
import com.qxd.learn.statusinfo.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@ApiSort(value = 3)//:给接口排序
@Controller
@RequestMapping("/api/sys/person")
@Api(value = "人员", tags = "人员API")
public class PersonController {
    @Autowired
    PersonDaoImpl personDao;

    @ApiOperationSupport(order=1)
    @ApiOperation(value = "创建")
    @RequestMapping(value = "/cp", method = RequestMethod.POST)
    @ResponseBody
    public Object test(){
        return computer(personDao.test());
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
