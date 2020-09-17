package com.qxd.learn.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qxd.learn.entity.entity.*;
import com.qxd.learn.libs.HttpClientUtils;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MenuEventService {

    public static boolean check(String TOKEN, String timestamp, String nonce, String signature) {
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strings = {TOKEN, timestamp, nonce};
        Arrays.sort(strings);
        String str = strings[0] + strings[1] + strings[2];
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String mysid = sha1(str);
        System.err.println(mysid);
        System.err.println(signature);
        return mysid.equalsIgnoreCase(signature);
    }

    private static String sha1(String str) {
        //获取加密对象
        MessageDigest md = null;
        StringBuffer sb = new StringBuffer();
        try {
            md = MessageDigest.getInstance("sha1");

            byte[] digest = md.digest(str.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

            for (byte b : digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b & 15]);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * @param is
     * @return
     */
    public static Map<String, String> requestParse(ServletInputStream is) {
        HashMap<String, String> map = new HashMap<>();
        //xml解析对象
        SAXReader reader = new SAXReader();
        try {
            //read 解析xml
            Document document = reader.read(is);
            //获取根节点
            Element rootElement = document.getRootElement();
            //获取所有子节点
            List<Element> elements = rootElement.elements();
            for (Element e : elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.err.println(map);
        return map;
    }

    /*@Test
    public  void requestParseTest() {
        String path = "./a.xml";
        HashMap<String, String> map = new HashMap<>();
        //xml解析对象
        SAXReader reader = new SAXReader();
        try {
            //read 解析xml
            Document document = reader.read(path);
            //获取根节点
            Element rootElement = document.getRootElement();
            //获取所有子节点
            List<Element> elements = rootElement.elements();
            for (Element e : elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.err.println(map);
    }*/

    /**
     * 给用户发送消息 处理菜单事件
     *
     * @param map
     * @return
     */
    public static String getResponse(Map<String, String> map) {
        BaseMessage msg = null;
        String msgType = map.get("MsgType");
        switch (msgType) {
            case "text":
                msg = dealTextMessage(map);
                break;
            case "image":
                msg = dealImageMessage(map);
                break;
            case "voice":

                break;
            case "video":

                break;
            case "shortvideo":

                break;
            case "location":

                break;
            case "link":

                break;
            case "event":
                msg = dealEventMessage(map);
                break;
            default:
                break;
        }
        if (msg != null) {
            //xml解析对象
            XStream stream = new XStream();
            //启用xml注释
            stream.processAnnotations(TextMessage.class);
            stream.processAnnotations(NewsMessage.class);
            String s = stream.toXML(msg);
            return s;
        }
        return null;
    }

    /**
     * 菜单事件
     *
     * @param map
     * @return
     */
    private static BaseMessage dealEventMessage(Map<String, String> map) {
        String event = map.get("Event");
        BaseMessage msg = null;
        switch (event) {
            case "CLICK":
                msg = switchClick(map);
                break;
            case "VIEW":
                break;
            case "pic_sysphoto":
                msg = switchClick(map);
                break;


        }
        return msg;
    }

    private static BaseMessage dealImageMessage(Map<String, String> map) {
        BaseMessage msg = new TextMessage(map, "我们已经收到您的图片反馈，我们会尽快处理。");
        return msg;
    }

    /**
     * 处理click事件
     *
     * @param map
     * @return
     */
    private static BaseMessage switchClick(Map<String, String> map) {
        BaseMessage msg = null;
        switch (map.get("EventKey")) {
            case "V1001_TODAY_MUSIC":
                msg = new TextMessage(map, "<<七友>>\n已记不起我也有权利爱人\n" +
                        "谁人曾照顾过我的感受\n" +
                        "待我温柔吻过我伤口\n" +
                        "能得到的安慰是失恋者得救后\n" +
                        "很感激忠诚的狗\n" +
                        "谁人曾介意我也不好受\n" +
                        "为我出头碰过我的手");
                break;
            case "V1001_GOOD":
                msg = new TextMessage(map, "感谢你的鼓励");
                break;
            case "V1001_TODAY_ZIXUN":
                msg = new TextMessage(map, "还没有哦");
                break;
            case "V1001_TODAY_MUSIC1":
                msg = new TextMessage(map, "初春");
                break;
            case "V1001_TODAY_MUSIC2":
                msg = new TextMessage(map, "盛夏");
                break;
            case "V1001_TODAY_MUSIC3":
                msg = new TextMessage(map, "晚秋");
                break;
            case "V1001_TODAY_MUSIC4":
                msg = new TextMessage(map, "凛冬将至");
                break;
            case "V1002_TODAY_MUSIC1":
            case "V1002_TODAY_MUSIC2":
            case "V1002_TODAY_MUSIC3":
            case "V1002_TODAY_MUSIC4":
            case "V1002_TODAY_MUSIC5":
                msg = new TextMessage(map, "金木水火土");
                break;
            case "V1003_TODAY_MUSIC1":
                msg = new TextMessage(map, "关于我");
                break;
            case "V1003_TODAY_MUSIC2":
                msg = new TextMessage(map, "关于你");
                break;
            case "V1003_TODAY_MUSIC3":
                msg = new TextMessage(map, "我们已经收到您的图片反馈，我们会尽快处理。");
                break;

        }
        return msg;
    }
   /* private static BaseMessage switchClickTest(Map<String, String> map) {
        BaseMessage msg = null;
        switch (map.get("EventKey")) {
            case "V1001_TODAY_MUSIC":
                msg = new TextMessage(map, "<<七友>>\n已记不起我也有权利爱人\n" +
                        "谁人曾照顾过我的感受\n" +
                        "待我温柔吻过我伤口\n" +
                        "能得到的安慰是失恋者得救后\n" +
                        "很感激忠诚的狗\n" +
                        "谁人曾介意我也不好受\n" +
                        "为我出头碰过我的手");
                break;
            case "V1001_GOOD":
                msg = new TextMessage(map, "感谢你的鼓励");
                break;
            case "V1001_TODAY_ZIXUN":
                msg = new TextMessage(map, "还没有哦");
                break;
            case "V1001_TODAY_MUSIC1":
                msg = new TextMessage(map, "初春");
                break;
            case "V1001_TODAY_MUSIC2":
                msg = new TextMessage(map, "盛夏");
                break;
            case "V1001_TODAY_MUSIC3":
                msg = new TextMessage(map, "晚秋");
                break;
            case "V1001_TODAY_MUSIC4":
                msg = new TextMessage(map, "凛冬将至");
                break;
            case "V1002_TODAY_MUSIC1":
            case "V1002_TODAY_MUSIC2":
            case "V1002_TODAY_MUSIC3":
            case "V1002_TODAY_MUSIC4":
            case "V1002_TODAY_MUSIC5":
                msg = new TextMessage(map, "金木水火土");
                break;
            case "V1003_TODAY_MUSIC1":
                msg = new TextMessage(map, "关于我");
                break;
            case "V1003_TODAY_MUSIC2":
                msg = new TextMessage(map, "关于你");
                break;
            case "V1003_TODAY_MUSIC3":

                msg = new TextMessage(map, "我们已经收到您的图片反馈，我们会尽快处理。");
                break;

        }
        return msg;
    }*/

    private static BaseMessage dealTextMessage(Map<String, String> map) {
        //接收的文本内容
        String content = map.get("Content");
        //调用聊天机器人
        if (content.equals("图文")) {
            Item item = new Item();
            item.setTitle("百度搜图");
            item.setDescription("众里寻他亲百度");
//            item.setUrl("https://www.bilibili.com/video/av35042298/?p=9&t=587");
            item.setUrl("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1600047239762_R&pv=&ic=&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&sid=&word=%E4%BA%8C%E6%AC%A1%E5%85%83");
//            item.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/ialbibib3e5H6wPYFltLUicduU4KS6awZwwTyC9eshpZqbQqicjIxNpOicGY8pWiaLEaia793ev40Ry47EEI2Gozb5NkyQ/0");
            item.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600057384831&di=9e7bc5e4517e931f43ca71938e666fdb&imgtype=0&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D2480667962%2C3249567117%26fm%3D214%26gp%3D0.jpg");
            Articles articles = new Articles(item);
            NewsMessage newsMessage = new NewsMessage(map, "1", articles);
            return newsMessage;
        }
        String res = chat(content);
        TextMessage tm = new TextMessage(map, res);

        return tm;
    }

    private static String chat(String content) {
        //准备请求图灵机器人的json数据
        InputText inputText = new InputText(content);
        InputImage inputImage = new InputImage();
        SelfInfo selfInfo = new SelfInfo();
        Perception perception = new Perception(inputText, inputImage, selfInfo);
        UserInfo userInfo = new UserInfo();
        TulingMessage tulingMessage = new TulingMessage(perception, userInfo);
        //String s = JSONObject.toJSONString(tulingMessage);
        String s = JSON.toJSONString(tulingMessage);
        System.err.println(s);
        //post请求接口
        /**
         * 请求接口的json数据格式
         * {
         "reqType":0,
         "perception": {
         "inputText": {
         "text": "你好"
         },
         "inputImage": {
         "url": "imageUrl"
         },
         "selfInfo": {
         "location": {
         "city": "北京",
         "province": "北京",
         "street": "信息路"
         }
         }
         },
         "userInfo": {
         "apiKey": "95336a4533004ec08026c9f330f38234",图灵接口的key
         "userId": "415411"
         }
         }
         */
        JSONObject jsonObject = HttpClientUtils.httpPost("http://openapi.tuling123.com/openapi/api/v2", s);
        //取出需要发送用户的数据text
        String text = "";
        JSONArray results = (JSONArray) jsonObject.get("results");
        /**
         *返回的json数据
         * {
         "emotion": {
         "robotEmotion": {
         "a": 0,
         "d": 0,
         "emotionId": 0,
         "p": 0
         },
         "userEmotion": {
         "a": 0,
         "d": 0,
         "emotionId": 10300,
         "p": 0
         }
         },
         "intent": {
         "actionName": "",
         "code": 10004,
         "intentName": ""
         },
         "results": [{
         "groupType": 1,
         "resultType": "text",
         "values": {
         "text": "又见面啦！"
         }
         }]
         }
         results为json数组只能遍历取出 有好的方法可以给我留言 小白一个
         get(角标)越界异常 遍历吧jsonarray转为jsonobj get("values")就可以直接取出
         */
        if (results.size() > 0) {
            for (int i = 0; i < results.size(); i++) {
                JSONObject jsonObj = results.getJSONObject(i);
                JSONObject values = (JSONObject) jsonObj.get("values");
                text = (String) values.get("text");
                System.err.println(text);
            }
        }
        return text;
    }
}
