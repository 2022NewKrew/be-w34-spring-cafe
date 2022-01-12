package com.kakao.cafe.application.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.FindArticlePort;

import java.util.List;
import java.util.Optional;

public class FindArticleService {

    private final FindArticlePort findArticlePort;

    public FindArticleService(FindArticlePort findArticlePort) {
        this.findArticlePort = findArticlePort;
    }

    public List<Article> findAll() {
        return findArticlePort.findAll();
    }

    public Article findById(int id) throws IllegalArgumentException {
        Optional<Article> optionalArticle = findArticlePort.findById(id);
        if (optionalArticle.isEmpty()) {
            throw new IllegalArgumentException("잘못 된 index 입니다");
        }

        return optionalArticle.get();
    }
}
