package com.qxd.learn.cotroller;


import com.qxd.learn.consts.WeixinConst;
import com.qxd.learn.entity.TokenEntity;
import com.qxd.learn.service.TokenDaoImpl;
import com.qxd.learn.statusinfo.Info;
import com.qxd.learn.statusinfo.ResponseInfo;
import com.qxd.learn.statusinfo.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;


@ApiSort(value = 5)
@Controller
@RequestMapping("/api/sys/token")
@Api(value = "token", tags = "tokenAPI")
public class TokenController {
    @Autowired
    TokenDaoImpl tokenDao;


    /**
     * 添加元素
     * @param access_token
     * @param expires_in
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseInfo add(
            @RequestParam("access_token") String access_token,
            @RequestParam("expires_in") String expires_in) {
        TokenEntity tokenEntity = new TokenEntity(access_token, expires_in);
        return computer(tokenDao.addToken(tokenEntity));
    }

    /**
     * 查找元素
     *
     * @param id
     * @return
     */
    @ApiOperationSupport(order = 2)
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ResponseInfo get(@RequestParam("id") Integer id) {
        return computer(tokenDao.findToken(id));
    }

    /**
     * 更新元素
     *
     * @param id
     * @param access_token
     * @param expires_in
     * @return
     */
    @ApiOperationSupport(order = 3)
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseInfo update(
            @RequestParam("id") Integer id,
            @RequestParam("access_token") String access_token,
            @RequestParam("expires_in") String expires_in
    ) {
        TokenEntity tokenEntity = tokenDao.updateToken(id, access_token, expires_in);
        return computer(tokenEntity);
    }

    /**
     * 移除元素
     *
     * @param id
     * @return
     */
    @ApiOperationSupport(order = 4)
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseInfo remove(@RequestParam("id") Integer id) {
        return computer(tokenDao.removeToken(id));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @ApiOperationSupport(order = 5)
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有token")
    public ResponseInfo all() {
        return computer(tokenDao.findAll());
    }

    /**
     * 获取token
     *
     * @return
     */

    @ApiOperationSupport(order = 5)
    @ResponseBody
    @RequestMapping(value = "/gettoken", method = RequestMethod.GET)
    @ApiOperation(value = "获取token")
    public ResponseInfo gettoken() {
        String str = "https://api.weixin.qq.com/cgi-bin/token?"
                + "grant_type=" + WeixinConst.GRANT_TYPE
                + "&appid=" + WeixinConst.WX_APPID
                + "&secret=" + WeixinConst.WX_SECRET;
        System.out.println(str);
        Object notice=null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            notice = restTemplate.getForObject(str, Object.class);
            JSONObject json = (JSONObject) JSONObject.fromObject(notice);
            add(json.getString("access_token"), json.getString("expires_in"));

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        return computer(notice);
    }










    /**
     * response数据处理
     *
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

    private Object getFieldValueByName(String fieldName, Object o) {
        System.out.println("fieldName: " + fieldName);
        System.out.println("Object: " + o);
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            System.out.println(getter);
            Method method = o.getClass().getMethod(getter, new Class[0]);
            Object value = method.invoke(o, new Object[0]);
            System.out.println(value.toString());
            return value.toString();
        } catch (Exception e) {
            return "";
        }
    }

}
