package com.qxd.learn.cotroller.wx;

import com.qxd.learn.consts.WeixinConst;
import com.qxd.learn.service.TokenDaoImpl;
import com.qxd.learn.service.wx.JSAPITicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApiSort(value = 5)
@Controller
@RequestMapping("/api/sys/auth")
@Api(value = "网页授权", tags = "网页授权API")
public class WeiXinCheckController {

    @Autowired
    TokenDaoImpl tokenDao;
    @Autowired
    JSAPITicketService jsapiTicketService;

    @Autowired
    private WxMpService wxMpService;

    @ApiOperationSupport(order = 1)
    @ResponseBody
    @ApiOperation(value = "签证")
    @RequestMapping(value = "/sigs", method = RequestMethod.POST)
    public Object Sigs(){
        Map<String,Object> map = new HashMap<>();
        // 1.获取access_token 这里没用
        String access_token =tokenDao.findToken(0).getAccess_token();
        // 2.获取jsapi_ticket 内嵌了1
        String jsapi_ticket =jsapiTicketService.findTicket(0).getTicket();
        // 3.随机字符串
        String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
        // 4.时间戳
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
        // 5.重定向url
        String url = "http://d3mwqv.natappfree.cc/index.html";

        // 6.排序
        String[] ArrTmp = {"jsapi_ticket","timestamp","noncestr","url"};
        Arrays.sort(ArrTmp);
        StringBuffer sf = new StringBuffer();
        for(int i=0;i<ArrTmp.length;i++){
            sf.append(ArrTmp[i]);
        }

        String str = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;

        //6、将字符串进行sha1加密
        String signature = SHA1(str);
        map.put("access_token",access_token);
        map.put("jsapi_ticket",jsapi_ticket);
        map.put("noncestr",noncestr);
        map.put("timestamp",timestamp);
        map.put("url",url);
        map.put("signature",signature);
        map.put("appid", WeixinConst.WX_APPID);

        return map;
    }

    @ApiOperationSupport(order = 1)
    @ResponseBody
    @ApiOperation(value = "签证")
    @RequestMapping(value = "/jssdk", method = RequestMethod.GET)
    public WxJsapiSignature getSdk(String url) {
        try {
            // 直接调用wxMpService 接口
            WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature(url);
            return wxJsapiSignature;
        } catch (WxErrorException e) {
            return null;
        }
    }


    public String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
