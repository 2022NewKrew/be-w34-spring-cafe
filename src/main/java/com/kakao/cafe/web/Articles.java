package com.kakao.cafe.web;

import com.kakao.cafe.dto.SampleArticleForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Articles {

    private static Logger logger = LoggerFactory.getLogger(Articles.class);
    private ArrayList<Article> articles;

    public Articles() {
        this.articles = new ArrayList<>();
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void addArticle(SampleArticleForm form){
        articles.add(Article.add(form));
    }

    public Article findArticle(int articleID){
        return articles.get(articleID);
    }
}
