package com.qxd.learn.action;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ApiSort(value=6)
@Api(value = "html", tags = "html")
@Controller
public class PageController {
    @RequestMapping("/index")
    @ResponseBody
    public String toIndex(){
        return "/index";
    }
    @RequestMapping("/test")
    @ResponseBody
    public String toTest(){
        return "test";
    }
}
