package com.kakao.cafe.application.article;

import com.kakao.cafe.application.article.validation.NonExistsArticleIdException;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleDaoPort;

import java.util.List;

public class FindArticleService {
    private final ArticleDaoPort articleDao;

    public FindArticleService(ArticleDaoPort articleDao) {
        this.articleDao = articleDao;
    }

    public List<Article> findAll() {
        return articleDao.findAll();
    }

    public Article findById(int id) throws NonExistsArticleIdException {
        return articleDao.findById(id)
                .orElseThrow(() -> new NonExistsArticleIdException());
    }
}
