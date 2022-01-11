package com.kakao.cafe.dao;

import com.kakao.cafe.vo.ArticleVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDao {

    private final List<ArticleVo> articles = new ArrayList<>();

    public void addArticle(ArticleVo articleVo) {
        articles.add(articleVo);
    }

    public ArticleVo getArticle(int id) {
        return articles.stream()
                .filter(articleVo -> articleVo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<ArticleVo> getArticles() {
        return articles;
    }
}
