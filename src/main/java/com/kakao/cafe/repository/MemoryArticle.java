package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.SampleArticleForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryArticle implements ArticleRepository {

    private static Logger logger = LoggerFactory.getLogger(MemoryArticle.class);
    private List<Article> articles;

    public MemoryArticle() {
        this.articles = new ArrayList<>();
    }

    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public void addArticle(SampleArticleForm form){
        articles.add(Article.add(form));
    }

    @Override
    public Article findArticle(int articleID){
        return articles.get(articleID);
    }
}
