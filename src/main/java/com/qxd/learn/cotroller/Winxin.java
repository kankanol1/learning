package com.qxd.learn.cotroller;


import com.qxd.learn.libs.WeixinSign;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ApiSort(value = 5)
@Controller
@RequestMapping("/api/sys/wx")
@Api(value = "signature", tags = "signatureAPI")
public class Winxin {
    @Autowired
    TokenDaoImpl tokenDao;

    /**
     * get方法校验
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param request
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "get方法方法校验")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String get(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
        System.out.println("=============================================== get start");
        for (Object o : request.getParameterMap().keySet()){
            System.out.println(o + " = " + request.getParameter((String)o));
        }
        System.out.println("=============================================== get end");

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (WeixinSign.checkSignature(signature, timestamp, nonce)) {
            System.out.println(echostr);
            return echostr;
        }

        return "false";
    }

    /**
     * post方法校验
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param request
     * @return
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "post方法方法校验")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Object post(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
        System.out.println("=============================================== post start");
        for (Object o : request.getParameterMap().keySet()) {
            System.out.println(o + " = " + request.getParameter((String) o));
        }
        System.out.println("=============================================== post end");
        StringBuilder result = new StringBuilder();
        result.append("<xml>" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
                "<CreateTime>12345678</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[你好]]></Content>" +
                "</xml>");

        Map<String,Object> map = new HashMap<>();
        map.put("signature",signature);
        map.put("timestamp",timestamp);
        map.put("nonce",nonce);
        map.put("echostr",echostr);
        map.put("result",result.toString());
        return map;
    }

    /**
     * 获取微信服务器IP地址
     * @return
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取微信服务器IP地址")
    @RequestMapping(value = "/ip", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo getServeIp() {
        String str = "cgi-bin/getcallbackip";
        return tokenDao.computer(tokenDao.baseMethodGet(str));
    }

    /**
     * 获取微信API接口IP地址
     * @return
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取微信API接口IP地址")
    @RequestMapping(value = "/api", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo getAPIIp() {
        String str = "cgi-bin/get_api_domain_ip";
        return tokenDao.computer(tokenDao.baseMethodGet(str));
    }

    /**
     * 获取网络监测API
     * @return
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "获取网络监测API")
    @RequestMapping(value = "/net", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo getNetIp() {
        String str = "cgi-bin/callback/check";
        Map<String, Object> map = new HashMap<>();
        map.put("action", "all");
        map.put("check_operator", "DEFAULT");
        return tokenDao.computer(tokenDao.baseMethodPost(str, map));
    }
}
