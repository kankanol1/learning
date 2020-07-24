package com.qxd.learn.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CorsFilter implements Filter{

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
//        response.setContentType("text/html;charset=UTF-8");

        //禁用缓存，确保网页信息是最新数据
//        response.setHeader("Expires", "-1");
//        response.setHeader("Pragma","No-cache");
//        response.setHeader("Cache-Control","no-cache");

        //Access-Control-Allow-Origin

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
//                "X-Requested-With, Content-Type, Authorization, Accept, Origin, User-Agent, Content-Range, Content-Disposition, Content-Description");

//        response.setDateHeader("Expires", -10);
        chain.doFilter(req, response);

    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {}

}

