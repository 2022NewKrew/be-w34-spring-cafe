package com.kakao.cafe.application;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticlePort;
import com.kakao.cafe.domain.article.ArticleVo;

import java.util.List;
import java.util.Optional;

public class ArticleService {

    private final ArticlePort articlePort;

    public ArticleService(ArticlePort articlePort) {
        this.articlePort = articlePort;
    }

    public void write(ArticleVo articleVo) {
        articlePort.save(articleVo);
    }

    public List<Article> findAll() {
        return articlePort.findAll();
    }

    public Article findById(int id) throws IllegalArgumentException {
        Optional<Article> optionalArticle = articlePort.findById(id);
        if (optionalArticle.isEmpty()) {
            throw new IllegalArgumentException("잘못 된 index 입니다");
        }

        return optionalArticle.get();
    }
}
