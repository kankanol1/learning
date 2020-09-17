package com.qxd.learn.cotroller;

import com.qxd.learn.libs.Computer;
import com.qxd.learn.service.EmployeeDaoImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@ApiSort(value=4)
@Controller
@RequestMapping("/api/sys/employee")
@Api(value = "员工", tags = "员工API")
public class EmployeeController {
    @Autowired
    EmployeeDaoImpl employeeDao;

    @ApiOperationSupport(order=1)
    @ApiOperation(value = "创建")
    @RequestMapping(value = "/cp", method = RequestMethod.POST)
    @ResponseBody
    public  Object test(){
        Computer computer = new Computer();
        return computer.getResponseInfo(employeeDao.test());
    }
}
