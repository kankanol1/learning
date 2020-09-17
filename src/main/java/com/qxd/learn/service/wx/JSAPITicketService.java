package com.qxd.learn.service.wx;

import com.qxd.learn.entity.wx.JSAPITicketEntiry;
import com.qxd.learn.service.TokenDaoImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class JSAPITicketService {
    @Autowired
    TokenDaoImpl tokenDao;
    @PersistenceContext
    private EntityManager ticket;

    public JSAPITicketService(EntityManager ticket) {
        this.ticket = ticket;
    }

    /**
     * 创建
     * @param jsapiTicketEntiry
     * @return
     */
    public JSAPITicketEntiry createTicket( JSAPITicketEntiry jsapiTicketEntiry) {
        ticket.persist(jsapiTicketEntiry);
        return jsapiTicketEntiry;
    }

    /**
     * 更新
     * @param jsapiTicketEntiry
     * @return
     */
    public JSAPITicketEntiry updateTicket( JSAPITicketEntiry jsapiTicketEntiry) {
        JSAPITicketEntiry jsapiTicketEntiry1 = ticket.find(JSAPITicketEntiry.class,0);
        if(jsapiTicketEntiry1!=null){
            jsapiTicketEntiry1.setTicket(jsapiTicketEntiry.getTicket());
            jsapiTicketEntiry1.setExpires_in(jsapiTicketEntiry.getExpires_in());
        }
        return jsapiTicketEntiry1;
    }

    /**
     * 查询
     * @param id
     * @return
     */
    public JSAPITicketEntiry findTicket(Integer id) {
        JSAPITicketEntiry jsapiTicketEntiry = ticket.find(JSAPITicketEntiry.class, id);
        return jsapiTicketEntiry;
    }

    public JSAPITicketEntiry save(String token) {
        Object notice=null;
        JSAPITicketEntiry  jsapiTicketEntiry=null;
        if(token!=null){
            //https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=37_iSGuQ6NwP1ZgEEdO977Gt16tAWKeUTLqx2awSMo-7n7TJFhlfj9oKE6JhIjwy8zmOP4TKUaB0_ygGOIoyPDKox87mQP5q3yE4b7xmua4BbhqRqNm2NknYwVNBnPkUqUfyp0oXNGcj6I3j3buPHGjAAAFXW&type=jsapi
            String str ="https://api.weixin.qq.com/cgi-bin/ticket/getticket?" +
                    "type=" + "jsapi" +
                    "&access_token=" + token;
            try {
                RestTemplate restTemplate = new RestTemplate();
                notice = restTemplate.getForObject(str, Object.class);
                JSONObject json = (JSONObject) JSONObject.fromObject(notice);
                jsapiTicketEntiry=new JSAPITicketEntiry(json.getString("ticket"), json.getString("expires_in"));
                if(findTicket(0)!=null){
                    updateTicket(jsapiTicketEntiry);
                }else{
                    createTicket(jsapiTicketEntiry);
                }

            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }
        }

        return jsapiTicketEntiry;
    }
}
