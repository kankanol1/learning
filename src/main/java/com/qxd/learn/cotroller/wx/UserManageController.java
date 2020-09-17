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
@RequestMapping("/api/sys/user")
@Api(value = "用户管理", tags = "用户管理API")
public class UserManageController {
    @Autowired
    TokenDaoImpl tokenDao;

    @ApiOperationSupport(order = 1)
    @ResponseBody
    @ApiOperation(value = "创建标签")
    @RequestMapping(value = "/tags/create", method = RequestMethod.POST)
    public ResponseInfo create(
            @RequestParam("id") String id,
            @RequestParam("name") String name){
        String str ="cgi-bin/tags/create";
        Map<String,Object> map = params(id,name);
        return tokenDao.computer(tokenDao.baseMethodPost(str,map));
    }

    @ApiOperationSupport(order = 2)
    @ResponseBody
    @ApiOperation(value = "获取标签列表")
    @RequestMapping(value = "/tags/get", method = RequestMethod.POST)
    public ResponseInfo get(){
        String str ="cgi-bin/tags/get";
        return tokenDao.computer(tokenDao.baseMethodGet(str));
    }

    @ApiOperationSupport(order = 3)
    @ResponseBody
    @ApiOperation(value = "编辑标签")
    @RequestMapping(value = "/tags/update", method = RequestMethod.POST)
    public ResponseInfo update(@RequestParam("id") String id, @RequestParam("name") String name){
        String str ="cgi-bin/tags/update";
        Map<String,Object> map = params(id,name);
        return tokenDao.computer(tokenDao.baseMethodPost(str,map));
    }

    @ApiOperationSupport(order = 4)
    @ResponseBody
    @ApiOperation(value = "删除标签")
    @RequestMapping(value = "/tags/delete", method = RequestMethod.POST)
    public ResponseInfo delete(@RequestParam("id") String id){
        String str ="cgi-bin/tags/delete";
        Map<String,Object> map = params(id);
        return tokenDao.computer(tokenDao.baseMethodPost(str,map));

    }


    @ApiOperationSupport(order = 2)
    @ResponseBody
    @ApiOperation(value = "获取标签下粉丝列表")
    @RequestMapping(value = "/user/tag/get", method = RequestMethod.POST)
    public ResponseInfo uget(@RequestParam("tagid") String tagid,String next_openid){
        next_openid = next_openid!=null?next_openid:"";
        String str ="cgi-bin/user/tag/get";
        Map<String,Object> map = new HashMap<>();
        map.put("tagid",tagid);
        map.put("next_openid",next_openid);
        return tokenDao.computer(tokenDao.baseMethodPost(str,map));
    }




    public Map<String,Object> params(String id,String name){
        Map<String,Object> map = new HashMap<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("id",id);
        map1.put("name",name);
        map.put("tag",map1);
        return map;
    }
    public Map<String,Object> params(String id){
        Map<String,Object> map = new HashMap<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("id",id);
        map.put("tag",map1);
        return map;
    }


}
