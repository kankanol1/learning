package com.qxd.learn.filter;

import com.qxd.learn.service.MenuEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
//        response.setContentType("text/html;charset=UTF-8");

        //禁用缓存，确保网页信息是最新数据
//        response.setHeader("Expires", "-1");
//        response.setHeader("Pragma","No-cache");
//        response.setHeader("Cache-Control","no-cache");

        //Access-Control-Allow-Origin
        // System.out.println("kankans");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        // response.setHeader("Access-Control-Max-Age", "3600");
        // response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        // "X-Requested-With, Content-Type, Authorization, Accept, Origin, User-Agent, Content-Range, Content-Disposition, Content-Description");
        // response.setDateHeader("Expires", -10);
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        Map<String, String> maps = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            // 排除Cookie字段
            if (key.equalsIgnoreCase("Cookie")) {
                continue;
            }
            String value = request.getHeader(key);
            maps.put(key, value);
        }

        //判断是否是微信浏览器 https://www.cnblogs.com/sherryweb/p/11239127.html
        // https://www.cnblogs.com/chxrs/p/11315140.html
        // https://www.microanswer.cn/blog/13
        String ua = request.getHeader("User-Agent");
//        if (ua.contains("micromessenger")) {
        System.out.println("User-Agent: "+maps.get("user-agent"));

        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }


        if(isMobileDevice(ua) || (request.getParameter("signature")!=null && request.getParameter("echostr")!=null)){
            chain.doFilter(req, response);
        }else if(maps.get("user-agent").equals("Mozilla/4.0")){
            ServletInputStream is = request.getInputStream();
            //处理消息 时间推送
            Map<String, String> map = MenuEventService.requestParse(is);
            //准备返回数据包
            String getrep = MenuEventService.getResponse(map);
            //返回数据
            PrintWriter writer = response.getWriter();
            writer.print(getrep);
            writer.flush();
            writer.close();
        }else{
            chain.doFilter(req, response);
        }




     /*   if(isMobileDevice(ua) || maps.get("user-agent").equals("Mozilla/4.0") ){
            ServletInputStream is = request.getInputStream();
            //处理消息 时间推送
            Map<String, String> map = MenuEventService.requestParse(is);
            //准备返回数据包
            String getrep = MenuEventService.getResponse(map);
            //返回数据
            PrintWriter writer = response.getWriter();
            writer.print(getrep);
            writer.flush();
            writer.close();
        }else{
            chain.doFilter(req, response);

        }
*/
/*            if (ua.toLowerCase().contains("micromessenger") && maps.get("user-agent").equals("Mozilla/4.0")) {//bug待修复
//            System.out.println("hearder: "+maps);
//            System.out.println("微信端: "+maps.get("user-agent"));
            //接收参数
            ServletInputStream is = request.getInputStream();
            //处理消息 时间推送
            Map<String, String> map = MenuEventService.requestParse(is);
            //准备返回数据包
            String getrep = MenuEventService.getResponse(map);
            //返回数据
            PrintWriter writer = response.getWriter();
            writer.print(getrep);
            writer.flush();
            writer.close();
        } else {
            chain.doFilter(req, response);
        }*/
//        chain.doFilter(req, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    public boolean isMobileDevice(String requestHeader) {
        /*
         * android            :所有安卓设备
         * mas  os            :iphone
         * windows phone    :windows系统手机
         */
        String[] deviceArray = new String[] {"android","mas os","windows phone"};
        if( null == requestHeader) {
            return false;
        }
        requestHeader = requestHeader.toLowerCase();
        for(int i = 0;i<deviceArray.length;i++) {
            if(requestHeader.indexOf(deviceArray[i]) > 0) {
                return true;
            }
        }
        return false;
    }

//    public static  final String TOKEN ="kankan";
/*    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        //设置中文编码
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        //接收参数
        ServletInputStream is = request.getInputStream();
        //处理消息 时间推送
        Map<String,String> map =MenuEventService.requestParse(is);
        //准备返回数据包
        String getrep = MenuEventService.getResponse(map);
        //返回数据
        PrintWriter writer = response.getWriter();
        writer.print(getrep);
        writer.flush();
        writer.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        System.err.println("get");
        */

    /**
     * 验证消息的确来自微信服务器
     * <p>
     * <p>
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     *//*
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        if (MenuEventService.check(TOKEN,timestamp,nonce,signature)){
            System.err.println("接入成功");
            PrintWriter writer = response.getWriter();
            writer.print(echostr);
            writer.flush();
            writer.close();

        }else{
            System.err.println("接入失败");
        }
    }
    */

}

