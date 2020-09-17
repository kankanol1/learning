package com.qxd.learn.service;


import com.alibaba.fastjson.JSON;
import com.qxd.learn.consts.WeixinConst;
import com.qxd.learn.entity.TokenEntity;
import com.qxd.learn.service.wx.JSAPITicketService;
import com.qxd.learn.statusinfo.Info;
import com.qxd.learn.statusinfo.ResponseInfo;
import com.qxd.learn.statusinfo.Status;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TokenDaoImpl {

    @Autowired
    JSAPITicketService jsapiTicketService;
    @PersistenceContext
    private EntityManager token;

    public TokenDaoImpl(EntityManager token) {
        this.token = token;
    }


    /**
     * 创建元素
     *
     * @param tokenEntity
     * @return
     */
    public TokenEntity createToken(TokenEntity tokenEntity) {
        token.persist(tokenEntity);
        return tokenEntity;
    }

    /**
     * 同上
     *
     * @param tokenEntity
     * @return
     */
    public TokenEntity addToken(TokenEntity tokenEntity) {
        token.persist(tokenEntity);
        return tokenEntity;
    }

    public void save() {
        String str = "https://api.weixin.qq.com/cgi-bin/token?"
                + "grant_type=" + WeixinConst.GRANT_TYPE
                + "&appid=" + WeixinConst.WX_APPID
                + "&secret=" + WeixinConst.WX_SECRET;
        Object notice = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            notice = restTemplate.getForObject(str, Object.class);
            JSONObject json = (JSONObject) JSONObject.fromObject(notice);
            TokenEntity tokenEntity = new TokenEntity(json.getString("access_token"),json.getString("expires_in"));

            if(findToken(0)!=null){
                updateToken(0, json.getString("access_token"), json.getString("expires_in"));

            }else {
                addToken(tokenEntity);
            }
            jsapiTicketService.save(json.getString("access_token"));

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找元素
     *
     * @param id
     * @return
     */
    public TokenEntity findToken(Integer id) {
        TokenEntity tokenEntity = token.find(TokenEntity.class, id);
        return tokenEntity;
    }

    /**
     * 更新元素
     *
     * @param id
     * @param access_token
     * @param expires_in
     * @return
     */
    public TokenEntity updateToken(Integer id, String access_token, String expires_in) {
        TokenEntity tokenEntity = token.find(TokenEntity.class, id);
        if (tokenEntity != null) {
            tokenEntity.setAccess_token(access_token);
            tokenEntity.setExpires_in(expires_in);
        }
        return tokenEntity;
    }


    /**
     * 移除元素
     *
     * @param id
     * @return
     */
    public TokenEntity removeToken(Integer id) {
        TokenEntity tokenEntity = findToken(id);
        if (tokenEntity != null) {
            token.remove(tokenEntity);
        }
        return tokenEntity;
    }

    /**
     * 查找所有
     *
     * @return
     */
    public List<TokenEntity> findAll() {
        TypedQuery<TokenEntity> query = token.createQuery("SELECT e FROM TokenEntity e", TokenEntity.class);
        return query.getResultList();
    }


    @Autowired
    TokenDaoImpl tokenDao;

    /**
     * java触发 GET 请求 无参数
     *
     * @param s
     * @return
     */
    public Object baseMethodGet(String s) {
        String token = tokenDao.findToken(0).getAccess_token();
        String str = WeixinConst.WX_URL + s + WeixinConst.WX_TOKEN + token;
        RestTemplate restTemplate = new RestTemplate();
        Object notice = restTemplate.getForObject(str, Object.class);
        return notice;
    }
    /*
     */
/**
 * java触发 GET 请求 有参数
 * @param s
 * @return
 */

/*
    public  ResponseInfo baseMethodGet(String s,Map<String,Object> map) {
        String token = tokenDao.findToken(0).getAccess_token();
        String str = WeixinConst.WX_URL + s + WeixinConst.WX_TOKEN + token;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(map, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(str, requestEntity, String.class);
            JSON json = JSON.parseObject((String)responseEntity.getBody());
            return computer(json);

    }
*/


    /**
     * java触发 POST 请求 json参数
     *
     * @param s0
     * @param map
     * @return
     */
    public JSON baseMethodPost(String s0, Map<String, Object> map) {
        String token = tokenDao.findToken(0).getAccess_token();
        String str = WeixinConst.WX_URL + s0 + WeixinConst.WX_TOKEN + token;

        RestTemplate restTemplate = new RestTemplate();
        // 设置请求头 json格式
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 参数 String content = "{\"action\": \"" + map.get("action") + "\",\"check_operator\": \"" + map.get("check_operator") + "\"}";
        // 请求实体 https://www.cnblogs.com/qq931399960/p/11420121.html
        HttpEntity<Object> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(str, requestEntity, String.class);
        JSON json = JSON.parseObject((String) responseEntity.getBody());
        return json;
    }

    /**
     * response数据处理
     *
     * @param pe
     * @return
     */
    public ResponseInfo computer(Object pe) {
        ResponseInfo responseInfo = pe != null ?
                new ResponseInfo(Status.RESPONSE_OK, Info.PARAMS_RIGHT, pe)
                :
                new ResponseInfo(Status.RESPONSE_ERR, Info.NODATA, null);
        return responseInfo;
    }


}
