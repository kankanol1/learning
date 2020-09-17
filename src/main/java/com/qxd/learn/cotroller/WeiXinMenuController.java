package com.qxd.learn.cotroller;

import com.qxd.learn.entity.MenuEntity;
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ApiSort(value = 7)
@Controller
@RequestMapping("/api/sys/wx/menu")
@Api(value = "微信自定义菜单", tags = "自定义菜单")
public class WeiXinMenuController {
    @Autowired
    TokenDaoImpl tokenDao;

    /**
     * 创建菜单
     * @return
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "创建菜单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo create() {
        String str = "cgi-bin/menu/create";
        Map<String, Object> menu = new HashMap<>();
        List<MenuEntity> list = new LinkedList<>();
        List<MenuEntity> list1 = new LinkedList<>();
        List<MenuEntity> list2 = new LinkedList<>();
        List<MenuEntity> list3 = new LinkedList<>();
        MenuEntity menuEntity1 = new MenuEntity("春夏秋冬", list1);
        MenuEntity menuEntity2 = new MenuEntity("五行", list2);
        MenuEntity menuEntity3 = new MenuEntity("关于我们", list3);

        MenuEntity menuEntity1_1 = new MenuEntity("click", "初春", "V1001_TODAY_MUSIC1");
        MenuEntity menuEntity1_2 = new MenuEntity("click", "盛夏", "V1001_TODAY_MUSIC2");
        MenuEntity menuEntity1_3 = new MenuEntity("click", "晚秋", "V1001_TODAY_MUSIC3");
        MenuEntity menuEntity1_4 = new MenuEntity("view", "凛冬将至", "V1001_TODAY_MUSIC4","https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1599930176355_R&pv=&ic=&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&hs=2&sid=&word=%E5%87%9B%E5%86%AC%E5%B0%86%E8%87%B3");
        list1.add(menuEntity1_1);
        list1.add(menuEntity1_2);
        list1.add(menuEntity1_3);
        list1.add(menuEntity1_4);

        MenuEntity menuEntity2_1 = new MenuEntity("click", "金", "V1002_TODAY_MUSIC1");
        MenuEntity menuEntity2_2 = new MenuEntity("click", "木", "V1002_TODAY_MUSIC2");
        MenuEntity menuEntity2_3 = new MenuEntity("click", "水", "V1002_TODAY_MUSIC3");
        MenuEntity menuEntity2_4 = new MenuEntity("click", "火", "V1002_TODAY_MUSIC4");
        MenuEntity menuEntity2_5 = new MenuEntity("click", "土", "V1002_TODAY_MUSIC5");
        list2.add(menuEntity2_1);
        list2.add(menuEntity2_2);
        list2.add(menuEntity2_3);
        list2.add(menuEntity2_4);
        list2.add(menuEntity2_5);

        MenuEntity menuEntity3_1 = new MenuEntity("click", "关于我", "V1003_TODAY_MUSIC1");
        MenuEntity menuEntity3_2 = new MenuEntity("click", "关于你", "V1003_TODAY_MUSIC2");
        MenuEntity menuEntity3_3 = new MenuEntity("pic_sysphoto", "拍照", "V1003_TODAY_MUSIC3");
        list3.add(menuEntity3_1);
        list3.add(menuEntity3_2);
        list3.add(menuEntity3_3);

        list.add(menuEntity1);
        list.add(menuEntity2);
        list.add(menuEntity3);
        menu.put("button",list);

        return tokenDao.computer(tokenDao.baseMethodPost(str, menu));
    }

    /**
     * 查询菜单
     * @return
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取菜单")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo get() {
        String str = "cgi-bin/menu/get";
        return tokenDao.computer(tokenDao.baseMethodGet(str));
    }

    /**
     * 查询菜单
     * @return
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询菜单")
    @RequestMapping(value = "/serch", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo serch() {
        String str = "get_current_selfmenu_info";
        return tokenDao.computer(tokenDao.baseMethodGet(str));
    }

    /**
     * 删除菜单
     * @return
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除菜单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo delete() {
        String str = "cgi-bin/menu/delete";
        return tokenDao.computer(tokenDao.baseMethodGet(str));
    }





}
