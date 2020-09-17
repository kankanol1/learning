package com.qxd.learn;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class RequestXmll {

    @Test
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
    }
}
