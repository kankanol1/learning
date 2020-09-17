package com.qxd.learn.entity.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {
    @XStreamAlias("ArticleCount")
    private String articleCount;
    @XStreamAlias("Articles")
    private Articles articles;

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }

    public NewsMessage(Map<String, String> map, String articleCount, Articles articles) {
        super(map);
        this.articleCount = articleCount;
        this.articles = articles;
        setMsgType("news");
    }


    @Override
    public String toString() {
        return "NewsMessage{" +
                "articleCount='" + articleCount + '\'' +
                ", articles=" + articles +
                '}';
    }
}
