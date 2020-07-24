package com.qxd.learn.cotroller;

import com.qxd.learn.entity.ProfessorEntity;
import com.qxd.learn.service.ProfessorServiceImpl;
import com.qxd.learn.statusinfo.Info;
import com.qxd.learn.statusinfo.ResponseInfo;
import com.qxd.learn.statusinfo.Status;
import io.swagger.annotations.*;
import io.swagger.annotations.ApiOperationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@ApiSort(value = 1)//:给接口排序
//
@Controller
@RequestMapping("/api/sys/professor")
@Api(value = "教授", tags = "教授API")
public class ProfessorController {
    @Autowired
    ProfessorServiceImpl personDao;

    @ApiOperationSupport(order=1)
    @ApiOperation(value = "创建")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "salary", value = "薪资", required = true, dataType = "long"),
    })
    public ResponseInfo createProfessor(String name, long salary) {
        ProfessorEntity professor = new ProfessorEntity(name, salary);
        return computer(personDao.createProfessor(professor));

    }

    @ApiOperationSupport(order=2)
    @ApiOperation(value = "查找元素")
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo findProfessor(@RequestParam("id") int id) {
        return computer(personDao.findProfessor(id));
    }

    @ApiOperationSupport(order=3)
    @ApiOperation(value = "调整工资")
    @RequestMapping(value = "/changeSalary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo raise(@RequestParam("id") int id, @RequestParam("salary") long raise) {
        return computer(personDao.raisePrfessorSalary(id, raise));
    }

    @ApiOperationSupport(order=4)
    @ApiOperation(value = "获取所有元素")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo findAllProfessor() {
        return computer(personDao.findAllProfessor());
    }


    /**
     * response数据处理
     * @param pe
     * @return
     */
    private ResponseInfo computer(Object pe) {
        ResponseInfo responseInfo = pe != null ?
                new ResponseInfo(Status.RESPONSE_OK, Info.PARAMS_RIGHT, pe)
                :
                new ResponseInfo(Status.RESPONSE_ERR, Info.NODATA, null);
        return responseInfo;
    }
}
