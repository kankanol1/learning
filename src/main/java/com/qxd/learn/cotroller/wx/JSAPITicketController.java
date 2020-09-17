package com.qxd.learn.cotroller.wx;

import com.qxd.learn.entity.wx.JSAPITicketEntiry;
import net.sf.json.JSONObject;
import com.qxd.learn.service.TokenDaoImpl;
import com.qxd.learn.service.wx.JSAPITicketService;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@ApiSort(value = 5)
@Controller
@RequestMapping("/api/sys/kf")
@Api(value = "获取JSAPITicket", tags = "JSAPITicketAPI")
public class JSAPITicketController {
    @Autowired
    TokenDaoImpl tokenDao;
    @Autowired
    JSAPITicketService jsapiTicketService;

    @ApiOperationSupport(order = 1)
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiOperation(value = "获取jsapi_ticket")
    public ResponseInfo get(){
        return tokenDao.computer(jsapiTicketService.findTicket(0));
    }

  /*  @ApiOperationSupport(order = 1)
    @ResponseBody
    @RequestMapping(value = "/getticket", method = RequestMethod.GET)
    @ApiOperation(value = "获取jsapi_ticket")
    public ResponseInfo gettick(){
        String token = tokenDao.findToken(0).getAccess_token();
        String str ="https://api.weixin.qq.com/cgi-bin/ticket/getticket" +
                "&type=" + "jsapi" +
                "&access_token=" + token;
        Object notice=null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            notice = restTemplate.getForObject(str, Object.class);
            JSONObject json = (JSONObject) JSONObject.fromObject(notice);
            JSAPITicketEntiry  jsapiTicketEntiry= new JSAPITicketEntiry(json.getString("ticket"), json.getString("expires_in"));
            if(jsapiTicketService.findTicket(0)!=null){
                jsapiTicketService.updateTicket(jsapiTicketEntiry);
            }else{
                jsapiTicketService.createTicket(jsapiTicketEntiry);
            }

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        return tokenDao.computer(notice);
    }*/





}
