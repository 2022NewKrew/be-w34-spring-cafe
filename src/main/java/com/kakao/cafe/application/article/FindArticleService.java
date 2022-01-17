package com.kakao.cafe.application.article;

import com.kakao.cafe.application.article.validation.NonExistsArticleIdException;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.FindArticlePort;

import java.util.List;

public class FindArticleService {
    private final FindArticlePort findArticlePort;

    public FindArticleService(FindArticlePort findArticlePort) {
        this.findArticlePort = findArticlePort;
    }

    public List<Article> findAll() {
        return findArticlePort.findAll();
    }

    public Article findById(int id) throws NonExistsArticleIdException {
        return findArticlePort.findById(id)
                .orElseThrow(() -> new NonExistsArticleIdException());
    }
}
