package com.qxd.learn.cotroller.wx;

import com.qxd.learn.service.TokenDaoImpl;
import com.qxd.learn.statusinfo.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ApiSort(value = 5)
@Controller
@RequestMapping("/api/sys/kf")
@Api(value = "客服", tags = "客服API")
public class KFAccountController {
    @Autowired
    TokenDaoImpl tokenDao;

    @ApiOperationSupport(order = 1)
    @ResponseBody
    @ApiOperation(value = "添加客服")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseInfo add(
            @RequestParam("kf_account") String kf_account,
            @RequestParam("nickname") String nickname,
            @RequestParam("password") String password) {
//        KFAccount kfAccount = new KFAccount(kf_account,nickname, password);
        String str = "customservice/kfaccount/add";
        Map<String, Object> map = new HashMap<>();
        map.put("kf_account",kf_account);
        map.put("nickname",nickname);
        map.put("password",password);
        return tokenDao.computer(tokenDao.baseMethodPost(str,map));
    }

    @ApiOperationSupport(order = 2)
    @ResponseBody
    @ApiOperation(value = "更新客服")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseInfo update(
            @RequestParam("kf_account") String kf_account,
            @RequestParam("nickname") String nickname,
            @RequestParam("password") String password) {
        String str = "customservice/kfaccount/update";
        Map<String, Object> map = new HashMap<>();
        map.put("kf_account",kf_account);
        map.put("nickname",nickname);
        map.put("password",password);
        return tokenDao.computer(tokenDao.baseMethodPost(str,map));
    }

    @ApiOperationSupport(order = 3)
    @ResponseBody
    @ApiOperation(value = "删除客服")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseInfo del(@RequestParam("kf_account") String kf_account) {
        String str = "customservice/kfaccount/del";
        Map<String, Object> map = new HashMap<>();
        map.put("kf_account",kf_account);
        return tokenDao.computer(tokenDao.baseMethodPost(str,map));
    }

    @ApiOperationSupport(order = 3)
    @ResponseBody
    @ApiOperation(value = "获取客服列表")
    @RequestMapping(value = "/getlist", method = RequestMethod.POST)
    public ResponseInfo getlist() {
        String str = "cgi-bin/customservice/getkflist";
        return tokenDao.computer(tokenDao.baseMethodGet(str));
    }


}
