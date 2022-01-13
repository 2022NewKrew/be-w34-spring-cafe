package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.repository.ArticleRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void postArticle(ArticleDto article) throws SQLException {
        articleRepository.save(article);
    }

    public List<Article> getArticleList() {
        return articleRepository.getAllArticles();
    }

    public ArticleDto findByIndex(int index) throws NoSuchElementException {
        ArticleDto article = articleRepository.findById(index);
        if (article == null)
            throw new NoSuchElementException("해당 Index를 가진 article이 존재하지 않음");
        return article;
    }
}
